/**
 * 
 */
package com.caimao.thread;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p>测试SimpleDateFormat是否线程安全</p>
 * <p>SimpleDateFormat是线程不安全的,其父类 DateFormat有一个成员变量protected Calendar calendar;
 *    calendar是在 format 和 parse 时用的。另外，因为 calendar 作为一个成员变量，在多线程场景下，
 *    会发生资源共享造成前后不一致的问题。这就是 SimpleDateFormat 是线程不安全的原因。
 * </p>
 * @author caimao
 *
 * @date 2017年11月16日 下午2:00:33
 * @version 1.0
 * 
 */
public class SimpleDateFormatTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/*Date formats are not synchronized.
         * It is recommended to create separate format instances for each thread.
         * If multiple threads access a format concurrently, it must be synchronized externally.
         */
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = TimeUtil.formatDate(new Date(), TimeUtil.YEAR_MONTH_DAY);
		String dateTime = "2017-11-16 23:59:01";
		for(int i = 0; i < 5; i++){
			new Thread(new Runnable(){
				@Override
				public void run() {
					for(int i = 0; i < 5; i++){
						try {
							System.out.println("thread: "+Thread.currentThread().getName()+
									",state: "+Thread.currentThread().getState()+
									",date: "+date);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					
				}{
				
			}
			}).start();
		}
	}

}
