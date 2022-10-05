package org.mql.threads.app;

import java.awt.Graphics;
import java.awt.Image;
import java.util.Hashtable;
import java.util.List;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Pingouin extends JPanel implements Runnable {
	private static final long serialVersionUID = 1L;
	private Thread runner;
//	private int x, y; // generaliser
	private Position pos;
	private int width = 30;
	private int height = 30;

	private String resources[] = { "stop", "right1", "right2", "right3", "left1", "left2", "left3" };
	private String type = ".gif";
	private String path = "resources/";
	private Image images[];
	private int current = 0;
	private int step = 5; // px
	private static Vector<Position> positions = new Vector<Position>();

	public Pingouin(String name, int x, int y) {
		runner = new Thread(this, name);
//		this.x = x;
//		this.y = y;
		pos = new  Position(x, y);
		if(estToucher(pos, positions)) {
			pos.setX(pos.getX()+30);
			pos.setY(pos.getY()+30);
		}
		positions.add(pos);
		setSize(width, height);
		setLocation(x, y);
		loadImages();
		repaint(); // invoquer paintComponent(g)
	}

	private void loadImages() {
		images = new Image[resources.length];
		for (int i = 0; i < images.length; i++) {
//			Toolkit.getDefaultToolkit().createImage(null); // default instance, class abstrait 
			ImageIcon icon = new ImageIcon(path + resources[i] + type);
			images[i] = icon.getImage(); // methode de fabrication, bin de fabrication, factory bin
		}
	}

	public void run() {
		do {
//			goRight();
			int decision = (int) (Math.random() * 100);
			if (decision < 40) {
				goRight(5);
			} else if (decision < 80) {
				goLeft(5);
			} else {
				stop();
			}
		} while (true);
	}

	public void goRight(int times) {
		for (int n = 0; n < times; n++) {
			for (int i = 1; i <= 3; i++) {
				current = i;
//				x += step;
				pos.setX(pos.getX()+step);
//				setLocation(x, y);
				setLocation(pos.getX() , pos.getY() );
				if(estToucher(pos, positions)) goLeft(5);
				repaint();
				pause(100);
			}
		}
	}

	public void goLeft(int times) {
		for (int n = 0; n < times; n++) {
			for (int i = 4; i <= 6; i++) {
				current = i;
//				x -= step;
				pos.setX(pos.getX()-step);
				setLocation(pos.getX(), pos.getY());
				if(estToucher(pos, positions)) goRight(5);
				repaint();
				pause(100);
			}
		}
	}

	public void stop() {
		current = 0;
		repaint();
		pause((int) (Math.random() * 3000) + 2000);
	}

	public static void pause(int time) {
		try {
			Thread.sleep(time);
		} catch (Exception e) {

		}
	}

	public void start() {
		runner.start();
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		// on dessine l'image current
		g.drawImage(images[current], 0, 0, null); // dessine l'image en 0,0, null : au place de l'observer: indique fin
		// wsalna fle dessine de l'image

	}
	
	private boolean estToucher(Position pos1, Vector<Position> positions) {
		for (Position p : positions) {
			if(pos1 !=p) {
				if(Math.abs(pos1.getX() - p.getX())<30 && Math.abs(pos1.getY() - p.getY())<30 ) {
					return true;
				}
			}
			
		}
		return false;
	}
	
	
}
