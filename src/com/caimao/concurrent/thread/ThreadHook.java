package com.caimao.concurrent.thread;

import java.util.concurrent.TimeUnit;

/**
 * @Author: CaiMao
 * @Description
 * @Date: created in 2018/12/30 18:04
 */
public class ThreadHook {
    public static void main(String[] args){
        //inject hook thread for application
        Runtime.getRuntime().addShutdownHook(new Thread("Hook1"){
            @Override
            public void run(){
                try{
                    System.out.println("The hook thread 1 is running!");
                    TimeUnit.SECONDS.sleep(1);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
                System.out.println("The hook thread 1 will exit");
            }
        });

        //inject hook thread for application
        Runtime.getRuntime().addShutdownHook(new Thread("Hook2"){
            @Override
            public void run(){
                try{
                    System.out.println("The hook thread 2 is running!");
                    TimeUnit.SECONDS.sleep(1);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
                System.out.println("The hook thread 2 will exit");
            }
        });
        System.out.println("the program will going to stop!!! ");
    }
}
