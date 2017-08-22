package com.game.snake;

public enum Direction {
	UP(0),
	RIGHT(1),
	DOWN(2),
	LEFT(3);
	
	private final int directionValue;
	
	Direction(int directionValue) {
		this.directionValue = directionValue;
	}
	
	private int directionValue() {
		return directionValue;
	}
	
	// Determine whether the new direction is valid
	public boolean compatibleWith(Direction direction) {
		return (direction.directionValue() + this.directionValue()) % 2 == 1;
	}
}
