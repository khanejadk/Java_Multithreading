package com.multithreading.ThreadCreation__1;

public class ThreadDemo3 {

	public static void main(String[] args) {
		
		ThreadRunnableClass runableObj = new ThreadRunnableClass();
		Thread td = new Thread(runableObj); //New-state
		
		td.start(); //Runnable-state
		//Running-state
		
		for(; ;) {
			System.out.println(Thread.currentThread().getName());
		}

		////terminated-state
		//td.getState();
	}

}

/*

Runnable object banakar thread instance ko pass kardo simple and then threadInstance.start();

*/