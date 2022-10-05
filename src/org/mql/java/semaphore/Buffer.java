package org.mql.java.semaphore;

import java.util.Vector;

public class Buffer {
	private Object data[];
	private int writeHead = 0;
	private int readHead = 0;
	private Vector<Observer> observers; 

	public Buffer(int size) {
		data = new Object[size];
		observers = new Vector<Observer>();
	}
	
	public void addObserver(Observer observer) {
		observers.add(observer);
	}
	
	public void write(Object item) {
//		log("Debut écriture");
		data[writeHead] = item;
		writeHead++;
		if(writeHead >= data.length) {
			writeHead =0;
		}
		signal();
		pause(2000);
//		log("Fin écriture");
	}
	
	public Object read() {
		Object item = data[readHead];
		data[readHead] = null;
		readHead++;
		if(readHead>= data.length) readHead = 0;
		signal();
		pause(2000);
		return item;
	}
	
	public void pause(int time) {
		try {
			Thread.sleep(time);
		} catch (Exception e) {	}
	}
	
	public Object[] getData() {
		return data;
	}
	
	public void log(String message) {
		System.out.println(message + " : "+Thread.currentThread().getName());
	}
	
	private void signal() {
		for (Observer observer : observers) {
			observer.print();
		}
	}
	
	public int size() {
		return data.length;
	}
}
