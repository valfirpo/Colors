package com.yourules.battleship.bean;

public class Constants {
	
	public enum CellStatus{
		open,
		miss,
		hit,
		boat,
	}
	
	public enum GameStatus{
		done,
		playing
	}
	
	public enum ShipStatus{
		notSet,
		sank,
		alive
	}
	
	public static final String open = " ";
	public static final String miss = "X";
	public static final String hit = "#";
	public static final String boat = "^";
	
	//The 5 ships are:  Carrier (occupies 5 spaces), Battleship (4), Cruiser (3), Submarine (3), and Destroyer (2).  
	public final static int CarrierLength = 5;
	public final static int BattleshipLength = 4;
	public final static int CruiserLength = 3;
	public final static int SubmarineLength = 3;
	public final static int DestroyerLength = 2;
	
	//Board
	public final static int BoardSize = 10;
}
