package com.iago.damavisChallenge.controller;

import com.iago.damavisChallenge.model.Vector2;

public class Path {
	private Vector2[] steps;
	
	public Path(int nSteps) {
		this.steps = new Vector2[nSteps];
		for(int i = 0; i < steps.length; i++) {
			steps[i] = new Vector2(-1,-1);
		}
	}
	
	public Vector2 getStep(int i) {
		return steps[i];
	}
	
	public void addStep(Vector2 step) {
		int index = findFirstFreeStep();
		if(index!=-1) {
			steps[index] = step;
		}
	}
	
	public void setSteps(Vector2[] steps) {
		for(int i = 0; i < steps.length; i++) {
			this.steps[i] = new Vector2(steps[i].getX(), steps[i].getY());
		}
	}
	public Vector2[] getSteps() {
		return steps;
	}
	
	public int findFirstFreeStep() {
		for(int i = 0; i < steps.length; i++) {
			if(steps[i].equals(new Vector2(-1,-1))) {
				return i;
			}
		}
		return -1;
	}
	
	public String toString() {
		String s = "";
		for(Vector2 v:steps) {
			s+=v.toString();
		}
		return s;
	}

	public Vector2 applyPath(Vector2 snakeHead) {
		for(Vector2 s:steps) {
			if(!s.equals(new Vector2(-1,-1))) {
				snakeHead.add(s);
			}
			
		}
		return snakeHead;
	}
}
