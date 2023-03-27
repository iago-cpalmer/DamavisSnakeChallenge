package com.iago.damavisChallenge.controller;

import java.util.ArrayList;

import com.iago.damavisChallenge.model.Model;
import com.iago.damavisChallenge.model.Snake;
import com.iago.damavisChallenge.model.Vector2;

public class Controller {
	private Model model;
	
	
	
	public Controller(Model model) {
		this.model=model;
	}
	
	public void findPossiblePaths(int maxLength, int currentLength, Path parentPath, Snake snake) {
		if(parentPath==null) {
			this.model.resetPath();
			parentPath = new Path(maxLength);
		}
		if(maxLength==currentLength) {
			// End of recursivity, all paths have been already found
			model.getPaths().add(parentPath);
			return;
		}
		// Check all possible directions from snake's head position
		int[] movs = {-1,1};
		Vector2 snakeHead = model.getSnake().getHead();
		
		// Apply current path
		snakeHead = parentPath.applyPath(snakeHead);
		Snake ogSnake = new Snake(snakeHead, snake.getTail());
		for(int i = 0; i < movs.length; i++) {
			Vector2 newPos = new Vector2(snakeHead.getX()+movs[i], snakeHead.getY());
			if(isValid(newPos) && !ogSnake.inPosition(newPos.getX(), newPos.getY())) {
				snake = new Snake(ogSnake.getHead(), ogSnake.getTail());
				snake.updateTail(newPos);
				// The new position is valid
				Path childPath = new Path(maxLength);
				childPath.setSteps(parentPath.getSteps());
				childPath.addStep(new Vector2(movs[i], 0));
				findPossiblePaths(maxLength, currentLength+1, childPath, snake);
				
			}
			
		}
		
		for(int i = 0; i < movs.length; i++) {
			Vector2 newPos = new Vector2(snakeHead.getX(), snakeHead.getY()+movs[i]);
			if(isValid(newPos) && !ogSnake.inPosition(newPos.getX(), newPos.getY())) {
				snake = new Snake(ogSnake.getHead(), ogSnake.getTail());
				snake.updateTail(newPos);
				// The new position is valid
				Path childPath = new Path(maxLength);
				childPath.setSteps(parentPath.getSteps());
				childPath.addStep(new Vector2(0, movs[i]));
				findPossiblePaths(maxLength, currentLength+1, childPath, snake);
			}
			
		}
		
	}
	
	public boolean isValid(Vector2 pos) {
		int x = pos.getX();
		int y = pos.getY();
		return 	x>=0 &&
				x<model.getCols() &&
				y>=0 &&
				y<model.getRows();
	}
	
	public boolean notOccupied(Vector2 pos, Snake snake) {
		ArrayList<Vector2> snakeTail = snake.getTail();
		for(Vector2 v: snakeTail) {
			if(v.equals(pos)) {
				return false;
			}
		}
		return true;
	}
	
	
}
