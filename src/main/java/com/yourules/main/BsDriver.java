package com.yourules.main;

import java.util.Scanner;

import com.yourules.battleship.bean.BattleShip;
import com.yourules.battleship.bean.Coordinates;
import com.yourules.service.BsGenerator;
import com.yourules.util.Status;

public class BsDriver {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		String pos;
		BsGenerator bs = new BsGenerator();
		BattleShip game = new BattleShip("temp", "yemp", Status.STARTED);
		

		game.printBattleShipGame();
		game.getPlayer1Set().setBoatCells(bs.bsCells());
		game.getPlayer2Set().setBoatCells(bs.bsCells());
		game.printBattleShipGame();

		while(!game.isGameOver()){
			System.out.print(game.getTurn() + ": ");
			pos = scan.nextLine();

			String[] arr = pos.split(",");
			Coordinates c = new Coordinates(arr[0], Integer.valueOf(arr[1]) - 1);
			
			game.putInBoard(game.getTurn(), c);
			game.printBattleShipGame();

		}
	}

}
