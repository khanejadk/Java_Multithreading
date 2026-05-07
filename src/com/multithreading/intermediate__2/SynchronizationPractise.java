package com.multithreading.intermediate__2;

public class SynchronizationPractise {

	public static void main(String[] args) {

		CounterClass counter = new CounterClass();
		
		ThreadClass t1 = new ThreadClass(counter);
		
		ThreadClass t2 = new ThreadClass(counter); // here both the thread are using the shared resource that is the counter instance and increment property of that class in run method.
		
		t1.start();
		t2.start();
		
		try {
			
			t1.join();
			t2.join();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println(counter.getCount());

	}

}

/*

Race condition : It is a condition where mutliple threads access the shared resource and we get inconsistency in our result due to multiple changes on a single shared resource.

Critical section : That part of code where actual write operation / execution happening that could cause race condition.

Mutual Exclusion : Making sure that one thread at a time get to access the critical section of the programme.

In synchronization, basically lock will applied on the shared resource for a partiuclar thread. Once, that thread is done with it's execution then lock will be released and other thread
will be given the resource and same lock will be applied for it.

Locks are of two types: intrinsic and explicit.

Inrinsic lock: In Java, all objects have their own lock and when we use the synchronized keyword then those automatics locks will be used.

Explicit lock: It is more advanced locks and we can set it manually and unlock it too. Basically, they are not automatic. Provide much more better control over synchronized.

Why Lock comes into picture when synchronized is already there?
Answer: Because, suppose there is an operation happening where thread are updating a bank account balance after withdrawal of amount and after this operation ends then other thread,
will get to do the next operation because of synchronized keyword used. And, if by any chance that first thread operation takes 10 sec or 1 min or indefinite time due to any reason,
then next thread will be waiting for forever. So, we require locks to do flexiblity in execution.



*/