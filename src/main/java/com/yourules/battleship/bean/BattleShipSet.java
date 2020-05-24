package com.yourules.battleship.bean;

import java.util.ArrayList;
import java.util.HashMap;

import com.yourules.battleship.bean.Constants.CellStatus;
import com.yourules.battleship.bean.Constants.ShipStatus;
import com.yourules.battleship.bean.Constants.WeaponType;

public class BattleShipSet {
	
	private String player;
	private boolean playerReady;
	private Def def; //top boart where your boats are placed
	private Off off; // bottom board where you track hit/miss
	private ArrayList<Boat> boats;
	private ArrayList<Weapon> weapons;
	private int hitStreak;
	
	
	public boolean isPlayerReady() {
		return playerReady;
	}

	public void setPlayerReady(boolean playerReady) {
		this.playerReady = playerReady;
	}

	

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
		
		this.weapons = new ArrayList<>();
		this.weapons.add(new Weapon(WeaponType.Air_Strike, 1));
		this.weapons.add(new Weapon(WeaponType.Lazer, 1));
		this.weapons.add(new Weapon(WeaponType.Cannon, 2));
		
		this.hitStreak = 0;
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
		
		for(Boat boat : this.boats){
			if(map.containsKey(boat.getName())){
				boat.setCells(map.get(boat.getName()));
				boat.setStatus(ShipStatus.alive);
			}
		}	
	}

	public void printBoards() {
		
		System.out.println("Player: " + player);
		System.out.println("Ready: " + playerReady);
		System.out.println("Streak: " + hitStreak);
		def.printBoard();
		System.out.println("==========");
		off.printBoard();
		
		for(Boat boat : boats){
			System.out.println(boat.toString());
		}
		
		for(Weapon weapon : weapons){
			System.out.println(weapon.toString());
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
	
	public void increaseHitStreak() {
		
		this.hitStreak++;
		
		if(hitStreak == 3){
			increaseWeapon(WeaponType.Cannon);
		}
		
		if(hitStreak == 5){
			increaseWeapon(WeaponType.Lazer);
		}
		
		if(hitStreak == 7){
			increaseWeapon(WeaponType.Air_Strike);
		}
		
	}
	
	private void increaseWeapon(WeaponType type) {
		
		for(Weapon weapon : weapons){
			if(weapon.getType().equals(type)){
				weapon.increaseCount();
				break;
			}
		}
		
	}
	
	private void decreaseWeapon(WeaponType type) {
		for(Weapon weapon : weapons){
			if(weapon.getType().equals(type)){
				weapon.decreaseCount();
				break;
			}
		}
	}

	public void resetHitStreak() {
		
		this.hitStreak = 0;
		
	}

	public ArrayList<Weapon> getWeapons() {
		return weapons;
	}

	public void setWeapons(ArrayList<Weapon> weapons) {
		this.weapons = weapons;
	}

	public int getHitStreak() {
		return hitStreak;
	}

	public void setHitStreak(int hitStreak) {
		this.hitStreak = hitStreak;
	}
}
