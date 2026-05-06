package com.multithreading.ThreadCreation__1;

public class MyThreadClass extends Thread{

	@Override
	public void run() {
		
		for(int i=0; i<10; i++) {
			System.out.println(Thread.currentThread().getName());
		}
	}
	
	public MyThreadClass(String name) {
		super(name);
	}
	
}
