/**
 * 
 */
package com.caimao.basic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * <p>Title: AutoBoxingDemo.java</p>
 * <p>Description: autoboxing自动装箱(Integer i = 100;//相当于编译器自动为您作以下的语法编译：Integer i = Integer.valueOf(100);)
 *                 unboxing自动拆箱(将对象中的基本数据从对象中自动取出int t = i; //拆箱，实际上执行了 int t = i.intValue();) 
 *    应当避免同一组参数只需要经过类型转换就可以被传递给不同的重载方法;当传递相同的参数时,所有重载方法的行为必须一致
 * </p>
 * @author caimao
 *
 * @date 2017年7月4日 下午4:52:25
 * @version 1.0
 * 
 */
public class AutoBoxingDemo {

	public static String classify(Set<?> s){
		return "Set";
	}
	public static String classify(List<?> l){
		return "List";
	}
	public static String classify(Collection<?> c){
		return "unknow Collection";
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Set<Integer> set = new TreeSet<Integer>();
		List<Integer> list = new ArrayList<Integer>();
		for(int i=-3; i<3;i++){
			set.add(i);
			list.add(i);
		}
		for(int j=0;j<3;j++){
			set.remove(j);
			list.remove(j);//list的remove(int index)
			               //list的remove(Object o)
		}
		System.out.println(set);
		System.out.println(list);
		
		
		PC[] pcs = {new PC(),new Mac(),new MacBook()};
		for(PC pc : pcs){
			System.out.println(pc.brand());
		}
		
		Collection<?>[] collection = {
				new HashSet<String>(),
				new ArrayList<Integer>(),
				new HashMap<String,String>().values()
		};
		for(Collection<?> c : collection){
			System.out.println(classify(c));
		}
	}
	

}

class PC{
	String brand(){
		return "PC";
	}
}

class Mac extends PC{
	String brand(){
		return "Mac";
	}
}

class MacBook extends Mac{
	String brand(){
		return "MacBook";
	}
}


