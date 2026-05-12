package com.multithreading.VolatileAtomic__6;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegterPractise {

	public static void main(String[] args) {
		
		Counter counter = new Counter();
		
		Thread t1 = new Thread( () -> {
			
			for(int i=0; i<1000; i++) {
				counter.increment();
			}
		});
		
		Thread t2 = new Thread( () -> {
			
			for(int i=0; i<1000; i++) {
				counter.increment();
			}
		});
		
		t1.start();
		t2.start();

		try {
			t1.join();
			t2.join();
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println(counter.getCount());
	}

}

class Counter {
	
	//private volatile int count = 0;
	
	// counter is thread safe now
	private AtomicInteger count = new AtomicInteger(0);  // 0 = initial value 
	
	public void increment() {
		
		// count++;
		count.incrementAndGet(); // increment and then get the value
	}
	
	public int getCount() {
		
		// return count;
		return count.get(); // get the value
	}
}

/*

Java has given some classes using which we can achieve atomicity (access to a resource one thread at a time) in the programme without using the synchronized and locks.

Some classes are as AtomicInteger, AtomicBoolean, AtomicLong etc....

AtomicInteger and all is basically a thread safe class
*/