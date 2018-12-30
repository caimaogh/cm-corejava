package com.caimao.concurrent.threadgroup;

public class ThreadGroupTest {
    public static void main(String[] args){
        //获取当前线程的group
        ThreadGroup currentGroup = Thread.currentThread().getThreadGroup();
        //定义一个新的group1,没有指定父group，所以默认父group为当前线程所在的group
        ThreadGroup group1 = new ThreadGroup("Group1");
        System.out.println("group1==currentGroup :" + (group1.getParent() == currentGroup));
        //定义一个新的group2，构造2时显式指定父group为group1，所以
        ThreadGroup group2 = new ThreadGroup(group1,"Group2");
        System.out.println("group2.getParent()==group1 :" + (group2.getParent() == group1));
        System.out.println("group2.getParent()==currentGroup :" + (group2.getParent() == currentGroup));
        System.out.println("group2.getParent():" + group2.getParent() + ",group1.getParent():" + group1.getParent() +
                ",currentGroup:" +currentGroup);
    }
}
