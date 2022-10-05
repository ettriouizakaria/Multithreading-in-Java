package org.mql.threads;

public class Examples {
	public Examples() {
		exp3();
	}

	void exp1() {
//		Thread p1 = new Thread("p1");
//		p1.start(); //rien a fiare !!!
//		p1.run(); // ne jamais appeler diretement -> vide
		Processus p1 = new Processus("p1");
		Processus p2 = new Processus("p2");
		p1.run(); //  c'est le main(thread appeler main cree par jvm) qui va executer cette methode
		p2.run();
	}
	
	void exp2() {
		Resource resource = new Resource();
		Producer w1 = new Producer("w1", resource);
		Producer w2 = new Producer("w2", resource);
		w1.start();
		w2.start();
	}
	
	void exp3() {
		Resource resource = new Resource();
		Producer p1 = new Producer("p1", resource);
		Consumer c1 = new Consumer("c1", resource);
		p1.start();
		c1.start();
		try {
			p1.join();
			c1.join();
			System.out.println("resource :"+resource.getValue());
		} catch (InterruptedException e) {
		}
		
	}

	public static void main(String[] args) {
		new Examples();
	}
}
