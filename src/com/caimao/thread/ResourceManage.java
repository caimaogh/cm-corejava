package com.caimao.thread;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

public class ResourceManage {

    private Semaphore semaphore;
    private boolean resourceArray[];
    private final ReentrantLock lock;

    public ResourceManage(){
        this.resourceArray = new boolean[10];//
        this.semaphore = new Semaphore(10, true);//
        this.lock = new ReentrantLock(true);
        for(int i = 0; i < 10; i++){
            resourceArray[i] = true;
        }
    }

    public void useResource(int userId){
        try {
            semaphore.acquire();
            int id = getResourceId();
            System.out.println("userId:"+userId+" is on using,resourceId is:"+id+",Thread:"+Thread.currentThread().getName()+"\n");
            Thread.sleep(1000);

            resourceArray[id] = true;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
        }
    }

    private int getResourceId(){
        int id = -1;
        lock.lock();
        try{
            for(int i=0; i<10; i++){
                if(resourceArray[i]){
                    resourceArray[i] = false;
                    id = i;
                    break;
                }
            }
        }catch ( Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
        return id;
    }

}
