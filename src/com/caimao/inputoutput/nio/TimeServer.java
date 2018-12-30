package com.caimao.inputoutput.nio;

public class TimeServer {
    public static void main(String[] args){
        int port = 8080;
        if(args!=null && args.length>0){
            port = Integer.valueOf(args[0]);
        }
        MutiplexerTimeServer timeServer = new MutiplexerTimeServer(port);
        new Thread(timeServer, "NIO-MutiplexerTimeServer-001").start();
    }
}
