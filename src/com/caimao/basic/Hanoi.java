package com.caimao.basic;

public class Hanoi {

    static int step = 0;

    private void hanoi(int plateNum, String origin, String assist, String destination){
        if (plateNum == 1){
            move(origin, destination);
        }else{
            hanoi(plateNum - 1, origin, destination, assist);
            move(origin, destination);
            hanoi(plateNum - 1, assist, origin, destination);
        }
    }

    private void move(String origin, String destination){
        System.out.println(origin+"---->"+destination);
        step += 1;
    }

    public static void main(String[] args){
        Hanoi h = new Hanoi();
        h.hanoi(3, "A", "B", "C");
        System.out.println("need step:" + step);
    }
}
