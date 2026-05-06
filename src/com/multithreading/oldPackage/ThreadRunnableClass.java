package com.multithreading.oldPackage;

public class ThreadRunnableClass implements Runnable{

	@Override
	public void run() {
		for(; ;) {
			System.out.println(Thread.currentThread().getName());
		}
		
	}

}
