package com.caimao.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author CM
 * @date 2018-08-30
 */

public class Exercise001 {
    /*
     *给定一个整数数组和一个目标值，找出数组中和为目标值的两个数。
     *你可以假设每个输入只对应一种答案，且同样的元素不能被重复利用。
     *给定 nums = [2, 7, 11, 15], target = 9
     * 因为 nums[0] + nums[1] = 2 + 7 = 9 所以返回 [0, 1]
     */
    public static void main(String[] args){
        Exercise001 ex001 = new Exercise001();
        int[] nums = {8, 3, 8, 9, 7, 9};
        int target = 18;
        try{
            int[] result = ex001.twoSum(nums, target);
            System.out.println(Arrays.toString(result));
        }catch(IllegalArgumentException e){
            System.out.println(e.getMessage());
        }

        System.out.println(ex001.maxLengthSubString("abcbaefaecdfd"));
    }

    public int[] twoSum(int[] nums, int target){
        Map<Integer, Integer> map = new HashMap<>();
        int[] result= new int[2];

        for(int i=0; i<nums.length; i++){
            int complent = target - nums[i];
            if(map.containsKey(complent)){
                return new int[]{map.get(complent), i};
            }
            map.put(nums[i], i);
        }
        throw new IllegalArgumentException("没有两个相加等于目标数的");
    }

    public int maxLengthSubString(String s){
        int maxLengtn = 0;
        String subString = "";
        Map<Character, Integer> indexMap = new HashMap<>();
        for(int i=0, j=0; j<s.length(); j++){
            if(indexMap.containsKey(s.charAt(j))){
                i = Math.max(indexMap.get(s.charAt(j)), i);
            }
            maxLengtn = Math.max(maxLengtn, j-i+1);
            indexMap.put(s.charAt(j), j+1);
            if(subString.length()<(j-i+1)){
                subString = s.substring(i, j+1);
            }
        }
        System.out.println("最长子串长度是:"+maxLengtn+",子串:"+subString);
        return maxLengtn;
    }
}
