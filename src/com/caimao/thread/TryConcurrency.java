package com.caimao.thread;

import java.util.concurrent.TimeUnit;

import static java.lang.Thread.currentThread;

public class TryConcurrency {

    public static void main(String[] args){
        System.out.println("music="+currentThread().getName());
        new Thread(TryConcurrency::brewNews).start();
        new Thread(TryConcurrency::listenMusic).start();
//        listenMusic();
//        brewNews();
    }

    public static void listenMusic(){
        for(;;){
            System.out.println("======listem to music Taylor Swift======");
            System.out.println("======listenMusic======"+ currentThread().getName());
            System.out.println("music="+currentThread().getName());
            sleep(1);
        }
    }

    public static void brewNews(){
        for(;;){
            System.out.println("------brew sports new------");
            System.out.println("======brewNews======"+ currentThread().getName());
            System.out.println("sports="+currentThread().getName());
            sleep(1);
        }
    }

    public static void sleep(int seconds){
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
