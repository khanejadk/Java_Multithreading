package com.multithreading.VolatileAtomic__6;

public class VolatilePractise {

	public static void main(String[] args) {
		
		SharedResource resource = new  SharedResource();
		
		Thread writerThread = new Thread( () -> {
			
			try {
				Thread.sleep(1000);  // adding delay of 1 sec, to check if the reader thread will pick latest value of flag when writer thread set the flag as true after 1 sec delay
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
			
			resource.settingTheFlagTrue();
		});
		
		Thread readerThread = new Thread( () -> {
			
			resource.printIfFlagTrue();
		});
		
		readerThread.start();
		writerThread.start();
		

	}

}

class SharedResource {
	
	private volatile boolean flag = false;
	
	public void settingTheFlagTrue() {
		
		System.out.println("Writer thread has set the flag as true");
		flag = true;
	}
	
	public void printIfFlagTrue() {
		
		while(!flag) {
			//System.out.println("Reader thread reading the flag as false still");
		}
		
		System.out.println("Flag set to true!!");
	}
}
/*
 
 Every thread keeps a local copy of the varibale in it's cache that it is using or working with. So, no matter if the original value of that variable got changed during the execution, thread
 will read the value from it's variable's copy only and that is old value. This is the default scenario of threads with variables they are using.
 
 Using volatile keyword with the variable, makes sure that the thread will pick the value of that variable from the main memory everytime instead of picking from the cache memory.
 
 Volatile usually applied on the shared resources where read and especially write operations are tend to happen on that variable.
 
 But usage of volatile keyword is limited to the simple executions only, in complex execution it won't be a good option.
 
 Volatile is just picking the value from the main memory, it does not prevent that shared variable from inconsistency if multiple thread change it's value. So, we can use AtomicInteger
 to fix that (Yeah, we can use synchronized also, but AtomicInteger can also be used to fix that issue).
 
 */
