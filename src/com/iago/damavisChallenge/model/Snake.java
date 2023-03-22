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
		Vector2 lastTail = tail.get(0);
		// Update snake's head position
		tail.set(0, p);
		
		for(int i = 1; i < tail.size(); i++) {
			// Store last tail position
			Vector2 aux=tail.get(i);
			// Update tail position 
			tail.set(i, lastTail);
			lastTail = aux;
		}
		tail.add(lastTail);
	}
	
	public ArrayList<Vector2> getTail() {
		return new ArrayList<Vector2>(tail.subList(1, tail.size()));
	}
	
	public Vector2 getHead() {
		return new Vector2( tail.get(0).getX(),  tail.get(0).getY());
	}
	
	
}
