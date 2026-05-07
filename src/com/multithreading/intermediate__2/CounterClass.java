package com.multithreading.intermediate__2;

public class CounterClass {

	private int count = 0;
	
	// here this method will cause the race condition because both are doing write operations and result will be abnormal so we will use synchronized keyword to fix that
	public void increment_withoutSynchronized() {
		
		count++;
	}


	// synchronized keyword will block that resource for a particular thread, once that thread completes it task then only other thread will get the access to it.
	public synchronized void increment() {
		
		count++;
	}
	
	// We can use synchronized keyword with methods and with blocks only.
	public synchronized void increment_synchBlock() {
		
		synchronized (this) { // if not with full method then we can just use the synchronized with on that particular part also, where actual data change is hapenning.
			count++;
		}
		
	}
	
	public int getCount() {
		return count;
	}

}