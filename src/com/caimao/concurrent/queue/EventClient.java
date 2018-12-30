package com.caimao.concurrent.queue;

import java.util.concurrent.TimeUnit;

public class EventClient {
    public static void main(String[] args){

        final EventQueue eventQueue = new EventQueue();
        //Producer线程模拟提交Event的客户端几乎没有任何的延时
        new Thread(() ->{
            for(;;){
                eventQueue.offer(new EventQueue.Event());
            }
        }, "Producer") .start();

        //consumer线程用于模拟处理请求的工作线程
        new Thread(() ->{
            for(;;){
                eventQueue.take();
                try{
                    TimeUnit.MILLISECONDS.sleep(10);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
        }, "consumer1").start();

        //consumer线程用于模拟处理请求的工作线程
        new Thread(() ->{
            for(;;){
                eventQueue.take();
                try{
                    TimeUnit.MILLISECONDS.sleep(10);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
        }, "consumer2").start();
    }
}









