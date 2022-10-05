package org.mql.threads;

public class Producer extends Thread {
	private Resource resource;

	public Producer(String name, Resource resource) {
		super(name);
		this.resource = resource;
	}

	@Override
	public void run() {
//		do {
			int data = (int)(Math.random()*100);
			resource.produce(data);
			System.out.println(Thread.currentThread().getName()+" produit "+data);
			try {
			Thread.sleep(100);
			}catch (Exception e) {}
//		} while (true);
	}
	
	
	
}
