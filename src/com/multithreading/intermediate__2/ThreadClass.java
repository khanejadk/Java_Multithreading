package com.multithreading.intermediate__2;

public class ThreadClass extends Thread{

	public CounterClass counter;
	
	public ThreadClass(CounterClass counter) {
		this.counter = counter;
	}

	@Override
	public void run() {
	
		for(int i=0; i<1000; i++) {
			counter.increment(); // this will cause race condition because this operation done by both thread parallely and will result in data inconsistency
		}
	}
	

}
