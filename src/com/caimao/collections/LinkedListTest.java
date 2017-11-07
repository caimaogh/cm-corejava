/**
 * 
 */
package com.caimao.collections;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * <p>Title: LinkedListTest.java</p>
 * <p>Description: </p>
 * @author caimao
 *
 * @date 2017年11月7日 下午1:42:40
 * @version 1.0
 * 
 */
public class LinkedListTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<String> alist = new LinkedList<String>(); 
		alist.add("A");
		alist.add("C");
		alist.add("E");
		List<String> blist = new LinkedList<String>();
		blist.add("B");
		blist.add("D");
		blist.add("F");
		blist.add("G");
		
		//merge blist into alist
		ListIterator<String> aIter = alist.listIterator();
		Iterator<String> bIter = blist.iterator();
		while(bIter.hasNext()){
			if(aIter.hasNext()){
				aIter.next();
			}
			aIter.add(bIter.next());
			
		}
		System.out.println(alist);
		
		//remove every second word from blist
		bIter = blist.iterator();
		while(bIter.hasNext()){
			bIter.next();
			if(bIter.hasNext()){
				bIter.next();
				bIter.remove();
			}
		}
		System.out.println(blist);
		
		//remove all words in blist from alist
		alist.removeAll(blist);
		System.out.println(alist);
	}
}
