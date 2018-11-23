package com.caimao.thread;

import java.util.concurrent.TimeUnit;

public class TryStart2Time {
    public static void main(String[] args){
        Thread thread = new Thread(){
            @Override
            public void run(){
                try {
                    System.out.println("Thread start:"+Thread.currentThread().getName()+",status:"+Thread.currentThread().getState());
                    Thread.sleep(2000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Thread start:"+Thread.currentThread().getName()+",status:"+Thread.currentThread().getState());
            }
        };
        thread.start();
        try{
            TimeUnit.SECONDS.sleep(3);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        thread.start();

    }


}
