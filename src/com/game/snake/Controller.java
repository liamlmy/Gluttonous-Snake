package com.game.snake;

import java.awt.event.*;

public class Controller implements Runnable, KeyListener {
	private final Grid grid;
	private final View view;
	boolean running;

	public Controller(Grid grid, View view) {
		this.grid = grid;
		this.view = view;
		this.running = true;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();

		switch (keyCode) {
		case KeyEvent.VK_UP:
			grid.changeDirection(Direction.UP);
			break;
		case KeyEvent.VK_DOWN:
			grid.changeDirection(Direction.DOWN);
			break;
		case KeyEvent.VK_LEFT:
			grid.changeDirection(Direction.LEFT);
			break;
		case KeyEvent.VK_RIGHT:
			grid.changeDirection(Direction.RIGHT);
			break;
		case KeyEvent.VK_SPACE:
			running = !running;
			new Thread(this).start();
			break;
		case KeyEvent.VK_ENTER:
			if (!running) {
				grid.init();
				running = true;
				new Thread(this).start();
				break;
			}
		default:
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	public void run() {
		while (running) {
			try {
				Thread.sleep(Settings.DEFAULT_MOVE_INTERVAL - getGrid().getSpeed());
			} catch (InterruptedException en) {
				break;
			}

			if (grid.nextRound()) {
                view.draw();
            } else {
                view.showGameOverMessage();
                break;
            }
			
//			if (running) {
//				view.draw();
//				running = grid.nextRound();
//			} else {
//				running = false;
//				break;
//			}
		}
		running = false;
	}

	public Grid getGrid() {
		return grid;
	}
}
