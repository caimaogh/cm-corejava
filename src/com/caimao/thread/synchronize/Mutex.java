package com.caimao.thread.synchronize;

import java.util.concurrent.TimeUnit;

/**
 * @Author: CaiMao
 * @Description
 * @Date: created in 2019/2/24 12:47
 */
public class Mutex {
    private final static Object MUTEX = new Object();

    public void accessResource(){
        synchronized (MUTEX){
            try{
                TimeUnit.MINUTES.sleep(1);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args){
        final Mutex mutex = new Mutex();
        for(int i=0; i<5; i++){
            new Thread(mutex::accessResource).start();
        }
    }
}
