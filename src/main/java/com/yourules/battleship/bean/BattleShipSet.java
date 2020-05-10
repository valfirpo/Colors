package com.yourules.battleship.bean;

import java.util.ArrayList;
import java.util.HashMap;

import com.yourules.battleship.bean.Constants.CellStatus;
import com.yourules.battleship.bean.Constants.ShipStatus;

public class BattleShipSet {
	
	private String player;
	private boolean playerReady;
	public boolean isPlayerReady() {
		return playerReady;
	}

	public void setPlayerReady(boolean playerReady) {
		this.playerReady = playerReady;
	}

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
		
		this.playerReady = false;
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
	
	public void setBoatCells(HashMap<String, Cell[]> map) {
		
		for(String s : map.keySet()){
			System.out.println("map:" + s);
		}
		
		for(Boat boat : this.boats){
			System.out.println("bos: " + boat.getName());
			if(map.containsKey(boat.getName())){
				System.out.println("boat fountd " +boat.getName());
				boat.setCells(map.get(boat.getName()));
				boat.setStatus(ShipStatus.alive);
			}
		}	
	}

	public void printBoards() {
		
		System.out.println("Player: " + player);
		System.out.println("Ready: " + playerReady);
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
	
	public void setPlayer(String username) {
		player = username;
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
