/**
 * 
 */
package com.caimao.thread;

/**
 * <p>Title: UnSynchBankTest.java</p>
 * <p>Description: </p>
 * @author caimao
 *
 * @date 2017年6月23日 下午5:27:37
 * @version 1.0
 * 
 */
public class UnSynchBankTest {

	public static final int NACCOUNTS = 100;
	public static final double INITAL_BALANCE = 1000;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Bank b = new Bank(NACCOUNTS,INITAL_BALANCE);
		int i;
		for(i=0;i<NACCOUNTS;i++){
			System.out.println("=============="+i);
			TransferRunnable r = new TransferRunnable(b, i, INITAL_BALANCE);
			Thread t = new Thread(r);
			t.start();
		}
	}

}
