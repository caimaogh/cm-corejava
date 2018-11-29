package com.caimao.designPattern.prototype.framework;

import java.util.HashMap;

/**
 *使用Product接口来复制实例，用map保存实例的名字和实例之间的关系；
 *register方法将会接收到1组名字和Product接口实例注册到map中；
 * 一旦在类中使用到了别的类名就意味着该类与其他类耦合在了一起；
 * 在Manager类中并没有写明具体的类名，仅仅使用Product接口名，即Product接口成为连接Manager类与其他具体类之间的桥梁
 */
public class Manager {
    private HashMap showcase = new HashMap();
    public void register(String name, Product proto){
        showcase.put(name, proto);
    }
    public Product create(String protoname){
        Product p = (Product) showcase.get(protoname);
        return p.createClone();
    }
}
