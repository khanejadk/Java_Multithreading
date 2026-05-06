package com.multithreading.oldPackage;

public class MyThreadClass extends Thread{

	@Override
	public void run() {
		
		for(int i=0; i<1000000; i++) {
			System.out.println(Thread.currentThread().getName());
		}
	}
	
}
