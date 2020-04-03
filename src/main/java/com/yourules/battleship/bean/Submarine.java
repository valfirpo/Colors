package com.yourules.battleship.bean;

public class Submarine extends Boat{
	public Submarine(){
		super();
		this.name = "Submarine";
		this.length = Constants.SubmarineLength;
		this.defaultPoints(Constants.SubmarineLength);
	}
}
