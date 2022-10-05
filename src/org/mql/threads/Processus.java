package org.mql.threads;

public class Processus extends Thread {

	public Processus(String name) {
		super(name);
	}

	public void run() {
		do {
			System.out.println(Thread.currentThread().getName());
		}
		while(true);
	}
}
