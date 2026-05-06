package com.multithreading.ThreadCreation__1;

public class ThreadDemo2 {

	public static void main(String[] args) {
		
		MyThreadClass t1 = new MyThreadClass("customThread"); // NEW --state and thread name will be customThread
		
		t1.start(); // RUNNABLE -- waiting for CPU time to start executing (or maybe it is executing already, still it will show RUNNABLE only)
					// RUNNING -- started execution when CPU gave the time to the thread (there is no actual RUNNING state for thread in java, it is just for understanding)
		
		System.out.println(t1.getState()); // getting the thread state
		
		try {
			Thread.sleep(1000);               // TIMED_WAITING
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		for(int i=0; i<10; i++) {
			System.out.println(Thread.currentThread().getName()); // getting the current thread name (it is main thread actually)
		}
		
		try {
			t1.join();                        // TERMINATED (here the main thread will wait here till the t1 completes it's execution and then t1 dies and main thread continues)
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}

/*

Basically there will be two conditions for successfull working of threads.

1. Run method ki definition dedo and
2. thread ko start kardo

Now, in case of class extending the Thread class, it will override the run method and then t.start(); -- Thread will get created

But in case of class implementing the Runnable interface, then it will override the run method and now what? start is required and start will be accessible through Thread class object
So, create a thread class instance and pass the runnable object into it. Boom, you have thread instance now, just do the t.start() and thread will run 

Also, when to use Thread or Runnable. See, if a class is already extending some other class and then a thread needs to be created for that class then use Runnable and implements it
because you cannot extend two classes. Else you can use Runnable or Thread in any other scenario.
 
 
 */
