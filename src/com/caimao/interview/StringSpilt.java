package com.caimao.interview;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 * 西门子上机题2019年1月，实现String类的 split 方法，限时半小时
 */

public class StringSplit {
    public static void main(String[] args){
        String s = "#abcd#ef#g";
        System.out.println("===true========="+split(s, '#', true));
        System.out.println("===false========="+split(s, '#', false));
    }

    private static String[] split(String s, char c, boolean flag){

        char[] target = s.toCharArray();
        List<String> list = new ArrayList<String>();
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<target.length; i++){
            if(target[i]==c){
                if(flag && sb.length()<1){
                    list.add(" ");
                }if(flag && sb.length()>0){
                    list.add(sb.toString());
                    list.add(" ");
                    sb = new StringBuilder();
                }if(!flag && sb.length()>0){
                    list.add(sb.toString());
                    sb = new StringBuilder();
                }
            }else{
                sb.append(target[i]);
                if(i==target.length-1){
                    list.add(sb.toString());
                }
            }
        }
        ;
        System.out.println("============"+Arrays.toString(list.toArray()));
        String[] result = new String[list.size()];
//        for(int j=0; j<list.size(); j++){
//            result[j] = list.get(j);
//        }
//        System.out.println("============"+ Arrays.toString(result));
        return result;
    }
}
