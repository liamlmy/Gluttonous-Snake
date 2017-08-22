package com.game.snake;

import java.util.*;

public class Snake {
	
	private final Deque<Node> body = new LinkedList<Node>();
	
	public Node eat(Node food) {
		if (isNeighbor(food, getBody().peekFirst())) {
			getBody().offerFirst(food);
			return food;
		}
		return null;
	}
	
	public Node move(Direction direction) {
		Deque<Node> body = getBody();
		int x = body.peekFirst().getX();
		int y = body.peekFirst().getY();
		
		switch (direction) {
			case UP:
				y--;
				break;
			case RIGHT:
				x++;
				break;
			case DOWN:
				y++;
				break;
			case LEFT:
				x--;
				break;
		}
		
		body.addFirst(new Node(x, y));
		return body.pollLast();
	}
	
	public Deque<Node> getBody() {
		return body;
	}
	
	public Node getHead() {
		return getBody().peekFirst();
	}
	
	public Node addTail(Node tail) {
		getBody().offerLast(tail);
		return tail;
	}
	
	private boolean isNeighbor(Node a, Node b) {
		return Math.abs(a.getX() - b.getX()) + Math.abs(a.getY() - b.getY()) == 1;
	}
}
