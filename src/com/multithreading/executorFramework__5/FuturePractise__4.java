package com.multithreading.executorFramework__5;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class FuturePractise__4 {

	public static void main(String[] args) throws ExecutionException, InterruptedException {

		ExecutorService ex = Executors.newFixedThreadPool(2);
		
		Callable<Integer> c1 = () -> {
			System.out.println("Callable 1");
			return 1;
		};
		
		Callable<Integer> c2 = () -> {
			System.out.println("Callable 2");
			return 2;
		};
		
		Callable<Integer> c3 = () -> {
			System.out.println("Callable 3");
			return 3;
		};
		
		List<Callable<Integer>> callableList = Arrays.asList(c1, c2, c3);
		
		try { 
			
			List<Future<Integer>> futures = ex.invokeAll(callableList); // it will give the list of completed tasks.
			
			// invokeAll basically taking the collection(list) of task and executing it together and then return a list of futures having some result (when all tasks completed)
			// isDone method will be true for all the task in the list when the futures will return
			
			for(Future<Integer> f: futures) {
				f.get();
			}
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			ex.shutdown();
		}
		
		System.out.println("Everything is completed now... :)"); // using invokeAll, main thread will wait till the whole execution and then at the end this print statement will execute

		
		// there is one more invokeAll with different method signature as invokeAll(list of callables, timeout)
		List<Future<Integer>> futuress = ex.invokeAll(callableList, 100, TimeUnit.MILLISECONDS);  // here it will wait till that time and return the result irrespective of all the tasks completed or not
		
		// invokeAny: It will start executing all the task and as soon as first task completed successfully, it will return that value and cancelled all the remaining tasks.

		Integer result = ex.invokeAny(callableList); // here it will not return the Future but the direct datatype. Also, all the thread will start executing the task, as soon as first task gets completed, it will return that value irrespective of other threads and other tasks.
		
		try {
			Integer result1 = ex.invokeAny(callableList, 100, TimeUnit.MILLISECONDS); // here same, give the first completed task, but that too if completed in given time, otherwise no result given.
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		} catch (TimeoutException e) {
			e.printStackTrace();
		}
	}

}
