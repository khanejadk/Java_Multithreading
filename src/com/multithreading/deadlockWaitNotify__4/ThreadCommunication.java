package com.multithreading.deadlockWaitNotify__4;

public class ThreadCommunication {

	public static void main(String[] args) {
		
		SharedResource resource = new SharedResource();
		
		Thread producerThread = new Thread(new Producer(resource));
		Thread consumerThread = new Thread(new Consumer(resource));
		
		producerThread.start();
		consumerThread.start();

	}

}

class SharedResource {
	
	private int data;
	private boolean hasData;
	
	public synchronized void produceData(int value) {
		
		while(hasData) { // if already data is there then we will ask the producerThread to wait till the data become empty
			try {
				wait();
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}
		
		data = value;
		hasData = true; // data has been produced, so, setting flag as true
		System.out.println("Producer produced: " + value);
		notify(); // here notify to consumer that data has been produced
	}
	
	public synchronized int consumeData() {
		
		while(!hasData) {
			try {
				wait();
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}
		
		hasData = false; // data has been consumed so, setting flag as false
		System.out.println("Consumer consumed: " + data);
		notify(); // here to notify producer that data has been consumed and he can wake up now and produce more data
		return data; //if we use notifyAll() then all the threads in the pool (here two only producer and cosumer) will wake up for taking the resource
		
	}
}

class Producer implements Runnable{

	private SharedResource res;
	
	public Producer(SharedResource res) {
		this.res = res;
	}
	
	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
            res.produceData(i);
        }
		
	}
	
}

class Consumer implements Runnable {

private SharedResource res;
	
	public Consumer(SharedResource res) {
		this.res = res;
	}
	
	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
            int value = res.consumeData();
        }
	}
	
}

/*

In a multithreading environment, threads communicating with each other to ensure proper utilizing of them and CPU efficiency.
If they do not communicate then there will be CPU utilization wastage chances and deadlock can also happens. Because they will be always in busy and waiting stages.

Because CPU time will get waste in continous checking of thread, are they working efficiently or not.

In producer and consumer scenario, consumer always needs to wait for the producer to produce the data. What if, producer itself notify the consumer when the data will produce then?
CPU time will not get waste and efficiency will be there.

We can use three methods to ensure proper thread inter communication: wait, notify and notifyAll. All three methods will be run inside synchronized block or method only.

wait(): asks a thread to release the lock and wait untill notify or notifyAll will not be called to use that thread again
notify(): wakes up a single thread from the waiting state
notifyAll(): wakes up all the thread from the waiting state to start working.

*/