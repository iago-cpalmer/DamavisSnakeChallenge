package com.iago.damavisChallenge.view;

import java.awt.HeadlessException;

import javax.swing.JFrame;

import com.iago.damavisChallenge.controller.Controller;
import com.iago.damavisChallenge.model.Model;

public class View extends JFrame{
	private Controller controller;
	private Model model;
	
	public View(Controller controller, Model model){
		this.controller = controller;
		this.model = model;
		
		// init jframe
	}
	
	
}
