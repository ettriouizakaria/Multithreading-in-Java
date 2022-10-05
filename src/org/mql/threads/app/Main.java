package org.mql.threads.app;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main extends JFrame implements MouseListener {
	
	private static final long serialVersionUID = 1L;
	private JPanel screen;
	

	public Main() {
		init();
	}

	private void init() {
		screen = new JPanel();
		screen.setLayout(null);
		screen.setBackground(Color.black);
		setContentPane(screen);
		setExtendedState(MAXIMIZED_BOTH);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		Pingouin p = new Pingouin("P01", 100, 100);
		screen.add(p);
		p.start();
		screen.addMouseListener(this);
		setVisible(true);
	}

	public static void main(String[] args) {
		new Main();
	}
	

	@Override
	public void mouseClicked(MouseEvent e) {
		Pingouin p = new Pingouin("P01", e.getX(), e.getY());
		screen.add(p);
		p.start();
	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}
}
