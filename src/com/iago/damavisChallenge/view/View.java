package com.iago.damavisChallenge.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.HeadlessException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.iago.damavisChallenge.controller.Controller;
import com.iago.damavisChallenge.model.Model;

public class View extends JFrame{
	private Controller controller;
	private Model model;
	
	private BoardPanel board;
	private JButton btnSolve;

	
	private final int MIN_WIDTH = 500, MIN_HEIGHT = 500;
	
	public View(Controller controller, Model model){
		this.controller = controller;
		this.model = model;
		
		this.setTitle("Damavis Snake Challenge");
        this.setMinimumSize(new Dimension(MIN_WIDTH, MIN_HEIGHT));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(null);
        this.getContentPane().setLayout(new BorderLayout());
        
        this.board = new BoardPanel(model);
        this.add(board, BorderLayout.CENTER);
        
        btnSolve = new JButton("Solve");
        
        JPanel buttons = new JPanel(new FlowLayout());
        buttons.add(btnSolve);
        this.add(buttons, BorderLayout.NORTH);
        
        
        this.pack();
        this.setVisible(true);
	}
	
	
}
