package com.caimao.thread;

public class ThreadLocalDemo {
    /**
     *每个线程所产生的序号虽然豆共享一个实例，但他们并没有发生相互干扰，而是各自产生独立的序号；
     * 原因就是通过ThreadLocal为每一个线程提供了单独的副本；
     * ThreadLocal提供了线程安全的对象封装，在实现多线程编码时，可以将不安全的变量装进ThreadLocal
     */
    //内部类修改ThreadLocal的初始值为0
    private static ThreadLocal<Integer> seqNum = new ThreadLocal<Integer>(){
        public Integer initialValue(){
            return 0;
        }
    };

    public int getNextNum(){
        seqNum.set(seqNum.get()+1);
        return seqNum.get();
    }

    public static void main(String[] args){
        ThreadLocalDemo td = new ThreadLocalDemo();
        TestClient tl1 = new TestClient(td);
        TestClient tl2 = new TestClient(td);
        TestClient tl3 = new TestClient(td);
        tl1.start();
        tl2.start();
        tl3.start();
    }

    private static class TestClient extends Thread{
        private ThreadLocalDemo td;
        public TestClient(ThreadLocalDemo td){
            this.td = td;
        }
        public void run(){
            for(int i=0; i<5; i++){
                System.out.println("======"+"Thread["+Thread.currentThread().getName()+"]td["+td.getNextNum()+"]");
            }
        }
    }

}
