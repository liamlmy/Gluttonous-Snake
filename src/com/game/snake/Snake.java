package com.game.snake;

import java.util.*;

public class Snake {
	
	private final Deque<Node> body = new LinkedList<Node>();
	
	public Node eat(Node food) {
		
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
