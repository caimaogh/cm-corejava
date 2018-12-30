package com.caimao.concurrent.booleanlock;

import java.util.List;
import java.util.concurrent.TimeoutException;

public interface Lock {
    //lock方法是永远阻塞的，除非获取到了锁；但是该方法是可中断的，中断时会抛出InterruptedException
    void lock() throws InterruptedException;
    //lock(long miles)方法可以被中断，还增加了超时功能
    void lock(long miles) throws InterruptedException, TimeoutException;
    //unlock()方法用来进行锁的释放
    void unlock();
    //getBlockedThreads()方法用于获取哪些线程被阻塞的
    List<Thread> getBlockedThreads();
}
