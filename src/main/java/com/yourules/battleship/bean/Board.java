package com.yourules.battleship.bean;

import java.util.Arrays;

import org.springframework.stereotype.Component;

import com.yourules.battleship.bean.Constants.CellStatus;


@Component
public abstract class Board {

	private int cellsHit;
	protected Cell[][] cells;
	
	public Board(){
		//this.cellsHit = 3;
		//this.cells = new Cell[Constants.BoardSize][Constants.BoardSize];
		this.cells = new Cell[Constants.BoardSize][Constants.BoardSize];
		
		for(int i = 0; i < Constants.BoardSize; i++){
			char temp = 'A';
			for(int j = 0; j < Constants.BoardSize; j++){
				this.cells [i][j] = new  Cell(new Coordinates((char) (temp + i), j), Constants.CellStatus.open);
			}
		}
	}



	public int getCellsHit() {
		return cellsHit;
	}

	public void setCellsHit(int cellsHit) {
		this.cellsHit = cellsHit;
	}

	@Override
	public String toString() {
		return "Board [cellsHit=" + cellsHit + ", cells=" + Arrays.toString(cells) + "]";
	}



	public Cell[][] getCells() {
		return cells;
	}



	public void setCells(Cell[][] cells) {
		this.cells = cells;
	}
	
	public void printBoard(){
		for(int i = 0; i < Constants.BoardSize; i++){
			for(int j = 0; j < Constants.BoardSize; j++){
				System.out.print(this.cells[i][j].getStatus() + " ");
			}
			System.out.println();
		}
	}
	
	public void updateCell(Coordinates c, CellStatus stat) {
		this.cells[c.getY() - 65][c.getX()].setStatus(stat);
	}
}
