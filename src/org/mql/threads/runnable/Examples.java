package org.mql.threads.runnable;

public class Examples {
	public Examples() {
		exp1();
	}
	
	void exp1() {
		Processus p1 = new Processus("p1");
		Processus p2 = new Processus("p2");
		p1.start();
		p2.start();
	}
	public static void main(String[] args) {
		new Examples();
	}

}
