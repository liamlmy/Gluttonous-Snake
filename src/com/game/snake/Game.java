package com.game.snake;

import java.awt.*;
import javax.swing.*;

public class Game implements Runnable {
	Grid grid;
	View view;
	Controller controller;
	
	public void run() {
		grid = new Grid(Settings.DEFAULT_GRID_WIDTH / Settings.DEFAULT_NODE_SIZE,
                        Settings.DEFAULT_GRID_HEIGHT / Settings.DEFAULT_NODE_SIZE);
		JFrame window = new JFrame("Mingyang-Gluttonous-Snake-Game");
		Container contentPane = window.getContentPane();
		view = new View(grid);
		view.init();
		view.getCanvas().setPreferredSize(new Dimension(Settings.DEFAULT_GRID_WIDTH, Settings.DEFAULT_GRID_HEIGHT));
		contentPane.add(view.getCanvas(), BorderLayout.CENTER);
		
		window.pack();
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
        
        controller = new Controller(grid, view);
        window.addKeyListener(controller);
        
        new Thread(controller).start();
	}
	
	public static void main(String[] args) {
        SwingUtilities.invokeLater(new Game());
    }
}
