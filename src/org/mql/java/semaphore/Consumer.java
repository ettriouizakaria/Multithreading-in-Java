package org.mql.java.semaphore;

public class Consumer  extends Thread{
	private Buffer buffer; 
	private Semaphore m,empty,full;
	public Consumer(String name,Buffer buffer,Semaphore m, Semaphore empty,Semaphore full) {
		super(name);
		this.buffer = buffer;
		this.m = m;
		this.empty = empty;
		this.full = full;
	}
	
	public void run() {
		do {
			full.lock();
			m.lock(); //verrouiller
			buffer.read(); //section critique
			m.unlock(); //devirouller
			empty.unlock(); //juste lecture
		}while(true);
	}

}
