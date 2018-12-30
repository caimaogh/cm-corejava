package com.caimao.inputoutput.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.rmi.server.ServerCloneException;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

public class MutiplexerTimeServer implements Runnable {
    private Selector selector;
    private ServerSocketChannel servChannel;
    private volatile boolean stop;

    /*
    * 构造方法，在构造方法中进行资源初始化。创建多路复用器Selector、ServerSocketChannel，对Channel和TCP参数进行配置；
    */
    public MutiplexerTimeServer(int port){
        try{
            selector = Selector.open();
            servChannel.configureBlocking(false);//将ServerSocketChannel设置成异步非阻塞模式
            servChannel.socket().bind(new InetSocketAddress(port), 1024);
            //系统资源初始化成功后，将ServerSocketChannel注册到Selector，监听SelectionKey.OP_ACCEPT操作位
            servChannel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("The nio time serveris start in port:" + port);
        }catch(IOException e){
            //初始化失败则退出
            System.exit(1);
        }
    }

    public void stop(){
        this.stop = stop;
    }

    /*
    *  在线程的run方法的while循环体中循环遍历selector，它的休眠时间为1秒；
    *  无论是否有读写等事件发生，selector每隔1秒都被唤醒一次；
    *  selector还提供了一个无参的select方法；当有处于就绪的Channel时，selector将返回该Channel的SelectionKey集合
    *  通过对就绪状态的Channel集合进行迭代，就可以进行网络的异步读写操作；
    */
    @Override
    public void run() {
        while(!stop){
            try{
                selector.select(1000);
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> it = selectionKeys.iterator();
                SelectionKey key = null;
                while(it.hasNext()){
                    key = it.next();
                    it.remove();
                    try{
                        handleInput(key);
                    }catch(Exception e){
                        if(key != null){
                            key.cancel();
                            if(key.channel()!=null){
                                key.channel().close();
                            }
                        }
                    }
                }
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
        //多路复用器关闭后，所以注册在上面的channel和pipe等资源都会被字段去注册并关闭，所以不需要重复释放资源
        if(selector!=null){
            try{
                selector.close();
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }

    private void handleInput(SelectionKey key) throws IOException{
        if(key.isValid()){
            //
            if(key.isAcceptable()){
                //Accept  the new connection
                ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
                SocketChannel sc = ssc.accept();
                sc.configureBlocking(false);
                //Add the new connection to the selector
                sc.register(selector, SelectionKey.OP_READ);
            }
            if(key.isReadable()){
                //Read the data
                SocketChannel sc = (SocketChannel) key.channel();
                ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                int readBytes = sc.read(readBuffer);
                if(readBytes > 0){
                    readBuffer.flip();
                    byte[] bytes = new byte[readBuffer.remaining()];
                    readBuffer.get(bytes);
                    String body = new String(bytes, "UTF-8");
                    System.out.println("The time server receive order:"+body);
                    String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body) ? new Date(System.currentTimeMillis()).toString() : "BAD ORDER";
                    doWrite(sc, currentTime);
                }else if(readBytes<0){
                    //close channel
                    key.cancel();
                    sc.close();
                }else{
                    ;
                }
            }
        }
    }

    private void doWrite(SocketChannel channel, String response) throws IOException{
        if(response!=null && response.trim().length()>0){
            byte[] bytes = response.getBytes();
            ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
            writeBuffer.put(bytes);
            writeBuffer.flip();
            channel.write(writeBuffer);
        }
    }
}
