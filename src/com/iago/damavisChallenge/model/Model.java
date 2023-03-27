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
		
	public ArrayList<Path> getPaths() {
		return paths;
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
