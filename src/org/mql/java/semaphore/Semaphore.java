package org.mql.java.semaphore;

import java.util.LinkedList;

public class Semaphore {
	private int value;
	private LinkedList<Thread> waitingThreadList; // liste de processus bloquer

	public Semaphore() {
		// djisktra propose trois primitive
		init(1); // semaphore d'exclusion mutuelle => la valuer =1; un seul valuer disponible
	}

	public Semaphore(int value) {
		// djisktra propose trois primitive
		init(value);
	}

	// primitive -- instruction atomique, on ne peut pas l'arrete
	// primitive de dijkstra
	synchronized public void init(int value) {
		this.value = value;
		waitingThreadList = new LinkedList<Thread>();
	}

	synchronized public void lock() { //==wait
		value--;
		if(value < 0) {
			sleep();
		}
	}
	
	
	synchronized public void unlock() { 
		value++;
		if(value <= 0) {
			wakeup();
		}
	}
	
	
	
	public void sleep() {

		waitingThreadList.add(Thread.currentThread());
		try {
			wait();
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
		}
		waitingThreadList.remove(Thread.currentThread());
	}
	
	public void wakeup() {
		notify();
	}
}
