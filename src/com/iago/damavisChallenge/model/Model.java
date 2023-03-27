package com.iago.damavisChallenge.model;

import java.util.ArrayList;

import com.iago.damavisChallenge.controller.Path;

public class Model {
	private int cols, rows;
	
	private Snake snake;
	private ArrayList<Path> paths;
	
	public Model(int cols, int rows, int xPos, int yPos) {
		this.cols = cols;
		this.rows=rows;
		this.snake=new Snake(new Vector2(xPos, yPos));
		this.paths = new ArrayList<Path>();
	}

	public int getCols() {
		return cols;
	}

	public void setCols(int cols) {
		this.cols = cols>=0?cols:1;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows=rows>=0?rows:1;
	}
	
	public Snake getSnake() {
		return this.snake;
	}
		
	public ArrayList<Path> getPaths() {
		return paths;
	}
	
	public boolean addTail(int x, int y) {
		// Get all possible coords where it can be placed
		// and check whether is valid or not
		
		Vector2 lastTail = snake.getLastTail();
		if(lastTail==null) {
			snake.addTail(x, y);
			return true;
		}
		// check neighbor squares
		// Check if it's not in diagonal with the last tail
		if((lastTail.getX()==x || lastTail.getY()==y) && (lastTail.getX()!=x || lastTail.getY()!=y)) {
			
			int xs[] = {-1,1};
			// Horizontal checks
			
			for(int i = 0; i < xs.length; i++) {
				int newX = lastTail.getX()+xs[i];
				if(x==newX && !snake.inPosition(x,y)) {
					// It's the same coord, it's valid
					snake.addTail(x, y);
					return true;
				}
			}
			
			// Vertical checks
			for(int i = 0; i < xs.length; i++) {
				int newY = lastTail.getY()+xs[i];
				if(y==newY && !snake.inPosition(x,y)) {
					// It's the same coord, it's valid
					snake.addTail(x, y);
					return true;
				}
			}
		}
		return false;
		
	}
	
	public void removeSnake() {
		if(this.snake.getHead()!=null) {
			this.snake.removeTail(this.snake.getHead());
		}
		
	}
	
	public void removeTail(int x, int y) {
		this.snake.removeTail(x, y);
	}
	
	public void resetPath() {
		this.paths = new ArrayList<Path>();
	}
	
	public void printPaths() {
		System.out.println("--------------");
		for(Path p:paths) {
			System.out.println("Path: " + p.toString());
			Vector2 snakeHead = snake.getHead();
			snakeHead = p.applyPath(snakeHead);
			System.out.println("End snake pos: " + snakeHead);
		}
	}
}
