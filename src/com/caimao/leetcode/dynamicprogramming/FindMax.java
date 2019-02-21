package com.caimao.leetcode.dynamicprogramming;



public class FindMax {
    public static int number = 4;
    public static int capacity = 8;
    static int[] x = {0,0,0,0,0};
    static int[] w = {0,2,3,4,5};
    static int[] v = {0,3,4,5,6};
    static int[][] V =new int[5][9];

    public static int  findMax(){
        int i,j;//j表示包的容量，
        for(i=1;i<=number;i++){
            for(j=1; j<=capacity; j++){
                if(j<w[i]){//包装不进去
                    V[i][j] = V[i-1][j];
                }else{//能装进去
                    if(V[i-1][j] > V[i-1][j-w[i]]+v[i]){
                        V[i][j] = V[i-1][j];
                    }else{
                        V[i][j] = V[i-1][j-w[i]]+v[i];
                    }
                }
            }
        }
        System.out.println((V[number][capacity]));
        return V[number][capacity];
    }

    public static void findProducts(int i, int j){
        if(i>0){
            if(V[i][j] == V[i-1][j]){//相等说明没有装
                x[i] = 0;//标记未选中
                findProducts(i-1,j);
            }else if(j-w[i] >= 0 && V[i][j] == V[i-1][j-w[i]]+v[i]){
                x[i] = 1;//标记已选中
                findProducts(i-1, j-w[i]);
            }
        }
    }

    public static void main(String[] args){
        findMax();
        findProducts(number,capacity);
        for(int i =0;i<x.length;i++){
            if(x[i]==1){
                System.out.println("第"+i+"个物品被选中，容量为"+w[i]+",价值为"+v[i]);
            }
        }
    }
}
