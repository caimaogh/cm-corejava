package com.caimao.concurrent.booleanlock;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import static java.lang.Thread.currentThread;
import static java.util.concurrent.ThreadLocalRandom.current;

public class BooleanLockTest {
    //定义BooleanLock
    private final Lock lock = new BooleanLock();
    //使用try finally语句块确保lock每次都能被正确释放
    public void syncMethod(){
        //add lock
        try{
            lock.lock();
            int randomInt = current().nextInt(10);
            System.out.println(currentThread() + " get the lock");
            TimeUnit.SECONDS.sleep(randomInt);
        }catch(InterruptedException e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args){
        BooleanLockTest test = new BooleanLockTest();
        IntStream.range(0,10)
                .mapToObj(i -> new Thread(test::syncMethod))
                .forEach(Thread::start);
    }
}























