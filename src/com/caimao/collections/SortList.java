/**
 * 
 */
package com.caimao.collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * <p>Title: SortList.java</p>
 * <p>Description: 用于自定义list排序</p>
 * @author caimao
 *
 * @date 2017年11月7日 下午3:26:44
 * @version 1.0
 * 
 */
public class SortList {
	
	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		list.add("Alibaba");
		list.add("FaceBook");
		list.add("Twitter");
		list.add("Google");
		list.add("Googlee");
		System.out.println(list);
		SortList st = new SortList();
		st.sort(list);
		System.out.println(list);
	}
	//该泛型除了String以外还可以是自定义类，例如DTO等，可实现对参数list的自定义排序
	private void sort(List<String> list){
		Collections.sort(list, new Comparator<String>(){

			@Override
			public int compare(String o1, String o2) {
				return o1.compareTo(o2);//String 实现comparable接口,直接调用compareTo方法;
			}
			
		});
	}

}
