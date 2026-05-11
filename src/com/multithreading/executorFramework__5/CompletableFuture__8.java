package com.multithreading.executorFramework__5;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CompletableFuture__8 {

	public static void main(String[] args) {
		
		CompletableFuture<String> cF = CompletableFuture.supplyAsync( () -> "Deepak Khaneja"); // passing a supplier in supplyAsynch. It will return a new CF with task completed in asycnch mode.
		
		try {
			cF.get(); // block the main thread execution till it get the result
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		
		cF.getNow("DefaultValue"); // get the result very same moment. If original result is computed then return that result otherwise default one will return.
		
		
		CompletableFuture<Integer> cF1 = CompletableFuture.supplyAsync(() -> {
			System.out.println("in second CF");
			return 36;
		});

		CompletableFuture<Void> masterCF = CompletableFuture.allOf(cF, cF1); // a new CF that is combination of completed (executed) CFs
		masterCF.join();
		
		// It is same as streams in terms of functional programming. We can use method after method (supply, accept, apply -- all the streams learning methods)
		CompletableFuture<Void> res = CompletableFuture.supplyAsync(() -> {
		    return "Hello";
		}).thenApply(x -> x.toUpperCase()).thenAccept(result -> {
		    System.out.println(result);
		});
		
		//res.get();
		
		// By default CF tasks run on daemon thread due to use of ForkJoinPool.commonPool, but we can also give  user thread by using executor.
		ExecutorService ex = Executors.newFixedThreadPool(3);
		
		CompletableFuture.supplyAsync(() -> 42, ex); // we can pass executor also in the supplyAsynch and will use the threads from the executor pool
		
		
		System.out.println("Main thread method");
	}

}

/*

CF introduced in Java 8 and it is used in ansynchronous programming. (Asynchronous == non blocking programming)

It is a class in Java that implements the Future interface. It basically solves Future issues and provides much more flexibility for asynch programming.

Benefits:
1. Asynch programming
2. Callbacks -- if one task return result then pass this on task 2 and then pass overall on task 3 (all these are in asynch mode)
3. blocking by get() method
4. combining tasks

*/