/**
 * 
 */
package com.caimao.basic;

import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

/**
 * <p>Title: BigIntegerTest.java</p>
 * <p>Description: </p>
 * @author caimao
 *
 * @date 2017年3月30日 下午1:52:29
 * @version 1.0
 * 
 */
public class BigIntegerTest {
	public static void main(String[] args) {
		Integer[] redBallPool = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,
				17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33};
		Integer[] blueBallPool = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16};
		
		Scanner in = new Scanner(System.in);
		System.out.println("请输入购买号码！");
		String buyNum = in.nextLine();
		//抽奖程序
		Set<Integer> set = new TreeSet<Integer>();
		Random ran = new Random(); 
		//抽红球
		while(set.size()<7){
			set.add(redBallPool[ran.nextInt(redBallPool.length)]);
		}
		//抽蓝球
		Integer blueBall = redBallPool[ran.nextInt(blueBallPool.length)];
		//打印下注号码
		System.out.println(buyNum.toString());
		//打印中奖号码
		System.out.println(set.toString()+"  "+blueBall);
	}

}
