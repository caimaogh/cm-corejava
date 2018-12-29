package com.caimao.thread;

import java.util.concurrent.TimeUnit;

public class ThreadInterrupt {
    public static void main(String[] args){
        System.out.println(Thread.currentThread().getId()+"-"+Thread.currentThread().getName()+
                " is interrupted? "+Thread.interrupted());
        Thread.currentThread().interrupt();
        System.out.println(Thread.currentThread().getId()+"-"+Thread.currentThread().getName()+
                " is interrupted? "+Thread.currentThread().isInterrupted());
        try{
            TimeUnit.MINUTES.sleep(2);
        }catch(InterruptedException e){
            System.out.println("be interrupted still");
        }
    }
}
