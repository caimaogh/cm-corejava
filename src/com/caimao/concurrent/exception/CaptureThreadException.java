package com.caimao.concurrent.exception;

import java.util.concurrent.TimeUnit;

/**
 * @Author: CaiMao
 * @Description
 * @Date: created in 2018/12/30 16:40
 */
public class CaptureThreadException {
    public static void main(String[] args){
        //config callback interface
        Thread.setDefaultUncaughtExceptionHandler((t, e) ->{
            System.out.println(t.getName() + " occur exception");
            e.printStackTrace();
        });
        final Thread thread = new Thread(() ->{
            try{
                TimeUnit.SECONDS.sleep(2);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
            //config an unchecked exception
            //here will throw unchecked exception
            System.out.println(1/0);
        }, "Test-Thread");
        thread.start();
    }
}
