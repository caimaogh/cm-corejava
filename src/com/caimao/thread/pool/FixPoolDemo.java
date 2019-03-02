package com.caimao.thread.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FixPoolDemo {
    private static Runnable getThread(final int i){
        return new Runnable() {
            @Override
            public void run() {
                try{
                    Thread.sleep(500);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());
            }
        };
    }
    public static void main(String[] args){
        ExecutorService fixPool = Executors.newFixedThreadPool(5);
        try{
            Thread.sleep(1000);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        for(int i=0; i<10; i++){
            fixPool.execute(getThread(i));
        }
        fixPool.shutdown();
    }
}
