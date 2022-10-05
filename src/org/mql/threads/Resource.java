package org.mql.threads;

/*
 * Resource partagée
 */
public class Resource {
	private int value =1000;
	public Resource() {
		
	}
	
	synchronized public void produce(int data) { // write
		System.out.println("debut traitement : "+Thread.currentThread().getName());
		int v = value; // BD
		pause(1000);
		v+=data;
		
		pause(2000);
		value = v;
		System.out.println("fin traitement : "+Thread.currentThread().getName());
	}
	synchronized public void consume(int data) { //read

		System.out.println("debut traitement : "+Thread.currentThread().getName());
		int v = value; // BD
		pause(1000);
		v-=data;
		pause(1000);
		value = v;
		System.out.println("fin traitement : "+Thread.currentThread().getName());
	}
	
	public static void pause(int time) {
		try {
			Thread.sleep(time);
		}catch (Exception e) {
			
		}
	}
	
	public int getValue() {
		return value;
	}
}
