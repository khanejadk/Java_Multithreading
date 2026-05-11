package com.multithreading.executorFramework__5;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutorService__5 {

	public static void main(String[] args) {
		
		ScheduledExecutorService sEx = Executors.newScheduledThreadPool(5);

		// schedule(RunnableObj, timePeriod, timeUnit); -- passing Runnable, so only print statement and no return statement
		ScheduledFuture<?> result1 = sEx.schedule(() -> System.out.println("Task will schedule after 5 seconds of delay"), 5, TimeUnit.SECONDS); 
		
		// schedule(CallableObj, timePeriod, timeUnit); -- passing Callable, so return statement also given.
		ScheduledFuture<?> result2 = sEx.schedule(() -> "Returning a string at 3 secs delay", 3, TimeUnit.SECONDS);
		
		try {
			System.out.println(result2.get());
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		
		// scheduleAtFixedRate(RunnableObj, initialDelay, timePeriod, timeUnit); -- here initial delay also been passed
		sEx.scheduleAtFixedRate(() -> System.out.println("Task will schedule after every 4 seconds of delay"), 8, 4, TimeUnit.SECONDS);
		// here we cannot directly use the sEx.shutDown(); because an initial delay is also there, so chances are that this task cannot even queued to execute, we need proper handling
		
		// why not we schedule another task, just to shutdown the sEx service. Initial delay will also handled in that way.
		sEx.schedule(() -> {
			System.out.println("Scheduling this task to shutdown the executor after 30 secs");
			sEx.shutdown();
		},30, TimeUnit.SECONDS);
		
		
		// Another method is scheduledAtFixedDelay(RunnableObj, initialDelay, givenDelay, timeUnit);
		// basically it will start the first task after given initial delay, then next task will be after completing previous task + given delay by us
		// in scheduleAtFixedRate. what actually happend that, after initial delay first task will start and then after fixed intervals next will also start
		sEx.scheduleWithFixedDelay(() -> System.out.println("Task will schedule after every 4 seconds of previous task completion"), 8, 4, TimeUnit.SECONDS);
		
		
		// newCachedThreadPool: basically it will create new threads in a particular threadPool if all the other threads are busy doing task. Then it will not wait for them and will
		// create the new ones. But if the old threads are available it will use them. It basically used for short frequency tasks and variable load that requires faster execution.
		ExecutorService ex = Executors.newCachedThreadPool(); // there is not limit also, it can create as many threads.
		
	}

}

/*

ScheduledExecutorService is an interface in java which extends the ExecutorService interface. It is used when there is a requirement of executing the task with some delay or over
the interval of time, that is periodically.



*/