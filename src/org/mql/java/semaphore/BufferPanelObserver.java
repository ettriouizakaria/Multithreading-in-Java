package org.mql.java.semaphore;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class BufferPanelObserver extends JPanel implements Observer {

	private static final long serialVersionUID = 1L;
	private Buffer buffer;
	private Dimension dimension;
	private int cellSize;
	private int margin = 70;

	public BufferPanelObserver(Buffer buffer, int cellSize) {
		this.buffer = buffer;
		dimension = new Dimension(buffer.size() * cellSize + 2 * margin, cellSize + 2 * margin);
		setBorder(new TitledBorder(new EtchedBorder(), "Buffer (Shared Resource) : "));
		this.cellSize = cellSize;
	}

	@Override
	public void print() {
		repaint();
	}

	@Override
	protected void paintComponent(Graphics g) {

		super.paintComponent(g);
		paintBuffer(g);
		writeData(g);
		paintIcons(g);
		paintMessage(g);
	}

	public void paintBuffer(Graphics g) {
//		g.setColor(Color.white);
//		g.fillRect(margin, margin, buffer.size() * cellSize, cellSize);
		g.setColor(Color.blue);
		g.drawRect(margin, margin, buffer.size() * cellSize, cellSize);
		for (int i = 1; i < buffer.size(); i++) {
			g.drawLine(i * cellSize + margin, margin , i * cellSize + margin, margin + cellSize);
		}
	}

	public void writeData(Graphics g) {
		Object data[] = buffer.getData();
		
		for (int i = 0; i < data.length; i++) {
			
			if (data[i] != null) {
				g.setColor(Color.orange);
				g.fillRect(margin + cellSize * i + 2, margin + 2, cellSize-3, cellSize -3);
				g.setColor(Color.red);
				g.drawString(" "+data[i],margin + cellSize * i + 12, margin + cellSize / 2);
				///******************
//				g.drawString("=>",margin + cellSize * i + 2, margin + cellSize / 2);
			}
		}
	}

	public void paintIcons(Graphics g) {

	}

	public void paintMessage(Graphics g) {

	}

	public Dimension getPreferredSize() {
		return dimension;
	}
}
