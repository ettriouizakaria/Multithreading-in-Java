package org.mql.threads;

public class Consumer extends Thread {
	private Resource resource;

	public Consumer(String name, Resource resource) {
		super(name);
		this.resource = resource;
	}

	@Override
	public void run() {
//		do {
		int data = (int) (Math.random() * 100);
		resource.consume(data);
		System.out.println(Thread.currentThread().getName() + " consome : " + data);
		try {
			Thread.sleep(100);
		} catch (Exception e) {
		}
//		} while (true);
	}

}
