package com.yourules.battleship.bean;

public class Carrier extends Boat{
	public Carrier(){
		super();
		this.name = "Carrier";
		this.length = Constants.CarrierLength;
		this.defaultPoints(Constants.CarrierLength);
	}
}
