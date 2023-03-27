package com.iago.damavisChallenge.view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import com.iago.damavisChallenge.model.Model;
import com.iago.damavisChallenge.model.Snake;
import com.iago.damavisChallenge.model.Vector2;

public class BoardPanel extends JPanel implements MouseListener{

	
	private Model model;
	
	private int xi, xf, yi,yf;
	
	private Color clrHead = new Color(0,100,0);
	private Color clrTail = Color.GREEN;
	
	public BoardPanel(Model model) {
		this.model = model;
		this.addMouseListener(this);
	}
	
	
	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		
		// Board
		int cols = model.getCols();
		int rows = model.getRows();
		
		int size = 0;
		if(this.getWidth()>this.getHeight()) {
			size = this.getHeight();
		} else {
			size = this.getWidth();
		}
		
		int offset = 0;
		if(rows > cols) {
			offset = Math.floorDiv(size, rows);
		} else {
			offset = Math.floorDiv(size, cols);
		}
		
		
		g2d.setColor(Color.BLACK);
		g2d.setStroke(new BasicStroke(3));
		
		xi=(this.getWidth()-offset*cols)/2;
		yi = (this.getHeight() - offset*rows)/2;
		
		// Vertical lines
		yf = offset*rows + yi;
		xf = offset*cols + xi;
		
		for(int x = 0; x <= cols; x++) {
			g2d.drawLine(offset*x + xi, yi, offset*x + xi, yf);
		}
		
		// Horizontal lines
		
		for(int y = 0; y <= rows; y++) {
			g2d.drawLine(xi, offset*y + yi, xf, offset*y + yi);
		}
		
		
		// Snake
		g2d.setColor(clrTail);
		Snake snake = model.getSnake();
		for(Vector2 t : snake.getTail()) {
			g2d.fillRect(t.getX()*offset + xi, t.getY()*offset + yi, offset, offset);
		}
		g2d.setColor(clrHead);
		Vector2 head = snake.getHead();
		g2d.fillRect(head.getX()*offset + xi, head.getY()*offset + yi, offset, offset);
		
		// Paths
				
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		int xRelCoord = e.getX();
		int yRelCoord = e.getY();
		int xBoard;
		int yBoard;
		if(!insideBoard(xRelCoord, yRelCoord)) {
			return;
		}
		xBoard = (xRelCoord-xi)/((xf-xi)/model.getCols());
		yBoard = (yRelCoord-yi)/((yf-yi)/model.getCols());
		switch(e.getButton()) {
			case MouseEvent.BUTTON1:
				
				break;
			case MouseEvent.BUTTON3:
				break;
		}
	}


	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public boolean insideBoard(int x, int y) {
		return x >=xi && x<xf && y>=yi && y<yf;
	}
}
