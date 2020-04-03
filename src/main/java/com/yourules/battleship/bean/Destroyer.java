package com.yourules.battleship.bean;

public class Destroyer extends Boat{
	public Destroyer(){
		super();
		this.name = "Destroyer";
		this.length = Constants.DestroyerLength;
		this.defaultPoints(Constants.DestroyerLength);
	}
}
