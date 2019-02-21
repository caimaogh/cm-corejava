package com.caimao.concurrent.thread;

/**
 * @Author: CaiMao
 * @Description
 * @Date: created in 2019/1/1 10:31
 */
public class ClassLoaderTest {
    private static ClassLoaderTest test = new ClassLoaderTest();

    public ClassLoaderTest(){
        a++;
        b++;
    }
    private static int a = 0;
    private static int b;


    private static ClassLoaderTest getTest(){
        return test;
    }
    public static void main(String[] args){
        ClassLoaderTest clt = ClassLoaderTest.getTest();
        System.out.println("a="+clt.a);
        System.out.println("b="+clt.b);

        System.out.println("Bootstrap："+String.class.getClassLoader());
        System.out.println("path:"+System.getProperty("sun.boot.class.path"));
    }
}
