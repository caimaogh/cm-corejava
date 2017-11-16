/**
 * 
 */
package com.caimao.thread;

/**
 * <p>Title: SimpleThreads.java</p>
 * <p>Description: </p>
 * @author caimao
 *
 * @date 2017年11月14日 上午9:40:36
 * @version 1.0
 * 
 */
public class SimpleThreads {
	 // Display a message, preceded by the name of the current thread 
	static void printMsg(String message){
		System.out.println("1ThreadName:"+Thread.currentThread().getName()
				+";1ThreadState:"+Thread.currentThread().getState()
				+",message="+message);
	}
	
	private static class MessageLoop implements Runnable{

		@Override
		public void run() {
			String[] importantMsg = {"Java", "PHP", "Python", "C++"};
			for(int i = 0; i<importantMsg.length; i++){
				try {
					Thread.sleep(4000);
					printMsg(importantMsg[i]);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					printMsg("Error:"+e);
				}
			}
			
		}
		
	}

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		long patience = 1000 * 10 ;
		if(args.length > 0){
			patience = Long.parseLong(args[0]) * 1000;
		}
		printMsg("Strating MessageLoop thread");
		long startTime = System.currentTimeMillis();
		Thread thread = new Thread(new MessageLoop());
		System.out.println("2ThreadName:"+thread.currentThread().getName()+";ThreadState:"+thread.currentThread().getState());
		String threadName = thread.currentThread().getName();
		thread.start();
		System.out.println("3ThreadName:"+thread.currentThread().getName()+";ThreadState:"+thread.currentThread().getState());
		printMsg("Waiting for MessageLoop thread to finish");
		while(thread.isAlive()){
			printMsg("4ThreadName:"+threadName+";Still waiting......");
			thread.join(5000);
			System.out.println("4ThreadName:"+Thread.currentThread().getName()
					+";4ThreadState:"+Thread.currentThread().getState());
			if(System.currentTimeMillis() - startTime > patience
					 && thread.isAlive()){
				printMsg("Tired of waiting!");
				thread.interrupt();
				thread.join();
				System.out.println("6ThreadName:"+thread.currentThread().getName()+";ThreadState:"+thread.currentThread().getState());
			}
				
		}
		printMsg("Finally");
	}

}
