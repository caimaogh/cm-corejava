package com.caimao.architecture;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.InetSocketAddress;
import java.net.Socket;
/**
 * 本地服务代理
 * 1）将本地的接口调用转换成JDK的动态代理，在动态代理中实现接口的远程调用
 * 2）创建Socket客户端，根据指定地址连接远程服务提供者
 * 3）将远程服务调用所需要的接口类、方法名、参数列表等编码后发生给服务提供者
 * 4）同步阻塞等待服务端返回响应，获取应答之后返回；
 *
 * */
public class RpcImporter<S> {
    public S importer(final Class<?> serviceClass, final InetSocketAddress addr){
        return (S) Proxy.newProxyInstance(serviceClass.getClassLoader(), new Class<?>[]{serviceClass.getInterfaces()[0]},
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        Socket socket = null;
                        ObjectOutputStream output = null;
                        ObjectInputStream input = null;
                        try{
                            socket = new Socket();
                            socket.connect(addr);
                            output = new ObjectOutputStream(socket.getOutputStream());
                            output.writeUTF(serviceClass.getName());
                            output.writeUTF(method.getName());
                            output.writeObject(method.getParameterTypes());
                            output.writeObject(args);
                            input = new ObjectInputStream(socket.getInputStream());
                            return input.readObject();
                        }finally{
                            if(socket!=null){
                                socket.close();
                            }
                            if(output!=null){
                                output.close();
                            }
                            if(input!=null){
                                input.close();
                            }
                        }
                    }
                });
    }

    public static void main(String[] args){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    RpcExporter.export("localhost", 8080);
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
        RpcImporter<EchoService> importer = new RpcImporter<EchoService>();
        EchoService echo = importer.importer(EchoService.class, new InetSocketAddress("localhost", 8080));
        System.out.println(echo.echo("Are U OK?"));
    }
}
