package com.caimao.thread;

public class ResourceUser implements Runnable{
    private ResourceManage resourceManage;
    private int userId;
    public ResourceUser(ResourceManage resourceManage, int userId){
        this.resourceManage = resourceManage;
        this.userId = userId;
    }

    public void run(){
        System.out.println("userId:"+userId+" is ready to use resource!");
        resourceManage.useResource(userId);
        System.out.println("userId:"+userId+" has finished to use resource");
    }

    public static void main(String[] args){
        ResourceManage resourceManage = new ResourceManage();
        Thread[] threads = new Thread[100];
        for(int i = 0; i < 100; i++){
            Thread thread = new Thread(new ResourceUser(resourceManage, i));
            threads[i] = thread;
        }
        for(int i = 0; i < 100; i++){
            Thread thread = threads[i];
            try{
                thread.start();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
