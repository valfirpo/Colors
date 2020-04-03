package com.yourules.battleship.bean;

public class Bomber extends Boat{

	public Bomber(){
		super();
		this.name = "Battleship";
		this.length = Constants.BattleshipLength;
		this.defaultPoints(Constants.BattleshipLength);
	}
}
