package com.iago.damavisChallenge.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.iago.damavisChallenge.controller.Controller;
import com.iago.damavisChallenge.model.Model;

public class View extends JFrame implements ActionListener{
	private Controller controller;
	private Model model;
	
	private BoardPanel board;
	private JButton btnSolve;
	private JTextField tfCols;
	private JTextField tfRows;
	private JTextField tfSteps;
	
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
        btnSolve.addActionListener(this);
        
        tfCols = new JTextField("5");
        tfCols.addActionListener(this);
        tfRows = new JTextField("5");
        tfRows.addActionListener(this);
        tfSteps = new JTextField("2");
        
        JPanel buttons = new JPanel(new FlowLayout());
        buttons.add(btnSolve);
        buttons.add(new JLabel("Steps: "));
        buttons.add(tfSteps);
        buttons.add(new JLabel("Columns: "));
        buttons.add(tfCols);
        buttons.add(new JLabel("Rows: "));
        buttons.add(tfRows);
        this.add(buttons, BorderLayout.NORTH);
        
        
        this.pack();
        this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnSolve) {
			System.out.println("Last size " + model.getSnake().getTail().size());
			int steps = Integer.parseInt(tfSteps.getText());
			controller.findPossiblePaths(steps, 0, null, model.getSnake());
			System.out.println("New size " + model.getSnake().getTail().size());
		} else if(e.getSource()==tfCols) {
			model.resetPath();
			model.removeSnake();
			model.setCols(Integer.parseInt(tfCols.getText()));
		} else if(e.getSource()==tfRows) {
			model.resetPath();
			model.removeSnake();
			model.setRows(Integer.parseInt(tfRows.getText()));
		}
		
	}
	
	
	
	
	
}
