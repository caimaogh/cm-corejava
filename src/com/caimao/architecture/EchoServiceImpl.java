package com.caimao.architecture;
/**
 *服务提供者，运行在服务端负责提供服务接口丁哟和服务实现类；
 *
 *
 */
public class EchoServiceImpl implements EchoService{
    @Override
    public String echo(String ping) {
        return ping != null ? ping+"---> I am OK!" : "I am Ok!";
    }
}
