package com.multithreading.locks__3;

public class LockPractise_usingSynch {

	public static void main(String[] args) {
		
		BankAccount_usingSynch sbi = new BankAccount_usingSynch(200);
				
		Runnable task = new Runnable() {
			
			@Override
			public void run() {
				sbi.withdrawAmount(50);
			}
		};
		
		Thread t1 = new Thread(task, "Thread-1");
		Thread t2 = new Thread(task, "Thread-2");
		
		t1.start();
		t2.start();
		
		try {
			
			t1.join();
			t2.join();
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println(sbi.getBalance());

	}

}
