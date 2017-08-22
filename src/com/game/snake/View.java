package com.game.snake;

import java.awt.*;
import java.util.Deque;
import javax.swing.*;

public class View {
	
	private final Grid grid;
	private JPanel canvas;
	
	public View(Grid grid) {
		this.grid = grid;
	}
	
	public void init() {
		canvas = new JPanel() {
			@Override
			public void paintComponent(Graphics graphics) {
				drawGridBackground(graphics);
				drawSnake(graphics, grid.getSnake());
				drawFood(graphics, grid.getFood());
			}
		};
	}
	
	public void draw() {
        canvas.repaint();
    }
	
	// Draw the food
	public void drawFood(Graphics graphics, Node food) {
		drawCircle(graphics, food, Settings.DEFAULT_FOOD_COLOR);
	}
	
	// Draw the snake
	public void drawSnake(Graphics graphics, Snake snake) {
		Deque<Node> body = snake.getBody();
		for (Node node : body) {
			drawSquare(graphics, node, Settings.DEFAULT_NODE_COLOR);
		}
	}
	
	// Draw the background
	public void drawGridBackground(Graphics graphics) {
        graphics.setColor(Settings.DEFAULT_BACKGROUND_COLOR);
        graphics.fillRect(0, 0, grid.getWidth() * Settings.DEFAULT_NODE_SIZE, grid.getHeight() * Settings.DEFAULT_NODE_SIZE);
    }
	
	// Draw a square
	private void drawSquare(Graphics graphics, Node squareArea, Color color) {
        graphics.setColor(color);
        int size = Settings.DEFAULT_NODE_SIZE;
        graphics.fillRect(squareArea.getX() * size, squareArea.getY() * size, size - 1, size - 1);
    }
	
	// Draw a circle
	private void drawCircle(Graphics graphics, Node squareArea, Color color) {
        graphics.setColor(color);
        int size = Settings.DEFAULT_NODE_SIZE;
        graphics.fillOval(squareArea.getX() * size, squareArea.getY() * size, size, size);
    }
	
	public JPanel getCanvas() {
		return canvas;
	}
}
