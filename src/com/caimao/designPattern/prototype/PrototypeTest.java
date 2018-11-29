package com.caimao.designPattern.prototype;

import com.caimao.designPattern.prototype.framework.Manager;
import com.caimao.designPattern.prototype.framework.Product;

public class PrototypeTest {
    public static void main(String[] args){
        Manager manager = new Manager();
        UnderlinePen pen = new UnderlinePen('~');
        MessageBox mbox = new MessageBox('*');
        MessageBox sbox = new MessageBox('/');
        manager.register("strong message", pen);
        manager.register("warning box", mbox);
        manager.register("slash box", sbox);

        Product p1 = manager.create("strong message");
        p1.use("Hello world");
        Product p2 = manager.create("warning box");
        p2.use("Hello world");
        Product p3 = manager.create("slash box");
        p3.use("Hello world");
    }
}
