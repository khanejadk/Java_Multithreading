package com.multithreading.locks__3;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BankAccounts_usingLock {

	private int balance = 200;
	
	private final Lock lock = new ReentrantLock();
	
	public void withdrawalAmount(int amount) {
		
		try {
			
			System.out.println(Thread.currentThread().getName() + " is attempting to withdraw amount - " + amount);
			
			if(lock.tryLock(1000, TimeUnit.MILLISECONDS)) {
				
				if(balance >= amount) {
					
					try {
						
						Thread.sleep(1000);
						System.out.println(Thread.currentThread().getName() + " Withdrawing the money rupees: " + amount);
						
						balance = balance - amount;
						System.out.println(Thread.currentThread().getName() + " Remaining balance is: " + balance);
						
					} catch (Exception e) {
						Thread.currentThread().interrupt(); // it is a good practise to interrupt the thread in catch so that shutdown operation will get performed otherwise lost
					} finally {
						lock.unlock(); // do not forget to unlock the thread
					}
					
				}else {
					System.out.println(Thread.currentThread().getName() + " Insufficient balance");
				}
			}else {
				System.out.println(Thread.currentThread().getName() + " not able to acqurie the lock, will try again later");
			}
			
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}
}

/*

Lock is an interface in java and ReentrantLock is it's implementation class.

now lock instance above, has different method and they provide much more flexibility while working with the threads.

.lock();
It is like synchronized only. It will try to acquire lock and if not then it will wait indefinitely to get the lock. --- lock.lock();


.tryLock(); | .tryLock(time, timeUnit);
It basically try to acquire the lock if it is not already taken by the other thread. Return true if acquired and immediately return false if not able to acquire lock.
while other one is for timeout. It will try to acquire the lock just for the given particular time and then it will return false.

.unlock();
It will release the lock that it has acquired before.

*/