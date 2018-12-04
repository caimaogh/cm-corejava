package com.caimao.architecture;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 *RPC服务端服务发布者，运行在RPC服务端，负责将本地服务发布在成远程服务，供其他消费者调用；
 *服务发布者的主要职责：
 * 1）作为服务器，监听客户端的TCP连接，接收到新的客户端连接后将其封装成Task，由线程池执行；
 * 2）将客户端发送的码流反序列化成对象，反射调用服务实现者，获取执行结果；
 * 3）将执行结果对象反序列化，通过Socket发生给客户端；
 * 4）远程服务调用完成后，释放Socket等连接资源，防止句柄泄露；
 */
public class RpcExporter {
    static Executor executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    public static void export(String hostName, int port) throws Exception{
        ServerSocket server = new ServerSocket();
        server.bind(new InetSocketAddress(hostName, port));
        try{
            while (true){
                executor.execute(new ExporterTask(server.accept()));
            }
        }finally {
            server.close();
        }
    }

    private static class ExporterTask implements Runnable{

        Socket client = null;

        public ExporterTask(Socket client){
            this.client = client;
        }

        @Override
        public void run() {
            ObjectInputStream input = null;
            ObjectOutputStream output = null;
            try{
                input = new ObjectInputStream(client.getInputStream());
                String interfaceName = input.readUTF();
                Class<?> service = Class.forName(interfaceName);
                String methodName = input.readUTF();
                Class<?>[] parameterType = (Class<?>[]) input.readObject();
                Object[] arguments = (Object[]) input.readObject();
                Method method = service.getMethod(methodName, parameterType);
                Object result = method.invoke(service.newInstance(), arguments);
                output = new ObjectOutputStream(client.getOutputStream());
                output.writeObject(result);
            }catch(Exception e){
                e.printStackTrace();
            }finally {
                if(output!=null){
                    try {
                        output.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if(input!=null){
                    try {
                        input.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if(client!=null){
                    try {
                        client.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
