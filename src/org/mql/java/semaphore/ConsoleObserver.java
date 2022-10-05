package org.mql.java.semaphore;

import java.util.Arrays;

public class ConsoleObserver implements Observer {
	private Buffer buffer;

	public ConsoleObserver(Buffer buffer) {
		this.buffer = buffer;
//		buffer.addObserver(this); pas la paine
	}

	@Override
	public void print() {
		System.out.println(Arrays.toString(buffer.getData()));

	}
}
