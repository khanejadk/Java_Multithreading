package com.multithreading.oldPackage;

public class ThreadDemo2 {

	public static void main(String[] args) {
		
		MyThreadClass t1 = new MyThreadClass();
		t1.start();
		
		for(int i=0; i<100000; i++) {
			System.out.println(Thread.currentThread().getName());
		}

	}

}
