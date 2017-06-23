/**
 * 
 */
package com.caimao.thread;

/**
 * <p>Title: Bank.java</p>
 * <p>Description: </p>
 * @author caimao
 *
 * @date 2017年6月23日 下午4:55:39
 * @version 1.0
 * 
 */
public class Bank {

	private final double[] accounts;
	/**
	 * Constructs the bank
	 * @param n the number of accounts
	 * @param initialBalance the inital balance for each account
	 */
	public Bank(int n,double initalBalance) {
		accounts = new double[n];
		for(int i=0;i<accounts.length;i++){
			accounts[i] = initalBalance;
		}
	}
	/**
	 * transfer money from one account to another account
	 * @param from the account to transfer from
	 * @param to the account to transfer to
	 * @param amount the amount to transfer
	 */
	public void transfer(int from,int to,double amount){
		if(accounts[from]<amount){
			return;
		}
		System.out.println(Thread.currentThread());
		System.out.println(Thread.currentThread().getState());
		accounts[from]-=amount;
		System.out.println(Thread.currentThread()+",amount="+amount+",from="+from+",to="+to);
		accounts[to]+=amount;
		System.out.println(Thread.currentThread()+",Total Balance:"+getTotalBalance());
	}
	
	/**
	 * gets the sum of accounts balances
	 * return the total balance
	 */
	public double getTotalBalance(){
		double sum = 0;
		for(double a : accounts){
			sum += a;
		}
		return sum;
	}
	
	/**
	 * gets the number of accounts in the bank
	 * return the number of accounts
	 */
	public int size(){
		return accounts.length;
	}

}
