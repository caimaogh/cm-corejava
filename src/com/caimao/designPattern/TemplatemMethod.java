package com.caimao.designPattern;
/**
 * Template Method: 在父类中定义处理流程的框架，在子类中实现具体处理；即在父类的模版方法中编写了算法，在每个子类中无需再编写算法；
 * AbstractClass(抽象类)角色不仅负责实现模版方法，还负责声明在模版方法中所使用到的抽象方法（由子类实现）；无法由接口代替，接口无法
 * ConcreteClass(具体类)角色抽象类角色中定义的方法；例如InputStream的子类都是；
 *
 *无论在父类类型的变量中保存哪个子类的实例，程序都可以正常工作，这种原则称为里氏替换原则；
 * */
public class TemplatemMethod {
    public static void main(String[] args){
        AbstractDisplay d1 = new CharDisplay('H');
        AbstractDisplay d2 = new StringDisplay("Hello,Template");
        d1.dispaly();
        d2.dispaly();
    }

}

//抽象类AbstractDisplay
abstract  class AbstractDisplay{
    //交给子类去实现抽象方法
    public abstract  void open();
    public abstract  void print();
    public abstract  void close();
    //本抽象类中实现的方法
    public final void dispaly(){
        open();
        for(int i=0; i<5; i++){
            print();
        }
        close();
    }
}

class CharDisplay extends AbstractDisplay{
    private char ch;
    public CharDisplay(char ch){
        this.ch = ch;
    }

    @Override
    public void open() {
        System.out.println("open():<<<<<<");
    }

    @Override
    public void print() {
        System.out.println(ch);
    }

    @Override
    public void close() {
        System.out.println("close():>>>>>>");
    }
}

class StringDisplay extends  AbstractDisplay{
    private String str;
    public StringDisplay(String str){
        this.str = str;
    }

    @Override
    public void open() {
        System.out.println("open():+++++++++");
    }

    @Override
    public void print() {
        System.out.println(str);
    }

    @Override
    public void close() {
        System.out.println("close():+++++++++");
    }
}
