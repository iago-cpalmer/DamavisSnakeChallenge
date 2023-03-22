package com.iago.damavisChallenge;

import com.iago.damavisChallenge.model.Model;
import com.iago.damavisChallenge.view.View;
import com.iago.damavisChallenge.controller.Controller;
public class Main {

	private static Model model;
	private static View view;
	private static Controller controller;
	
	private static final int COLS = 5;
	private static final int ROWS = 5;
	
	public static void main(String[] args) {
		model = new Model(COLS, ROWS , 1,1);
		controller = new Controller(model);
		view = new View(controller, model);
		
		controller.findPossiblePaths(2, 0, null, model.getSnake());
		controller.printPaths();
	}
	
}
