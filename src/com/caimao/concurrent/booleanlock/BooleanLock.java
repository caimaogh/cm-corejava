package com.caimao.concurrent.booleanlock;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeoutException;

import static java.lang.System.currentTimeMillis;
import static java.lang.Thread.currentThread;

public class BooleanLock implements Lock {
    private  Thread currentThread;//代表当前拥有锁的线程
    private boolean locked = false;//是一个开关，false表示当前锁没有被任何线程获得或者已经释放
    private final List<Thread> blockedList = new ArrayList<>();//用于存放哪些线程在获取当前线程时进入阻塞状态
    @Override
    public void lock() throws InterruptedException {
        //lock方法使用synchronize同步代码块的方式进行方法同步
        synchronized (this){
            //如果当前锁已经被某个线程获得，则该线程将加入阻塞队列，并且使当前线程wait释放出对this monitor的所有权
            while(locked){
                blockedList.add(currentThread());
                this.wait();
            }
            //如果当前锁没有被其他线程获得，则该线程将尝试从阻塞队列中删除自己（当前线程从未进入过阻塞队列，删除不影响）
            blockedList.remove(currentThread());
            //指定开关为true
            this.locked = true;
            //记录获取锁的线程
            this.currentThread = currentThread();
        }

    }

    @Override
    public void lock(long miles) throws InterruptedException, TimeoutException {
        synchronized(this){
            //如果miles不合法则默认调用lock()方法；也可以抛出参数非法的异常；
            if(miles <= 0){
                this.lock();
            }else{
                long remainingMiles = miles;
                long endMiles = currentTimeMillis() + remainingMiles;
                while(locked){
                    //如果remainingMiles小于等于0，则意味着当前线程被其他线程唤醒或者在指定的wait时间到了之后还没有获得锁
                    if(remainingMiles <= 0){
                        throw new TimeoutException("can not get the lock during " + miles);
                    }
                    if(!blockedList.contains(currentThread())){
                        blockedList.add(currentThread());
                    }
                    //等待remainingMiles的毫秒数，该值最开始是由其他线程传入的，但在多次wait的过程中会重新计算
                    this.wait(remainingMiles);
                    //重新计算等待remainingMiles的毫秒数时间
                    remainingMiles = endMiles - currentTimeMillis();
                }
                //获得该锁，并且从block列表中删除当前线程，将locked的状态修改为true并且指定获得锁的线程就是当前线程
                blockedList.remove(currentThread());
                this.locked = true;
                this.currentThread = currentThread();
            }
        }
    }

    @Override
    public void unlock() {
        synchronized (this){
            //判断当前线程是否为获取锁的那个线程，只有加了所得线程才有资格进行解锁
            if(currentThread == currentThread()){
                //修改锁的locked状态为false
                this.locked = false;
                //通知其他在wait set中的线程可以再次尝试抢锁了
                this.notifyAll();
            }
        }
    }

    @Override
    public List<Thread> getBlockedThreads() {
        return Collections.unmodifiableList(blockedList);
    }
}
