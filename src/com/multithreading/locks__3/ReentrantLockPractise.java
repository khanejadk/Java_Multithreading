package com.multithreading.locks__3;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockPractise {

	private final Lock lock = new ReentrantLock();
	
	public void outerMethod() {
	
		try {
			
			lock.lock(); // firstly, a thread will take the lock. So, lock count will be one
			System.out.println("In outer method");
			innerMethod(); // with that lock it is moved to innerMethod call
			
		} catch (Exception e) {
			Thread.currentThread().interrupt();
		}finally {
			lock.unlock(); // finally lock count will be zero here
			System.out.println("outer lock unlocked");
		}
	}
	
	public void innerMethod() {
		
		try {
			
			lock.lock();  // here again, that thread will take the lock. so, lock count will be two (and it will be in the order it acquired the lock). It's Reentrant lock feature.
			System.out.println("In inner method");
			
		} catch (Exception e) {
			Thread.currentThread().interrupt();
		}finally {
			lock.unlock();  // here the second acquired lock wil get unlocked and total lock count will again be one.
			System.out.println("inner lock unlocked");
		}
	}
	
	
	// if we are not using the Reentrant lock, then it is not possible to acquire two locks at the same time for a thread and that will cause the deadlock condition.
	// Deadlock condition is that, where two thread waiting for each other to acquire and release the lock on a shared resource, indefinitely.
	// But Reentrant lock provide the multiple locking and unlocking by a thread and that too in an ordered manner.
	
	public static void main(String[] args) {
		
		ReentrantLockPractise obj = new ReentrantLockPractise();
		
		obj.outerMethod();
		
		// there is one more method as lock.lockInterruptibly, which basically indicates the other thread that stop waiting to acquire the lock of that resource as of now and try again
		// first the original thread which has the lock, will be having it's interrupt() call somewhere in the code and then when another thread try to get the lock, the above method
		// will indicate that stop waiting for the lock
		
		Lock lock = new ReentrantLock();
		
		try {
			
			lock.lockInterruptibly(); // indication to other threads to stop waiting
			
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		
		// Fairness of thread with respect to lock
		
		Lock fairLock = new ReentrantLock(true); // here true insures the fairness of lock towards all the threads
		
		// suppose 20 thread are working on some resource and there are chances that 1-2 thread might not able to get the chance to access the resource, that will be unfair condition.
		// but if we apply the true in lock, then every 20 threads will get the access to that resource. However, the order of access will be decided by OS/CPU only.
		
		// in unfair scenario, if a thread is not able to access the resource at all, then thing is called as thread starvation.
		
		
		// Drawbacks of Synchronized: No Fairness, Indefinite blocking of resource, No lockInterruptibly, No Read/Write locking understanding

	}

}
