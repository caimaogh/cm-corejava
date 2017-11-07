/**
 * 
 */
package com.caimao.collections;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * <p>Title: IteratorMap.java</p>
 * <p>Description: </p>
 * @author caimao
 *
 * @date 2017年11月7日 下午4:33:37
 * @version 1.0
 * 
 */
public class IteratorMap {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("A", "10");
		map1.put("B", "20");
		map1.put("C", "30");
		map1.put("D", "40");
		map1.put("E", "50");
		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("F", "60");
		map2.put("H", "70");
		map2.put("I", "80");
		map2.put("G", "90");
		map2.put("K", "100");
		Map<String, Object> map3 = new HashMap<String, Object>();
		map3.put("L", "110");
		map3.put("M", "120");
		map3.put("N", "130");
		map3.put("O", "140");
		map3.put("P", "150");
		
		//1 keySet (keySet其实是遍历了2次，一次是转为Iterator对象，另一次是从hashMap中取出key所对应的value)
		Iterator<String> iter1 = map1.keySet().iterator();
		Object key;
		Object value;
		while(iter1.hasNext()){
			key = iter1.next();
			value = map1.get(key);
			System.out.println("key="+key+",value="+value);
		}
		
		//2 entrySet (而entrySet只是遍历了一次就把key和value都放到了entry中，效率更高)
		Iterator<Entry<String, Object>> iter2 = map2.entrySet().iterator();
		while(iter2.hasNext()){
			Map.Entry<String, Object> entry = iter2.next();
			System.out.println("key="+entry.getKey()+",value="+entry.getValue());
		}
		
		//3 Map.foreach (本质仍然是entrySet 如果是JDK8，使用Map.foreach方法)
		map3.forEach((k,v)->System.out.println("Item : " + k + " Count : " + v));
		
		map3.forEach((k,v)->{
		    System.out.println("Item2 : " + k + " Count : " + v);
		    if("P".equals(k)){
		        System.out.println("Hello P");
		    }
		});
	}

}
