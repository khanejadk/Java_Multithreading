package com.multithreading.executorFramework__5;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CountDownLatch__6 {

	public static void main(String[] args) {

		int numberOfTasks = 5;
		CountDownLatch latch = new CountDownLatch(numberOfTasks); // It basically holds the number of thread till the count down become zero
		
		ExecutorService ex = Executors.newFixedThreadPool(numberOfTasks);
		
		ex.submit(new DependentService(latch)); // with every count down the next task will be picked up and upon completion, count down again decremented by latch.
		ex.submit(new DependentService(latch));
		ex.submit(new DependentService(latch));
		ex.submit(new DependentService(latch));
		ex.submit(new DependentService(latch));
		
		try {
			
			latch.await(); // it's purpose is to hold the main thread untill the countdown of the latch become zero. Once countdown is zero then it will release the main thread
			
			latch.await(100, TimeUnit.MILLISECONDS); // can also give a particular time, till that time main thread will be at hold and after that it will get released
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("Main thread resume it's further execution");
		
		ex.shutdown();

	}

}

class DependentService implements Callable<String> {
	
	private final CountDownLatch latch;  // latch as a class variable
	
	public DependentService(CountDownLatch latch) {  // constructor
		this.latch = latch;
	}
	
	
	public String call() throws Exception {  // call method
		
		try {
			
			Thread.sleep(1000);
			System.out.println(Thread.currentThread().getName() + " Service started");
			
		} finally {
			latch.countDown(); // it will start the countdown from 5 to 0 and then release all the waiting threads and then main thread starts executing further.
		}
		
		return "ok";
	}
}

/*

when we need to perform suppose 10 tasks and we initiated ex.submit(); 10 times by passing Callable tasks and every task result will be taken in Future object.
Now that future object we will perform future.get(); 10 times and we already know that get() method blocks the main thread exectuion unitll the task result received.

So, to fixing that delay, or to not to things manually 10 times or many times writing the future.get() method, we will use the CountDownLatch.

CountDown is basically counting till a fixed number and Latch is basically a door open. So, it will hold the main thread till the countDownLatch number ( any integer) and after
all the countdown has been completed, it will un hold the main thread and main thread will start executing again

If we have 30 tasks to perform then we will create a countDownLatch of 30 and will do our execution without manually doing all the things. 

Basically, all the 30 threads will start executing their task parallely. The moment a thread completes it's execution then latch will decrement the countdown by 1 and all these
thing is done in paralley and no thread is waiting for anyone.

latch can also works with the manual thread creation and usage, not just limited to use with the Executors only.

CountDownLatch is not reusable, once it value reaches zero then it cannot be reset to any number. New latch need to be used next time.

Though we cannot use CountDownLatch again but instead we can use Cyclic barrier. Mutilpe threads will come to a point (barrier) and wait for other threads there. Once all threads 
reaches that point then all the threads will get released.
*/