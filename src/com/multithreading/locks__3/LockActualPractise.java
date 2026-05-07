package com.multithreading.locks__3;

public class LockActualPractise {

	public static void main(String[] args) {

		BankAccounts_usingLock hdfc = new BankAccounts_usingLock();
		
		Runnable task = new Runnable() {
			
			@Override
			public void run() {
				hdfc.withdrawalAmount(50);				
			}
		};
		
		Thread t1 = new Thread(task, "Thread-1");
		Thread t2 = new Thread(task, "Thread-2");
		
		t1.start();
		t2.start();
		
	}

}
