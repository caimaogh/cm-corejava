package com.caimao.designPattern.prototype.framework;

/**
 *Product接口是复制功能的接口，继承接口Cloneable(实现该接口的实例可以调用clone方法来字段复制实例)
 * 声明了抽象方法use和createClone的接口
 */
public interface Product extends Cloneable {
    //use方法是用于“使用”的方法，具体使用逻辑则交给子类实现
    public abstract void use(String s);
    /*
      createClone方法是用于复制实例的方法，但是对具体要复制哪一个类一无所知。
      只要是实现了Product接口的类，调用他的createClone方法就可以复制出新的实例
     */

    public abstract  Product createClone();
}
