package com.caimao.basic;

import java.util.Calendar;
import java.util.Date;

public class DateTimeTest {

    public static void main(String[] args){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        Date zero2 = calendar.getTime();
        System.out.println((zero2));
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date zero = calendar.getTime();
        System.out.println((zero));
    }
}
