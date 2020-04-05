package com.yourules.battleship.bean;

public class Bomber extends Boat{

	public Bomber(){
		super();
		this.name = "Bomber";
		this.length = Constants.BomberLength;
		this.defaultPoints(Constants.BomberLength);
	}
}
