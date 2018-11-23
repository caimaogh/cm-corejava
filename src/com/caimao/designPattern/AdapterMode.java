package com.caimao.designPattern;
/**
 * @author cm
 *  Adapter模式用于连接接口(API)不同的类；
 * 类适配器模式
 * 位于实际情况与需求之间，填补两者之间的差异
 * 对象适配器模式则是把需要实现的接口换成抽象类并且注入进来即可
 * Adapt模式：
 *  Target(对象):    负责定义所需的方法；--Print（接口/抽象类）
 *  Client(请求者):  负责使用Target角色所定义的方法进行具体处理；（main方法，调用者或实际需求）
 *  Adaptee(被适配): 持有既定方法的角色；--Banner
 *  Adapter(适配器): 适配器模式主角，使Adaptee角色的方法来满足Target角色的需求；--PrintBanner
 */
public class AdapterMode {
    public static void main(String[] args){
        //将PrintBanner类的实例保存在Print类型的变量中，Banner类包括其中方法完全被隐藏
        //对于调用者来说在完全不知道PrintBanner类如何实现的，可以不用对调用者进行修改下改变PrintBanner类的具体实现
        Print p = new PrintBanner("Heelo");
        p.printWeak();
        p.printStrong();
    }

}

class Banner{
    private String string;
    public Banner(String string){
        this.string = string;
    }
    public void showWithParen(){
        System.out.println("("+string+")");
    }
    public void showWithAster(){
        System.out.println("*"+string+"*");
    }
}

interface  Print{
    public abstract void printWeak();
    public abstract void printStrong();
}

class PrintBanner extends Banner implements Print{
    public PrintBanner(String string){
        super(string);
    }

    @Override
    public void printWeak() {
        showWithAster();
    }

    @Override
    public void printStrong() {
        showWithParen();
    }
}
