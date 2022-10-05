package org.mql.threads.runnable;

/*
 * Comportement d'un processus
 */
public class Processus  implements Runnable {
	private Thread runner;

	public Processus(String name) {
		runner = new Thread(this,name);
	}

	
	public void run() {
		do {
			System.out.println(Thread.currentThread().getName());
		} while (true);
	}
	
	public void start() {
		runner.start();
	}
}
