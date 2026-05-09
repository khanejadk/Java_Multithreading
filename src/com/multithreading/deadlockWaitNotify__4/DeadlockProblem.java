package com.multithreading.deadlockWaitNotify__4;

public class DeadlockProblem {

	public static void main(String[] args) {

		Pens pen = new Pens();
		Papers paper = new Papers();
		
		Thread t1 = new Thread(new Task1(pen, paper), "Thread-1");
		Thread t2 = new Thread(new Task2(paper, pen), "Thread-2");
		
		t1.start();
		t2.start();

	}

}

class Pen {
	
	public synchronized void writeWithPenAndPaper(Papers paper) {
		System.out.println(Thread.currentThread().getName() + " is using pen " + this + " and try to use the paper " + paper);
		paper.finishWithWriting(); //here pen is depending on the paper to finish the writing
	}
	
	public synchronized void finishWithWriting() {
		System.out.println(Thread.currentThread().getName() + " is finished writing using pen" + this);
	}
}

class Paper {
	
	public synchronized void writeWithPaperandPen(Pens pen) {
		System.out.println(Thread.currentThread().getName() + " is using paper " + this + " and try to use the pen " + pen);
		pen.finishWithWriting(); // in return, the paper is also dependent on the pen to finish the writing
	}
	
	public synchronized void finishWithWriting() {
		System.out.println(Thread.currentThread().getName() + " is finished writing using paper" + this);
	}
}

// both are dependent on each other to finsish the writing task but we are using synchronized and lock will be created by the thread
// First t1 will start and acquire the lock on Pen's object becoz synchronized is used with writeWithPenAndPaper method. Then it will go to paper's finishWithWriting method.
// here it will try to acquire the lock on paper object also. But paper lock is with t2 as it is also started at the same time and already acquire the lock on Paper becoz of
// writewithPaperAndPen method, so t1 dependent on t2 for paper lock but it already has pen's lock

// now t2 move to pen's finishWithWriting method and thus it will require the pen's lock but pen's lock is alread with t1.

// so t1 dependent on t2 and in return, t2 is dependent on t1. That's the deadlock buddy

class Task1 implements Runnable {

	private Pens pen;
	private Papers paper;
	
	public Task1 (Pens pen, Papers paper) {
		this.pen = pen;
		this.paper = paper;
	}
	
	@Override
	public void run() {
		pen.writeWithPenAndPaper(paper); // locking pen and tries to acquire lock on paper also
	}
}

class Task2 implements Runnable {

	private Papers paper;
	private Pens pen;
	
	public Task2 (Papers paper, Pens pen) {
		this.paper = paper;
		this.pen = pen;
	}
	
	@Override
	public void run() {
		paper.writeWithPaperandPen(pen); // locking paper and tries to acquire lock on pen also
	}
}

/* 

Deadlock is a condition where a thread dependent on other thread to provide the resource and in return the other thread is also dependent on the first thread for the resource.

Four conditions required for deadlock situatio:
1. Mutual exclusion: one thread at a time get the access of a particular resource.
2. Hold and Wait: A thread actually holding a resource and waiting for other resource also.
3. No Preemption: A thread cannot forcefully take the resource from other thread.
4. Circular wait: threads must be waiting for each other for resource.

Solution for deadlock: Lock should be acquire in an consistent order.




*/