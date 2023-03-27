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
			parentPath = new Path(maxLength);
		}
		if(maxLength==currentLength) {
			// End of recursivity, all paths have been already found
			model.getPaths().add(parentPath);
			return;
		}
		// Check all possible directions from snake's head position
		int[] movsX = {-1,0,1};
		int[] movsY = {-1,0,1};
		Vector2 snakeHead = model.getSnake().getHead();
		// Apply current path
		snakeHead = parentPath.applyPath(snakeHead);
		
		for(int i = 0; i < movsX.length; i++) {
			for(int j = 0; j < movsY.length; j++) {
				// Check if new position is valid
				// For a position to be valid it must be
				// inside the board, cannot share square
				// with any of its tails and cannot be diagonal
				// Make an other snake from the last snake
				snake = new Snake(snake.getHead(), snake.getTail());
				Vector2 newPos = new Vector2(snakeHead.getX()+movsX[i], snakeHead.getY()+movsY[j]);
				// Update the tail of the snake with the new positions to check if head collides with any square of its tail
				snake.updateTail(newPos);
				if(isValid(newPos) && notOccupied(newPos, snake) && movsX[i]!=movsY[j] && (movsX[i]==0 || movsY[j]==0)) {
					// The new position is valid
					Path childPath = new Path(maxLength);
					childPath.setSteps(parentPath.getSteps());
					childPath.addStep(new Vector2(movsX[i], movsY[j]));
					findPossiblePaths(maxLength, currentLength+1, childPath, snake);
				}
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
