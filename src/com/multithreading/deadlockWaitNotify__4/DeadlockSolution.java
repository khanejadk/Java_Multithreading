package com.multithreading.deadlockWaitNotify__4;

public class DeadlockSolution {

	public static void main(String[] args) {
		
		Pens pen = new Pens();
		Papers paper = new Papers();
		
		Thread t1 = new Thread(new Task01(pen, paper), "Thread-1");
		Thread t2 = new Thread(new Task02(paper, pen), "Thread-2");
		
		t1.start();
		t2.start();

	}

}

class Pens {
	
	public synchronized void writeWithPenAndPaper(Papers paper) {
		System.out.println(Thread.currentThread().getName() + " is using pen " + this + " and try to use the paper " + paper);
		paper.finishWithWriting(); 
	}
	
	public synchronized void finishWithWriting() {
		System.out.println(Thread.currentThread().getName() + " is finished writing using pen" + this);
	}
}

class Papers {
	
	public synchronized void writeWithPaperandPen(Pens pen) {
		System.out.println(Thread.currentThread().getName() + " is using paper " + this + " and try to use the pen " + pen);
		pen.finishWithWriting(); 
	}
	
	public synchronized void finishWithWriting() {
		System.out.println(Thread.currentThread().getName() + " is finished writing using paper" + this);
	}
}

class Task01 implements Runnable {

	private Pens pen;
	private Papers paper;
	
	public Task01 (Pens pen, Papers paper) {
		this.pen = pen;
		this.paper = paper;
	}
	
	@Override
	public void run() {
		pen.writeWithPenAndPaper(paper);
	}
}

class Task02 implements Runnable {

	private Papers paper;
	private Pens pen;
	
	public Task02 (Papers paper, Pens pen) {
		this.paper = paper;
		this.pen = pen;
	}
	
	@Override
	public void run() {
		synchronized (pen) {
			paper.writeWithPaperandPen(pen); // here we are telling thread t2 that first take pen's lock then you can be having paper's lock.
			// so at a time t1 will have both pen and paper's lock and finish it's execution and later on t2 get the chance to access both the resource and there will be no deadlock.
		}
	}
}