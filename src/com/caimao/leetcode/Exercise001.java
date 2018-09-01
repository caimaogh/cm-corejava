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
        int[] nums = {8, 3, 8, 9, 7, 9};
        int target = 18;
        try{
            int[] result = twoSum(nums, target);
            System.out.println(Arrays.toString(result));
        }catch(IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }

    public static int[] twoSum(int[] nums, int target){
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
}
