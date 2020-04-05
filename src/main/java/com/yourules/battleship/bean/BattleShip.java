package com.yourules.battleship.bean;

import com.yourules.battleship.bean.Constants.CellStatus;
import com.yourules.bean.GameTemplate;
import com.yourules.util.GamesAvailable;
import com.yourules.util.Status;

public class BattleShip extends GameTemplate{

	BattleShipSet player1Set;
	BattleShipSet player2Set;
	
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
		
		if(!this.getPlayer1Set().stillAlive()){
			return true;
		}
		if(!this.getPlayer2Set().stillAlive()){
			return true;
		}
		return false;
	}

	public void putInBoard(String turn, Coordinates pos) {
		
		CellStatus stat;
		
		if(player1Set.getPlayer().equals(turn)){
			stat = player2Set.updateShips(pos);
			System.out.println(stat);
			player1Set.updateOffBoard(pos, stat);
		} else {
			stat = player1Set.updateShips(pos);
			System.out.println(stat);
			player2Set.updateOffBoard(pos, stat);
		}
		swap();
	}

}
