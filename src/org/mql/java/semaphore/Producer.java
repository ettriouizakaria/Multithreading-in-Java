package org.mql.java.semaphore;

public class Producer extends Thread{
	private Buffer buffer; 
	private Semaphore m,empty,full;
	public Producer(String name,Buffer buffer,Semaphore m, Semaphore empty,Semaphore full) {
		super(name);
		this.buffer = buffer;
		this.m = m;
		this.empty = empty;
		this.full = full;
	}
	
	public void run() {
		do {
			int item= 1000 + (int)(Math.random()* 9000);
			empty.lock();
			m.lock(); //verrouiller
			buffer.write(item); //section critique
			m.unlock(); //deverouiller
			full.unlock(); //juste lecture
		}while(true);
	}

}
