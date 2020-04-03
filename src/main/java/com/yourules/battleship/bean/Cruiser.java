package com.yourules.battleship.bean;

public class Cruiser extends Boat{

	public Cruiser(){
		super();
		this.name = "Cruiser";
		this.length = Constants.CruiserLength;
		this.defaultPoints(Constants.CruiserLength);
	}
}
