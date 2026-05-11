package com.multithreading.executorFramework__5;

import java.util.concurrent.Callable;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CyclicBarrier__7 {

	public static void main(String[] args) {
		
		int numberOfTasks = 5;
		
		// Cyclic barrier -- provides reUsability
		
		CyclicBarrier barrier = new CyclicBarrier(numberOfTasks);
		
		ExecutorService ex = Executors.newFixedThreadPool(numberOfTasks);
		
		ex.submit(new SharedService(barrier));
		ex.submit(new SharedService(barrier));
		ex.submit(new SharedService(barrier));
		ex.submit(new SharedService(barrier));
		ex.submit(new SharedService(barrier));
		
		System.out.println("Main thread resume it's execution parallely with the user threads");
		
		barrier.reset(); // reset the barrier to it's initial stage, if the parties are waiting already then it will throw some exception and then reseted.
		
		barrier.getParties(); // it is basically total parties or threads == numberOfTasks
		
		barrier.getNumberWaiting(); // will give the total threads/parties waiting at the barrier
		
		
		ex.shutdown();
	}

}


class SharedService implements Callable<String> {
	
	private final CyclicBarrier barrier;  // barrier as a class variable
	
	public SharedService(CyclicBarrier barrier) {  // constructor
		this.barrier = barrier;
	}
	
	
	public String call() throws Exception {  // call method
		
		System.out.println(Thread.currentThread().getName() + " Service started");
		Thread.sleep(1000);
			
		System.out.println(Thread.currentThread().getName() + " is waiting for the barrier");
			
		barrier.await(); // make sure all the thread will reach at the barrier point, till than it will await and once all are reached it will then start their task execution

		return "ok";
	}
}

/*

CountDownLatch is not reusable, once it value reaches zero then it cannot be reset to any number. Though we cannot use CountDownLatch again but instead we can use Cyclic barrier.

In Cyclic barrier, Mutilpe threads will come to a point (barrier) and wait for other threads there. Once all threads reaches that point after then only they will start executing 
furhter their tasks otherwise till the last thread, all will waiting at this barrier point only. 

It does not hold the main thread, so main execution will be on parallel, and not after the userThreads task completion.

Unlike latch, it can be reset and start from a new.

*/