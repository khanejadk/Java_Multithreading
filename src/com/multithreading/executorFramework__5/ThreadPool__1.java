package com.multithreading.executorFramework__5;

public class ThreadPool__1 {

	public static void main(String[] args) {

		long startTime = System.currentTimeMillis(); // start time before any execution
		
		Thread[] threads = new Thread[9]; // we are manually creating the thread pool of 9 for doing 9 factorials.
		
		for(int i=1; i<10; i++) {
			
			int newI = i;  // effectively final statement to use with lambda expression so that latest value of i can be picked.
			
			threads[newI - 1] = new Thread(() ->{
				long result = factorial(newI);
				System.out.println(result);
				});
			threads[newI - 1].start(); // starting all the threads.
		}
		
		for(Thread thread : threads) {
			try {
				thread.join();                 // using join method for all 9 threads so that main thread could wait for all then execute last print statement
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("Total time taken: " + (System.currentTimeMillis() - startTime)); // total time taken.

	} // in total, we are manually creating all the threads, staring it manually and joining them. This is very hectic in big complex things, so we require executor framework
	
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

/*
Thread Pool is the collection of pre initialized threads that are ready to perform the task. Instead of creating thread one by one, you just created a pool with fix number of threads
or may not be fixed in some cases, and all these threads will divide the task between them and complete it.

Benefits:
1. Resource management (creation and descturction of thread in an optimized manner)
2. Response time (no need to waste time by creating thread one by one, you already have a fixed set of threads here)
3. Control over thread count (you already have the idea how many threads are created and you will destroy them. Not like, you do not have any idea that thread has been destroyed or not)


----->>>

Executor Framework:

It is a multithreading framework in java that is created to simplify the concurrent working in an application. It basically, hides(abstracts) the complexity of creating and managing
threads. It has been introduced in Java 5.

It solves the following problems:
Thread resource management, scalability, memory overhead, resposne time, error handling


*/