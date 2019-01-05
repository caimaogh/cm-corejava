package com.caimao.concurrent.thread;

import java.util.concurrent.TimeUnit;

import static java.lang.Thread.currentThread;

public class ThreadInterrupt {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() ->{
            try{
                //TimeUnit.MINUTES.sleep(1);
                while(true){
                    System.out.println("Thread.interrupted():"+Thread.interrupted());
                }

            }catch(Exception e){
                System.out.println("Oh, i am be interrupted.");
            }
        });
        System.out.println("before thread start: "+thread.getId()+"_"+ thread.getName()+" status:"+ thread.getState());
        thread.setDaemon(true);
        thread.start();
        System.out.println("after thread start: "+thread.getId()+"_"+ thread.getName()+" status:"+ thread.getState());

        //休眠2毫秒确保线程启动
        TimeUnit.MILLISECONDS.sleep(1);
        System.out.println("before invoke interrupt, thread isInterrupted:"+thread.isInterrupted());
        //中断线程
        thread.interrupt();
        System.out.println("after invoke interrupt, thread isInterrupted:"+thread.isInterrupted());
        System.out.println("after thread interrupt: "+thread.getId()+"_"+ thread.getName()+" status:"+ thread.getState());
        //System.out.println("after interrupt: "+ currentThread().getId()+"_"+ currentThread().getName()+" status:"+ currentThread().getState());

    }

}
