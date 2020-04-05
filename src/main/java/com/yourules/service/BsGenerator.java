package com.yourules.service;

import java.util.ArrayList;

import com.yourules.battleship.bean.BattleShip;
import com.yourules.battleship.bean.Cell;
import com.yourules.battleship.bean.Constants;
import com.yourules.battleship.bean.Coordinates;
import com.yourules.bean.GameTemplate;
import com.yourules.util.Status;

public class BsGenerator {

	public GameTemplate bsBS(String username) {
		GameTemplate game = new BattleShip(username, "yemp", Status.STARTED);
		return game;
	}

	public ArrayList<Cell[]> bsCells() {
		
		ArrayList<Cell[]> cells = new ArrayList<Cell[]>();
		
		Cell[] arr1 = new Cell[4];
		Cell[] arr2 = new Cell[5];
		Cell[] arr3 = new Cell[3];
		Cell[] arr4 = new Cell[2];
		Cell[] arr5 = new Cell[3];
		
		arr1[0] = new Cell(new Coordinates('A', 0), Constants.CellStatus.boat);
		arr1[1] = new Cell(new Coordinates('A', 1), Constants.CellStatus.boat);
		arr1[2] = new Cell(new Coordinates('A', 2), Constants.CellStatus.boat);
		arr1[3] = new Cell(new Coordinates('A', 3), Constants.CellStatus.boat);
		
		arr2[0] = new Cell(new Coordinates('C', 0), Constants.CellStatus.boat);
		arr2[1] = new Cell(new Coordinates('C', 1), Constants.CellStatus.boat);
		arr2[2] = new Cell(new Coordinates('C', 2), Constants.CellStatus.boat);
		arr2[3] = new Cell(new Coordinates('C', 3), Constants.CellStatus.boat);
		arr2[4] = new Cell(new Coordinates('C', 4), Constants.CellStatus.boat);
		
		arr3[0] = new Cell(new Coordinates('A', 6), Constants.CellStatus.boat);
		arr3[1] = new Cell(new Coordinates('B', 6), Constants.CellStatus.boat);
		arr3[2] = new Cell(new Coordinates('C', 6), Constants.CellStatus.boat);
		
		arr4[0] = new Cell(new Coordinates('E', 0), Constants.CellStatus.boat);
		arr4[1] = new Cell(new Coordinates('E', 1), Constants.CellStatus.boat);
		
		arr5[0] = new Cell(new Coordinates('E',	8), Constants.CellStatus.boat);
		arr5[1] = new Cell(new Coordinates('F', 8), Constants.CellStatus.boat);
		arr5[2] = new Cell(new Coordinates('G', 8), Constants.CellStatus.boat);
		
		cells.add(arr1);
		cells.add(arr2);
		cells.add(arr3);
		cells.add(arr4);
		cells.add(arr5);
		
		return cells;
	}




	
}
