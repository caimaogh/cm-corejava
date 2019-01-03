package com.caimao.concurrent.classload;

public class Singleton {
    private static Singleton instance = new Singleton();//0,1
    private static int x = 0;
    private static int y;
    //private static Singleton instance = new Singleton(); 1,1
    private Singleton(){
        x++;
        y++;
    }
    public static Singleton getInstance(){
        return instance;
    }
    public static void main(String[] args){
        Singleton singleton = Singleton.getInstance();
        System.out.println("x=" + singleton.x);
        System.out.println("y=" + singleton.y);
    }
}
