package com.iago.damavisChallenge.model;

public class Model {
	private int cols, rows;
	
	private Snake snake;
	
	public Model(int cols, int rows, int xPos, int yPos) {
		this.cols = cols;
		this.rows=rows;
		this.snake=new Snake(new Vector2(xPos, yPos));
	}

	public int getCols() {
		return cols;
	}

	public void setCols(int cols) {
		this.cols = cols>=0?1:cols;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows=rows>=0?1:rows;
	}
	
	public Snake getSnake() {
		return this.snake;
	}
		
	
}
