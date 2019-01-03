package com.caimao.thread;

import java.util.concurrent.TimeUnit;

public class TyrInterrupt {
    public static void main(String[] args){
        //判断当前线程是否被中断
        System.out.println("id:"+Thread.currentThread().getId()+",name:"+
                Thread.currentThread().getName()+",interrupt:"+Thread.interrupted());
        //中断当前线程
        Thread.currentThread().interrupt();
        //判断当前线程是否已经被中断
        System.out.println("id:"+Thread.currentThread().getId()+",name:"+
                Thread.currentThread().getName()+",interrupt:"+Thread.currentThread().isInterrupted());
        try{
            TimeUnit.MINUTES.sleep(1);
        }catch (InterruptedException e){
            System.out.println("====I will be interrupted still==");
        }

    }
}
