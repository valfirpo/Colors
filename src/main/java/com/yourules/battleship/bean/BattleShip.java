package com.yourules.battleship.bean;

import java.util.ArrayList;

import com.yourules.bean.GameTemplate;
import com.yourules.util.GamesAvailable;
import com.yourules.util.Status;

public class BattleShip extends GameTemplate{
	
	private Off off;
	private Def def;
	private ArrayList<Boat> boats;

	public BattleShip(String player1, String player2, Status status) {
		super(player1, player2, status);
		type = GamesAvailable.Battle_Ship;
		//boardInit();
		//winningInit();
		//count = 0;
		
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
}
