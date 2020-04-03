package com.yourules.battleship.bean;

import java.util.Arrays;

import com.yourules.battleship.bean.Constants.ShipStatus;


public abstract class  Boat {
	
	protected String name;
	protected ShipStatus status;
	protected Cell[] cells;
	protected int length;
	protected boolean horizontal;
	
	public boolean isHorizontal() {
		return horizontal;
	}

	public void setHorizontal(boolean horizontal) {
		this.horizontal = horizontal;
	}

	public Boat(){
		this.status = ShipStatus.notSet;
	}
	
	public void defaultPoints(int len){
		cells = new Cell[len];
		for(int i = 0; i < len; i++){
			cells[i] = new Cell(new Coordinates('A', i), Constants.CellStatus.boat);
		}
		this.horizontal = true;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ShipStatus getStatus() {
		return status;
	}

	public void setStatus(ShipStatus status) {
		this.status = status;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public Cell[] getCells() {
		return cells;
	}

	public void setCells(Cell[] cells) {
		this.cells = cells;
	}

	@Override
	public String toString() {
		return "Boat [name=" + name + ", status=" + status + ", cells=" + Arrays.toString(cells) + ", length=" + length
				+ "]";
	}
}