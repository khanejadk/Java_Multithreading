package com.multithreading.ThreadCreation__1;

public class ThreadMethods_4 extends Thread{

	public static void main(String[] args) {

		// Already we studied: run, start, sleep, join
		// New methods are: setPriority, interrupt, yield, setDaemon
		
		ThreadMethods_4 t1 = new ThreadMethods_4();
		
		t1.start();
		
		// before that, Thread priority was 5 (neurtal) -- Thread-0 - thread has priority: 5 count: 0
		
		t1.setPriority(MAX_PRIORITY); // priority will be 10 now
		t1.setPriority(MIN_PRIORITY); // priority will be 1 now
		t1.setPriority(NORM_PRIORITY); // 5
		// Also there are just hints that we give to CPU to give the tasks priority, but at the end it is CPU only who will run it accordlingy
		
		
		// interrupt method basically tells a thread that please interrupt whatever you are doing. If thread is sleeping then it will wake it, if it is doing some work
			// then it will stops that work
		ThreadMethods_4 t2 = new ThreadMethods_4();
		
		t2.start();
		t2.interrupt();
		
		
		// yield method basically tells CPU that please give chance to other thread for execution, I can execute later also. But at the end again it is upto CPU only whom to execute 1st
		
		ThreadMethods_4 t3 = new ThreadMethods_4();
		ThreadMethods_4 t4 = new ThreadMethods_4();
		
		t3.start();
		t4.start();
		
		Thread.yield();
		
		// Daemon thread is basically a backgroud thread and JVM will not wait for it, to start or stop the execution. Unlike user thread for which JVM actually waits, for example -
		// if a user thread is running a loop 10000 times then JVM will wait for it's completion then it will get terminate. Garbage collector is a daemon thread
		
		ThreadMethods_4 t5 = new ThreadMethods_4();
		
		t5.setDaemon(true);
		t5.start(); // also it is not like that daemon thread will not do anything. It will be able to do it's work in BG parallely but once all user thread work is done then 
					// JVM will stop everything. So, till that point whatever work is done by daemom, it will be there only.
		
	}

	@Override
	public void run() {
		
		for(int i=0; i<5; i++) {
			System.out.println(Thread.currentThread().getName() + " - thread has priority: " + Thread.currentThread().getPriority() + " count: " + i);
		}
		
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace(); 
		}
	}

}
