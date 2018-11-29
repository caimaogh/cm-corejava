package com.caimao.designPattern.singleton;

public class SingletonMode {
    //static 静态类变量在类加载初始化的时候生成一个实例
    private static SingletonMode singletonMode = new SingletonMode();
    private SingletonMode(){
        System.out.println("create a new Singleton instance!");
    }
    public static SingletonMode getInstance(){
        return singletonMode;
    }
}
