package com.caimao.concurrent.thread;

import java.util.concurrent.TimeUnit;

/**
 * @Author: CaiMao
 * @Description 如果一个线程设置了interrupt标识，那么之后执行可中断方法将会立即中断；
 * @Date: created in 2018/12/30 13:40
 */
public class ThreadInterruptTest {
    public static void main(String[] args){
        System.out.println("1 main thread isInterrupted:"+Thread.currentThread().isInterrupted());
        //invoke interrupted method
        System.out.println("2 main thread invoke static method interrupted:"+Thread.interrupted());
        System.out.println("3 main thread isInterrupted:"+Thread.currentThread().isInterrupted());
        //interrupt thread
        Thread.currentThread().interrupt();
        System.out.println("4 main thread isInterrupted:"+Thread.currentThread().isInterrupted());
        try{
            Thread.interrupted();//立即擦除interrupt标识，使可中断方法继续
            TimeUnit.MILLISECONDS.sleep(1);
            System.out.println(" =============");
        }catch(InterruptedException e){
            System.out.println("interrupted ops");
        }
    }
}
