package com.caimao.designPattern;

import java.util.*;

public class IteratorMode {
    /*
     *Iterator模式可以将遍历与实现分离开来
     * Iterator迭代器，负责定义按顺序逐个遍历元素的接口（API）定义hasNext和next方法
     * 具体的集合类实现该接口并实现其两个方法，抽时间看下ArrayList LinkedList HashMap等的实现
     */

    public static void main(String[] args){
        List<String> list = new ArrayList();
        list.add("Java");
        list.add("JavaScript");
        list.add("Zookeeper");
        list.add("Node.js");
        list.add("Spring Boot");
        Iterator it = list.iterator();
        while(it.hasNext()){
            System.out.println(it.next());
        }
        Map<String, String> map = new HashMap<>();
        map.put("001", "Redis");
        map.put("002", "Hadoop");
        map.put("003", "MSA");
        //需要在遍历时删除entries活着JDK1.5以下时需要用Iterator方式
        Iterator<Map.Entry<String, String>> entries = map.entrySet().iterator();
        while(entries.hasNext()){
            Map.Entry<String, String> entry = entries.next();
            System.out.println("key="+entry.getKey()+", value="+entry.getValue());
        }
        //也可以使用如下遍历 代码简洁
        for(Map.Entry<String, String> entry : map.entrySet()){
            System.out.println("key="+entry.getKey()+", value="+entry.getValue());
        }
        //也可以使用单独循环key或value
        for(String str : map.keySet()){
            System.out.println(str+","+map.get(str));
        }
        for(String str : map.values()){
            System.out.println(str);
        }
    }
}
