package org.mql.java.semaphore;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class ProducerConsumer extends JFrame {

	private static final long serialVersionUID = 1L;

	public ProducerConsumer() {
		exp2();
		setSize(700, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

	void exp1() {
		Buffer buffer = new Buffer(10);
		buffer.addObserver(new ConsoleObserver(buffer));
		Semaphore s1 = new Semaphore(0); // exclusion mutuelle
		Semaphore empty = new Semaphore(buffer.size());
		Semaphore full = new Semaphore(0);

		Producer p1[] = new Producer[5];
		for (int i = 0; i < p1.length; i++) {
			p1[i] = new Producer("p" + i, buffer, s1, empty, full);
			p1[i].start();
		}
		try {
			Thread.sleep(10000);
		} catch (Exception e) {
			// TODO: handle exception
		}
		s1.unlock();
	}

	void exp2() {
		Buffer buffer = new Buffer(10);
		buffer.addObserver(new ConsoleObserver(buffer));
		BufferPanelObserver panel = new BufferPanelObserver(buffer, 50);
		buffer.addObserver(panel);
		JPanel content = new JPanel();
		content.add(panel);
		setContentPane(content);

		Semaphore s1 = new Semaphore(1); // semaphore d'exclusion mutuelle
		Semaphore empty = new Semaphore(buffer.size());
		Semaphore full = new Semaphore(0); // au debut le nombre des cases pleins c'est 0

		Producer p1[] = new Producer[3];
		for (int i = 0; i < p1.length; i++) {
			p1[i] = new Producer("p" + i, buffer, s1, empty, full);
			p1[i].start();
		}

		Consumer c1[] = new Consumer[2];
		for (int i = 0; i < c1.length; i++) {
			c1[i] = new Consumer("p" + i, buffer, s1, empty, full);
			c1[i].start();
		}

	}

	public static void main(String[] args) {
		new ProducerConsumer();
	}
}
