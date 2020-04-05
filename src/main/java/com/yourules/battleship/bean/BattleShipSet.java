package com.yourules.battleship.bean;

import java.util.ArrayList;

import com.yourules.battleship.bean.Constants.CellStatus;
import com.yourules.battleship.bean.Constants.ShipStatus;
import com.yourules.bean.GameTemplate;
import com.yourules.util.GamesAvailable;
import com.yourules.util.Status;

public class BattleShipSet {
	
	private String player;
	private Def def; //top boart where your boats are placed
	private Off off; // bottom board where you track hit/miss
	private ArrayList<Boat> boats;

	public BattleShipSet(String player) {

		this.player = player;
		
		this.off = new Off();
		this.def =  new Def();
		
		this.boats = new ArrayList<>();
		
		this.boats.add(new Bomber());
		this.boats.add(new Carrier());
		this.boats.add(new Cruiser());
		this.boats.add(new Destroyer());
		this.boats.add(new Submarine());
	}

	public Off getOff() {
		return off;
	}

	public void setOff(Off off) {
		this.off = off;
	}

	public Def getDef() {
		return def;
	}

	public void setDef(Def def) {
		this.def = def;
	}

	public ArrayList<Boat> getBoats() {
		return boats;
	}

	public void setBoats(ArrayList<Boat> boats) {
		this.boats = boats;
	}
	
	public void setBoatCells(ArrayList<Cell[]> cells) {
		
		for(int i = 0; i < boats.size(); i++){
			boats.get(i).setCells(cells.get(i));
			boats.get(i).setStatus(ShipStatus.alive);
		}
		
	}

	public void printBoards() {
		
		def.printBoard();
		System.out.println("==========");
		off.printBoard();
		
		for(Boat boat : boats){
			System.out.println(boat.toString());
		}
		
	}

	public boolean stillAlive() {
		
		for(Boat boat : boats){
			if(boat.getStatus().equals(ShipStatus.alive)){
				return true;
			}
		}
		return false;
	}

	public String getPlayer() {
		return player;
	}

	public void updateDefBoard(Coordinates pos) {
		
		
	}

	public void updateOffBoard(Coordinates pos, CellStatus stat) {
		
		this.off.updateCell(pos, stat);
		
	}

	public CellStatus updateShips(Coordinates c) {
		
		for(Boat boat : boats){
			if(boat.inCoodinate(c)){
				boat.setHit(c);
				return CellStatus.hit;
			}
		}
		return CellStatus.miss;
	}	
}
