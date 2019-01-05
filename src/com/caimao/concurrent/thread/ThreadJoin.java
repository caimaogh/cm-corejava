package com.caimao.concurrent.thread;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

/**
 * @Author: CaiMao
 * @Description
 * @Date: created in 2018/12/30 14:07
 */
public class ThreadJoin {
    public static void main(String[] args) throws InterruptedException {
        //define two threads
        List<Thread> threads = IntStream.range(1, 3).mapToObj(ThreadJoin::create).collect(toList());
        //run this two threads
        threads.forEach(Thread::start);
        //execute two of threads's join method
        for(Thread thread : threads){
            thread.join();
        }
        //main thread output
        for(int i=0; i<10; i++){
            System.out.println(Thread.currentThread().getName()+" # "+i);
            TimeUnit.SECONDS.sleep(1);
        }
    }

    private static Thread create(int seq){
        return new Thread(() ->{
            for(int i=0; i<10; i++){
                System.out.println(Thread.currentThread().getName()+" # "+i);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, String.valueOf(seq));
    }
}
