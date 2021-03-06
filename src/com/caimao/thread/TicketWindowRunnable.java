package com.caimao.thread;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class TicketWindowRunnable implements Runnable{
    private int index = 1;
    public final static int MaxNum= 500;
    private final static Object MUTEX = new Object();
    @Override
    public void run() {
        synchronized (MUTEX) {
            while (MaxNum >= index) {
                System.out.println(Thread.currentThread().getName() + "的号码是：" + (index++));
            }
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args){
        final TicketWindowRunnable task = new TicketWindowRunnable();
        Thread th1 = new Thread(task, "一号窗口");
        Thread th2 = new Thread(task, "二号窗口");
        Thread th3 = new Thread(task, "三号窗口");
        Thread th4 = new Thread(task, "四号窗口");
        th1.start();
        th2.start();
        th3.start();
        th4.start();

        IntStream.range(0, 5).boxed().map(i -> new Thread(
                () -> System.out.println("ThreadName:"+Thread.currentThread().getName())
        )).forEach(Thread::start);
    }


}
