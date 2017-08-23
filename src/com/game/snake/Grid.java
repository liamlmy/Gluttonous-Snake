package com.game.snake;

public class Grid {
	
	public final boolean[][] status;
	private final int width;
	private final int height;
	
	private Snake snake;
	private Node food;	
	private Node barrier;
	private Direction snakeDirection = Direction.LEFT;
	
	private int speed;
	
	public Grid(int width, int height) {
		this.width = width;
		this.height = height;
		status = new boolean[width][height];
		
		init();
	}
	
	public void init() {
		// Initialize the background
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				status[i][j] = false;
			}
		}
		
		// Initialize the direction
		snakeDirection = Direction.LEFT;
		
		// Initialize the snake
		initSnake();
		
		// Initialize the barrier
		createBarrier();
		
		// Initialize the food
		createFood();
	}
	
	private Snake initSnake() {
		snake = new Snake();
		speed = 0;
		
		int originalLen = width / 10;
		for (int i = originalLen - 1; i >= 0; i--) {
			snake.getBody().offerFirst(new Node(width / 2 + i, height / 2));
			status[width / 2 + i][height / 2] = true;
		}
		
		return snake;
	}
	
	private Node createFood() {
		int x;
		int y;
		
		do {
			x = (int) (Math.random() * width);
			y = (int) (Math.random() * height);
		} while (status[x][y]);
		
		food = new Node(x, y);
		return food;
	}
	
	public void changeDirection(Direction direction) {
		if (snakeDirection.compatibleWith(direction)) {
			snakeDirection = direction;
		}
	}
	
	private Node createBarrier() {
		int x;
		int y;
		
		do {
			x = (int) (Math.random() * width);
			y = (int) (Math.random() * height);
		} while (status[x][y]);
		
		barrier = new Node(x, y);
		status[x][y] = true;
		return barrier;
	}
	
	public boolean nextRound() {
		Node tail = snake.move(snakeDirection);
		Node head = snake.getHead();
		
		if (validPosition(head)) {
			if (isFood(head)) {
				getSnake().addTail(tail);
				if (speed < 110) {
					speed += 5;
				}
				createBarrier();
				createFood();
			} else {
				dispose(tail);
			}
			occupy(head);
			return true;
		}
		return false;
	}
	
	private boolean validPosition(Node node) {
		if (node.getX() >= 0 && node.getX() < width && node.getY() >= 0 && node.getY() < height && !status[node.getX()][node .getY()]) {
			return true;
		}
		return false;
	}
	
	private void dispose(Node node) {
        status[node.getX()][node.getY()] = false;
    }

    private void occupy(Node node) {
        status[node.getX()][node.getY()] = true;
    }
    
    private boolean isFood(Node node) {
    	int x = node.getX();
    	int y = node.getY();
    	if (getFood().getX() == x && getFood().getY() == y) {
    		return true;
    	}
    	return false;
    }
    
    public Node getBarrier() {
    	return barrier;
    }
    
    public Node getFood() {
    	return food;
    }
    
    public int getWidth() {
    	return width;
    }
    
    public int getHeight() {
    	return height;
    }
    
    public Snake getSnake() {
    	return snake;
    }
    
    public int getSpeed() {
    	return speed;
    }
}
