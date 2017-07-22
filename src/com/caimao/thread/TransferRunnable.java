/**
 * 
 */
package com.caimao.thread;

/**
 * <p>Title: TransferRunnable.java</p>
 * <p>Description: a runnable that transfers money from an account to another account in a bank</p>
 * @author caimao
 *
 * @date 2017年6月23日 下午5:13:33
 * @version 1.0
 * 
 */
public class TransferRunnable implements Runnable {

	private Bank bank;
	private int fromAccount;
	private double maxAmount;
	private int DELAY = 10;
	/**
	 * Constructs the TransferRunnable
	 * @param b the bank between whose account money is transferred
	 * @param from  the account to transfer money from
	 * @param max  the maximum amount of money in each transfer
	 */
	public TransferRunnable(Bank b,int from,double max) {
		bank = b;
		fromAccount = from;
		maxAmount = max;
	}

	public void run() {
		try {
			while(true){
				int toAccount = (int) (bank.size()*Math.random());
				double amount =  (int) (maxAmount*Math.random());
				bank.transfer(fromAccount, toAccount, amount);
				Thread.sleep((int) (DELAY*Math.random()));
			}
		} catch (InterruptedException e) {
			System.out.println("error:"+e);
		}

	}

}
