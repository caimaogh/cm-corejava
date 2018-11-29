package com.caimao.designPattern.singleton;

public class SingletonTest {
    public static void main(String[] args){
        SingletonMode sm1 = SingletonMode.getInstance();
        SingletonMode sm2 = SingletonMode.getInstance();
        System.out.println("is same instance:"+(sm1==sm2));
    }
}
