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
        	default:
		}
		
		// your code here：处理回车键，重新开始游戏
        if (keyCode == KeyEvent.VK_ENTER) {
            if (!running) {
                running = true;
                grid.init();
                new Thread(this).start();
            }
        }
        // your code here：处理回空格
        if (keyCode == KeyEvent.VK_SPACE) {
            if (running) {
                running = false;

            } else {
                new Thread(this).start();
                running = true;

            }
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
			
			if (running) {
                view.draw();
                running = grid.nextRound();
            } else {
                running = false;
                break;
            }
        }
        running = false;
	}
	
	public Grid getGrid() {
		return grid;
	}
}
