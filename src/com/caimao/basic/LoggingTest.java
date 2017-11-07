/**
 * 
 */
package com.caimao.basic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>Title: LoggingTest.java</p>
 * <p>Description: </p>
 * @author caimao
 *
 * @date 2017年10月17日 上午9:55:15
 * @version 1.0
 * 
 */
public class LoggingTest {

	final Logger logger = LoggerFactory.getLogger(LoggingTest.class); 
	/**
	 * 测试slf4j
	 */

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		LoggingTest test = new LoggingTest();
		test.testLogging(10,100);
	}
	
	public  void testLogging(Integer a,Integer b){
		logger.info("a="+a+",b="+b+",a/b="+a/b);
		System.out.println("a="+a+",b="+b+",a/b="+a/b);
	}

}
