package com.multithreading.executorFramework__5;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class FurutePractise__3 {

	public static void main(String[] args) throws InterruptedException, ExecutionException {

		ExecutorService ex = Executors.newSingleThreadExecutor();  // gives only single thread.
		
		Future<?> future = ex.submit(() -> System.out.println("Deepak")); // no return value, just printing the statement.
		
		Future<Integer> future1 = ex.submit(() -> 42); // here it is returning Integer value
		
		Future<String> future2 = ex.submit(() -> "Deepak"); // here it is returning String value
		
		// but we all know that Runnable uses void run() method and there is no return type for run method. That means submit() uses some other thing than the Runnable object.
		
		// it is using the Callable object and internally Callable is a functional interface that has V call() mehtod and it has a return type and also return some value.
		
		Callable<String> callable = () -> "Deepak Khaneja";
		Future<String> future3 = ex.submit(callable); // here we directly passed the callable object in the submit argument
		
		// Runnable: No return type, use run() method, no exception can be thrown (need to use try catch only)
		// Callable: Can return something, use call() method, can throw exception (V call() throws Exception)
		
		// There is one more submit(Runnable task, V result) method also other than submit with Runnable and submit with Callable
		Future<String> result = ex.submit(() -> System.out.println("printing something"), "Deepak"); // sending runnable task and getting String value in future and will require get method to get the value.
		ex.shutdown();
		
		result.isDone();
		
		try {
			
			future.get();  // here the execution will block untill the above task get done and get() method will fetch the value or make sure the exection is completed.
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		
		future.isDone();    // return true if the task has been completed due to any reason (success or failure both).
		future.isCancelled(); // return true if the task has been cancelled before the completion.
		future.cancel(true); // It will cancel the task if task not started. But if it is started then it try to cancel the task by interrupting it.
		future.cancel(false); // It will cancel the task if task has not started yet. But if it is already started then it will not try to cancel it. In both the cases, isCancelled will return true
		try {
			
			future.get(100, TimeUnit.MILLISECONDS); // get method with timeout. It will wait till that time then throw timeout exception
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		} catch (TimeoutException e) {
			e.printStackTrace();
		}
	}

}
