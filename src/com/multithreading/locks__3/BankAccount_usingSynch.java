package com.multithreading.locks__3;

public class BankAccount_usingSynch {

	private int balance;
	
	public BankAccount_usingSynch(int balance) {
		this.balance = balance;
	}
	
	public synchronized void withdrawAmount(int amount) {
		
		System.out.println(Thread.currentThread().getName() + " is about to perform the withdrawal operation");
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		if(balance >= amount) {
			
			System.out.println(Thread.currentThread().getName() + " is withdrawing amount of rupees : " + amount);
			balance = balance - amount;
			
		}else {
			
			System.out.println(Thread.currentThread().getName() + " is not able to perform the withdrawal operation due to low balance");
		}
		
	}
	
	public int getBalance() {
		return balance;
	}
}

/* 

here, using synchronized will lock the withdrawAmount method for t1 thread and t2 will wait till it will become free.

so, next we will study the Lock interface that provides much more flexibility compares to synchronized


*/