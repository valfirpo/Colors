package com.yourules.battleship.bean;

public class Coordinates {

	private char y;
	private int x;
	
	public Coordinates() {
		super();
	}
	
	public Coordinates(char y, int x) {
		super();
		this.y = y;
		this.x = x;
	}

	public char getY() {
		return y;
	}

	public void setY(char y) {
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	@Override
	public String toString() {
		return "Coordinates [" + y + "," + x + "]";
	}
}
