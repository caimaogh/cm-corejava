/**
 * 
 */
package com.caimao.thread;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * <p>Title: TimeUtil.java</p>
 * <p>Description: 时间工具类</p>
 * @author caimao
 *
 * @date 2017年11月16日 下午2:28:55
 * @version 1.0
 * 
 */
public class TimeUtil {
	
	public static final String YEAR_MONTH_DAY_SECOND = "yyyy-MM-dd HH:mm:ss";
	public static final String YEAR_MONTH_DAY_SECOND2 = "yyyy/MM/dd HH:mm:ss";
	public static final String YEAR_MONTH_DAY_SECOND3 = "yyyy年MM月dd日 HH时mm分ss秒";
	public static final String YEAR_MONTH_DAY = "yyyy-MM-dd";
	public static final String YEAR_MONTH_DAY2 = "yyyy/MM/dd";
	public static final String YEAR_MONTH_DAY3 = "yyyy年MM月dd日";
	
	/**
	 * 采用ThreadLocal避免SimpleDateFormat非线程安全的问题
	 * <p>
	 * Key   -- 时间格式
	 * Value -- 解析特定时间格式的SimpleDateFormat
	 */
	private static ThreadLocal<Map<String, SimpleDateFormat>> sThreadLocal = new ThreadLocal<>();
	
	/**
	 * 获取解析特定时间格式的SimpleDateFormat
	 * @param pattern 时间格式
	 */
	private static SimpleDateFormat getDateFormat(String pattern){
		// get()方法是用来获取ThreadLocal在当前线程中保存的变量副本
		Map<String, SimpleDateFormat> strDateFormatMap = sThreadLocal.get();
		
		if(strDateFormatMap == null){
			strDateFormatMap = new HashMap<>();
		}
		
		SimpleDateFormat simpleDateFormat = strDateFormatMap.get(pattern);
		if(simpleDateFormat == null){
			simpleDateFormat = new SimpleDateFormat(pattern, Locale.getDefault());
			strDateFormatMap.put(pattern, simpleDateFormat);
			//set()用来设置当前线程中变量的副本
			sThreadLocal.set(strDateFormatMap);
		}
		
		return simpleDateFormat;
	}
	
	/**
	 * 时间格式化
	 * 
	 * @param date: 要格式化的时间
	 * @param pattern 要格式化的类型
	 */
	public static String formatDate(Date date, String pattern){
		if(date == null || pattern == null){
			return null;
		}
		
		return getDateFormat(pattern).format(date);
	}

}
