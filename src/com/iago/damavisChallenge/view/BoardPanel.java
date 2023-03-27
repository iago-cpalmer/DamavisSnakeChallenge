package com.iago.damavisChallenge.view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.iago.damavisChallenge.controller.Path;
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
		
		Snake snake = model.getSnake();
		int lastCenterX = 0;
		int lastCenterY = 0;
		
		g2d.setColor(clrHead);
		Vector2 head = snake.getHead();
		if(head!=null) {
			lastCenterX = head.getX() * offset + offset/2 + this.xi;
			lastCenterY = head.getY() * offset + offset/2 + this.yi;
			g2d.fillRect(head.getX()*offset + xi + 1, head.getY()*offset + yi + 1, offset-2, offset-2);
		}
		for(Vector2 t : snake.getTail()) {
			int newCenterX = t.getX() * offset + offset/2 + this.xi;
			int newCenterY = t.getY() * offset + offset/2 + this.yi;
			g2d.setColor(clrTail);
			g2d.fillRect(t.getX()*offset + xi + 1, t.getY()*offset + yi  + 1, offset -2, offset -2);
			g2d.setColor(clrHead);
			paintArrow(lastCenterX, newCenterX, lastCenterY, newCenterY, g2d);
			lastCenterX = newCenterX;
			lastCenterY = newCenterY;
		}
		// Paths
		Random rand = new Random(3);
		
		g2d.setStroke(new BasicStroke(3));
		for(Path path:model.getPaths()) {
			int lastX = head.getX();
			int lastY = head.getY();
			int lastPX = 0;
			int lastPY = 0;
			Color c = new Color(rand.nextFloat(0,1), rand.nextFloat(0,1), rand.nextFloat(0,1));
			int rOffset = rand.nextInt((-offset+10)/2,(offset-10)/2);
			lastPX = lastX*offset + offset/2+this.xi + rOffset;
			lastPY = lastY*offset + offset/2+this.yi + rOffset;
			for(Vector2 p:path.getSteps()) {
				g2d.setColor(c);
				int newX = lastX + p.getX();
				int newY = lastY + p.getY();
				
				if(newX==lastX) {
					int newPX = lastPX;
					int newPY = lastPY + (newY-lastY)*offset;
					paintArrow(lastPX, newPX, lastPY, newPY, g2d);
					lastPX = newPX;
					lastPY = newPY;
				} else {
					int newPX = lastPX + (newX-lastX)*offset;
					int newPY = lastPY;
					paintArrow(lastPX, newPX, lastPY, newPY, g2d);
					lastPX = newPX;
					lastPY = newPY;
				}
				lastX = newX;
				lastY = newY;
				
			}
				paintDot(lastPX, lastPY, g2d);
		}
		
	}

	public void paintDot(int x, int y, Graphics2D g2d) {
		g2d.fillOval(x-5, y-5, 10, 10);
	}
	
	public void paintArrow(int xi, int xf, int yi, int yf, Graphics2D g2d) {
		g2d.drawLine(xi, yi, xf, yf);
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
		yBoard = (yRelCoord-yi)/((yf-yi)/model.getRows());
		switch(e.getButton()) {
			case MouseEvent.BUTTON1:
				// place
				if(!model.addTail(xBoard, yBoard)) {
					// open dialog
					JOptionPane.showMessageDialog(this, "Invalid position for the tail!");
				}
				
				break;
			case MouseEvent.BUTTON3:
				model.removeTail(xBoard, yBoard);
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
