package com.caimao.thread;

import java.util.concurrent.TimeUnit;

public class WaitNotify {
    static boolean flag = true;
    static Object lock = new Object();

    public static void main(String[] args) throws InterruptedException{
        //线程A在获取锁后调用了对象lock的wait方法进入了等待状态
        Thread a = new Thread(new Wait(), "wait thread");
        a.start();
        TimeUnit.SECONDS.sleep(2);
        //线程B调用对象lock的notifyAll()方法，线程A收到通知后从wait方法处返回继续执行，线程B对共享变量flag的修改对线程A来说是可见的。
        Thread b = new Thread(new Thread(new Notify()), "notify thread");
        System.out.println("调用wait方法后222："+a.getState());
        b.start();
    }

    static class Wait implements Runnable{
        @Override
        public void run(){
            synchronized(lock){
                while(flag){
                    try{
                        System.out.println(Thread.currentThread()+",flag is "+flag);
                        System.out.println("调用wait方法前："+Thread.currentThread().getState());
                        lock.wait();
                        System.out.println("调用wait方法后："+Thread.currentThread().getState());
                    }catch(InterruptedException e){
                        e.printStackTrace();
                    }
                }
                System.out.println(Thread.currentThread()+",flag is "+flag);
            }

        }
    }

    static class Notify implements Runnable{
        @Override
        public void run(){
            synchronized(lock){
                flag = false;
                System.out.println("调用notifyAll方法前："+Thread.currentThread().getState());
                lock.notifyAll();
                System.out.println("调用notifyAll方法后："+Thread.currentThread().getState());
                try{
                    TimeUnit.SECONDS.sleep(7);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
    }
}
