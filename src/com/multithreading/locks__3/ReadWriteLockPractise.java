package com.multithreading.locks__3;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockPractise {

	public static void main(String[] args) {

		CounterClass counter = new CounterClass();
		
		Runnable readTask = new Runnable() {
			@Override
			public void run() {
				for(int i=0; i<10; i++) {
					System.out.println(Thread.currentThread().getName() + " reads: " + counter.getCount());
				}
			}};
			
		Runnable writeTask = new Runnable() {
			@Override
			public void run() {
				for(int i=0; i<10; i++) {
					System.out.println(Thread.currentThread().getName() + " incremented");
					counter.increment();
				}
			}};
			
		Thread readerThread1 = new Thread(readTask); // both reader 1 and 2 threads will get the access to read operations simultaneously.
		Thread readerThread2 = new Thread(readTask); 
		Thread writerThread = new Thread(writeTask); // one thread at a time for write operations.
		
		readerThread1.start();
		readerThread2.start();
		writerThread.start();
		
		// Still the sequence of execution of all three threads will be decided by CPU only. but yes, multiple reader threads can access the resource at one time, that's the point.
				
		try {
			readerThread1.join();
			readerThread2.join();
			writerThread.join();
		} catch (Exception e) {
			Thread.currentThread().interrupt();
		}
		
		System.out.println("Final count: " + counter.getCount());
	}

}

class CounterClass {
	
	private int count = 0;
	
	private ReadWriteLock readWriteLock = new ReentrantReadWriteLock(); // read write actual lock
	
	private Lock readLock = readWriteLock.readLock(); // read lock
	
	private Lock writeLock = readWriteLock.writeLock(); // write lock
	
	public void increment() {
		writeLock.lock();
		try {
			count++;
		} finally {
			writeLock.unlock();
		}
	}
	
	public int getCount() {
		readLock.lock();
		try {
			return count;
		} finally {
			readLock.unlock();
		}
	}
}

/*

ReadWriteLock is an interface in Java and ReentrantReadWriteLock class is it's implementation class.

It basically provides the functionality to distinguish between read and write operations locking by the threads which Synchronized is not able to provide.

It will allow multiple threads to do the read operations parallely as long as any write operation is not performed.

*/