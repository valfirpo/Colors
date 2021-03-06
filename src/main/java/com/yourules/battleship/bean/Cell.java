package com.yourules.battleship.bean;

import com.yourules.battleship.bean.Constants.CellStatus;


public class Cell{

	private Coordinates coordinate;
	private CellStatus status;
	private int items;
	
	public Cell() {
		super();
	}
	
	public Cell(Coordinates coordinate, CellStatus status) {
		super();
		this.coordinate = coordinate;
		this.status = status;
	}

	public Coordinates getCoordinate() {
		return coordinate;
	}

	public void setCoordinate(Coordinates coordinate) {
		this.coordinate = coordinate;
	}

	public CellStatus getStatus() {
		return status;
	}

	public void setStatus(CellStatus status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Cell [coordinate=" + coordinate + ", status=" + status + "]";
	}

	public int getItems() {
		return items;
	}

	public void setItems(int items) {
		this.items = items;
	}
	
	public boolean equals(Coordinates c) {
		System.out.println(c.toString());
		
		if(this.getCoordinate().getY() != c.getY()){
			//System.out.println("Y: " + this.getCoordinate().getY() + " " + c.getY());
			return false;
		}
		
		if(this.getCoordinate().getX() != c.getX()){
			//System.out.println("X: " + this.getCoordinate().getX() + " " + c.getX());
			return false;
		}
		
        return true;
    }
}
