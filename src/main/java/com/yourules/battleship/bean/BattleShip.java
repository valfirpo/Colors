package com.yourules.battleship.bean;

import com.yourules.battleship.bean.Constants.CellStatus;
import com.yourules.bean.GameTemplate;
import com.yourules.util.GamesAvailable;
import com.yourules.util.Status;

public class BattleShip extends GameTemplate{

	BattleShipSet player1Set;
	BattleShipSet player2Set;
	boolean atLeastOneHit;
	
	public BattleShip(String player1, String player2, Status status){
		super(player1, player2, status);
		type = GamesAvailable.Battle_Ship;
		this.player1Set = new BattleShipSet(player1);
		this.player2Set = new BattleShipSet(player2);
		this.setTurn(this.getPlayer1());
	}

	public void printBattleShipGame(){
		
		player1Set.printBoards();
		player2Set.printBoards();

	}
	
	public BattleShipSet getPlayer1Set() {
		return player1Set;
	}

	public BattleShipSet getPlayer2Set() {
		return player2Set;
	}

	public boolean isGameOver() {
		
		if(this.getStatus().equals(Status.STARTED2)){
			if(!this.getPlayer1Set().stillAlive()){
				System.out.println("##player 1 lost");
				this.setPlayer2Winner();
				return true;
			}
			if(!this.getPlayer2Set().stillAlive()){
				System.out.println("##player 2 lost");
				this.setPlayer1Winner();
				return true;
			}	
		}
		
		
		return false;
	}

	private void setPlayer2Winner() {
		this.setWinner(player2);
		this.setLoser(player1);
	//	this.setStatus(Status.OVER);
	}
	
	private void setPlayer1Winner() {
		this.setWinner(player1);
		this.setLoser(player2);
	//	this.setStatus(Status.OVER);
	}

	public void putInBoard(String turn, Coordinates pos) {
		
		CellStatus stat;
		
		if(player1Set.getPlayer().equals(turn)){
			stat = player2Set.updateShips(pos);
			System.out.println("%%" + stat);
			player1Set.updateOffBoard(pos, stat);
		} else {
			stat = player1Set.updateShips(pos);
			System.out.println("%%" + stat);
			player2Set.updateOffBoard(pos, stat);
		}
		
		if(stat.equals(CellStatus.hit)){
			atLeastOneHit = true;
		}
		//swap();
	}
	
	public void putInBoard(String turn, String weapon, Coordinates[] cells) {
				
		atLeastOneHit = false;
		
		for(Coordinates pos : cells){
			putInBoard(turn,  pos);
		}
		
		
		if(player1Set.getPlayer().equals(turn)){
			if(atLeastOneHit) {
				player1Set.increaseHitStreak();
			} else {
				player1Set.resetHitStreak();
			}
			player1Set.decreaseWeapon(weapon);
		} else {
			if(atLeastOneHit) {
				player2Set.increaseHitStreak();
			} else {
				player2Set.resetHitStreak();
			}
			player2Set.decreaseWeapon(weapon);
		}
		
		
		swap();
	}

	@Override
	public void setPlayer2(String player2) {
		this.player2 = player2;
		this.getPlayer2Set().setPlayer(player2);
	}
}
