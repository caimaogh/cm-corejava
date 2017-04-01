package com.caimao.basic;

import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * <p>Title: CalendarTest.java</p>
 * <p>Description: </p>
 * @author caimao
 *
 * @date 2017年3月31日 上午10:20:47
 * @version 1.0
 * 
 */
public class CalendarTest {

	public static void main(String[] args) {
		 Locale.setDefault(Locale.CHINA);
		//construct currData as current date
		GregorianCalendar currData = new GregorianCalendar();
		
		int today = currData.get(Calendar.DAY_OF_MONTH);//获取当前月的当前日
		int month = currData.get(Calendar.MONTH);//获取当前月(0~11)在获取月份时，Calendar.MONTH + 1 
		System.out.println("today="+today+",month="+month);
		
		//set currData to start date of the month 
		currData.set(Calendar.DAY_OF_MONTH, 1);
		int weekday = currData.get(Calendar.DAY_OF_WEEK);//在获取星期几 Calendar.DAY_OF_WEEK – 1 
		//get first day of week
		int firstDayOfWeek = currData.getFirstDayOfWeek();
		System.out.println("weekday="+weekday+",firstDayOfWeek="+firstDayOfWeek);
		
		//determine the required indentation for the first line
		int ident = 0;
		while(weekday!=firstDayOfWeek){
			ident++;
			currData.add(Calendar.DAY_OF_MONTH, -1);
			weekday = currData.get(Calendar.DAY_OF_WEEK);
		}
		
		//print weekday names
		String[] weekdayNames = new DateFormatSymbols().getShortWeekdays();
		do{
			System.out.printf("%4s",weekdayNames[weekday]);
			currData.add(Calendar.DAY_OF_MONTH, 1);
			weekday =  currData.get(Calendar.DAY_OF_WEEK);
		}
		while(weekday!=firstDayOfWeek);
		System.out.println();
		System.out.println("1====weekday="+weekday+",firstDayOfWeek="+firstDayOfWeek);
		for(int i=1;i<=ident;i++){
			System.out.println("i="+i);
			currData.set(Calendar.DAY_OF_MONTH, 1);
		}
		do{
			//print day
			int day =currData.get(Calendar.DAY_OF_MONTH);
			System.out.printf("%3d",day);
			System.out.println("");
			//mark current day with *
			if(day == today){
				System.out.println("*");
			}else{
				System.out.println("2=====day="+day+",today="+today);
			}
			//advance currData to the next day
			currData.add(Calendar.DAY_OF_MONTH, 1);
			weekday =  currData.get(Calendar.DAY_OF_WEEK);
			//start a new line at the start of the week
			if(weekday!=firstDayOfWeek){
				System.out.println("  ");
			}
		}
		while(currData.get(Calendar.MONTH)==month);
		
		
		if(weekday!=firstDayOfWeek){
			System.out.println("  ");
		}
	}

}
