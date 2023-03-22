package com.iago.damavisChallenge.model;

public class Vector2 {
	private int x,y;
	
	public Vector2(int x, int y) {
		this.x=x;
		this.y=y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public boolean equals(Vector2 b) {
		return b.getX()==this.getX() && b.getY()==this.getY();
	}
	
	public String toString() {
		return "(" + x + ", " + y +")";
	}
	
	public void add(Vector2 b) {
		this.x=this.x+b.getX();
		this.y=this.y + b.getY();
	}
}
