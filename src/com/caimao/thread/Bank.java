/**
 * 
 */
package com.caimao.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

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
//	private Lock bankLock;
//	private Condition sufficientFunds;
	/**
	 * Constructs the bank
	 * @param n the number of accounts
	 * @param initialBalance the inital balance for each account
	 */
	public Bank(int n,double initalBalance) {
		accounts = new double[n];
		for(int i=0;i<accounts.length;i++){
			accounts[i] = initalBalance;
//			bankLock = new ReentrantLock();
//			sufficientFunds = bankLock.newCondition();
		}
	}
	/**
	 * transfer money from one account to another account
	 * @param from the account to transfer from
	 * @param to the account to transfer to
	 * @param amount the amount to transfer
	 * @throws InterruptedException 
	 */
	public synchronized void transfer(int from,int to,double amount) throws InterruptedException{
//		bankLock.lock();
		try {
			while(accounts[from]<amount)
				wait();
//			sufficientFunds.await();
			System.out.println(Thread.currentThread()+"---"+Thread.currentThread().getState());
			accounts[from]-=amount;
			accounts[to]+=amount;
			System.out.println(Thread.currentThread()+",amount="+amount+",from="+from+",to="+to);
			System.out.println(Thread.currentThread()+",Total Balance:"+getTotalBalance());
		} finally{
//			bankLock.unlock();
			notifyAll();
		}
	}
	
	/**
	 * gets the sum of accounts balances
	 * return the total balance
	 */
	public synchronized double getTotalBalance(){
//		bankLock.lock();
		double sum;
		try {
			sum = 0;
			for(double a : accounts){
				sum += a;
			}
			return sum;
		}  finally{
//			bankLock.unlock();
			notifyAll();
		}
	}
	
	/**
	 * gets the number of accounts in the bank
	 * return the number of accounts
	 */
	public int size(){
		return accounts.length;
	}

}
