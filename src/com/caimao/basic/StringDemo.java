package com.caimao.basic;
import java.lang.String;
import java.util.Arrays;
public class StringDemo {

	public StringDemo() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		String str = "0,1,,,,,,,,,,";
		String[] strArr = str.split(",",1);
		System.out.println(Arrays.toString(strArr));
		System.out.println("=strArr.length="+strArr.length);
		char a = 'a';
		//Character.MAX_HIGH_SURROGATE 56319
		//Character.MAX_LOW_SURROGATE  57343 1024d
		//Character.MIN_HIGH_SURROGATE 55296
		//Character.MIN_LOW_SURROGATE  56321 1025
		System.out.println(Character.MAX_LOW_SURROGATE+2);
		System.out.println(a < Character.MIN_HIGH_SURROGATE ||
	             a > Character.MAX_LOW_SURROGATE);
		System.out.println("Character.MAX_LOW_SURROGATE="+Character.MAX_LOW_SURROGATE);
	}

}
