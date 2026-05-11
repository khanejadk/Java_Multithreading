 package com.multithreading.executorFramework__5;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExecutorFramework__2 {

	public static void main(String[] args) {
		
		long startTime = System.currentTimeMillis(); // start time before any execution
		
		// ExecutorService is an interface that extends another interface Executor. And Executors is an utility class that provides various thread utility methods.
		ExecutorService executorSer = Executors.newFixedThreadPool(9); // more the threads, fast the execution.
		
		for(int i=1; i<10; i++) {
			
			int newI = i;
			
			executorSer.submit(() -> {    // submit takes the Runnable object like the Thread class. So we passed the lambda expression over here.
				
				long result = factorial(newI);
				System.out.println(result);
			});
			
		}
		executorSer.shutdown(); // won't take any new task after that and will try to complete already taken tasks and after that, all threads in the pool will start terminating.
		  
		// but using shutdown, it won't ensure that next print statement will execute after all the thread done their work. for that we need to use below:
		
		try {
			executorSer.awaitTermination(1000, TimeUnit.MILLISECONDS); // main thread will wait till the given time for all threads to complete their execution and then run next task.
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			e.printStackTrace();
		}
		
		// executorSer.shutdownNow(); it will forcefully shutdown the thread operation immediately. No matter, task fully completed or not.
		
		executorSer.isShutdown(); // return true if the executor has been shutdown
		executorSer.isTerminated(); // return true if all the task has been completed after shutdown called
		
		System.out.println("Total time taken: " + (System.currentTimeMillis() - startTime)); // total time taken.
		
		
		// there is one more interface(parent interface of ExecutorService) -- Executor. It has one method only that is execute and no shutdown mehtod is has
		Executor exec = Executors.newFixedThreadPool(2);
		exec.execute(() -> System.out.println("Nothing, it will just execute it and we have to manually shut it down due to no shutdown method. So, submit is far better that it."));
		
		// execute has void type and submit can return some value also because it has Future<?> datatype.

	} 
	
	public static long factorial(int n) {
		try {
            Thread.sleep(1000);               // using sleep here just to show that factorial is an extensive task and taking this much time to do the execution
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        long result = 1;
        for (int i = 1; i <= n; i++) {
            result *= i;
        }
        return result;		
	}

}
