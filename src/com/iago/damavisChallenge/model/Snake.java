package com.iago.damavisChallenge.model;

import java.util.ArrayList;

public class Snake {
	private ArrayList<Vector2> tail; // Index 0: snake head
	
	public Snake(Vector2 initPosition) {
		this.tail = new ArrayList<Vector2>();
		this.tail.add(initPosition);
	}
	
	public Snake(Vector2 head, ArrayList<Vector2> tail) {
		this.tail = new ArrayList<Vector2>();
		this.tail.add(head);
		for(Vector2 v:tail) {
			this.tail.add(new Vector2(v.getX(), v.getY()));
		}
	}
	
	public void updateTail(Vector2 p) {
	    // Store current snake's head position
	    Vector2 lastTail = new Vector2(tail.get(0).getX(), tail.get(0).getY());
	    // Update snake's head position
	    tail.set(0, p);

	    for (int i = 1; i < tail.size(); i++) {
	        Vector2 t = tail.get(i);
	        // Store last tail position
	        Vector2 aux = new Vector2(t.getX(), t.getY());
	        // Update tail position
	        t.setX(lastTail.getX());
	        t.setY(lastTail.getY());
	        lastTail = aux;
	    }
	}

	
	public boolean inPosition(int x, int y) {
		for(Vector2 v:getTail()) {
			if(v.equals(x, y)) {
				return true;
			}
		}
		return false;
	}
	
	public Vector2 getLastTail() {
		if(tail.size()==0) {
			return null;
		}
		return tail.get(tail.size()-1);
	}
	
	public void addTail(int x, int y) {
		
		this.tail.add(new Vector2(x,y));
	}
	
	public void removeTail(int x, int y) {
		if(tail.get(0).equals(x, y)) {
			// it's the head
			tail = new ArrayList<Vector2>();
		}
		for(int i = 0; i < tail.size(); i++) {
			if(tail.get(i).equals(x, y)) {
				tail = new ArrayList<Vector2>(tail.subList(0, i));
			}
		}
	}
	
	public void removeTail(Vector2 v) {
		if(tail.get(0).equals(v)) {
			// it's the head
			tail = new ArrayList<Vector2>();
		}
		for(int i = 0; i < tail.size(); i++) {
			if(tail.get(i).equals(v)) {
				tail = new ArrayList<Vector2>(tail.subList(0, i));
			}
		}
	}
	
	public ArrayList<Vector2> getTail() {
		if(tail.size()==0) {
			return new ArrayList<Vector2>();
		}
		return new ArrayList<Vector2>(tail.subList(1, tail.size()));
	}
	
	public Vector2 getHead() {
		if(tail.size()==0) {
			return null;
		}
		return new Vector2( tail.get(0).getX(),  tail.get(0).getY());
	}
	
	
}
