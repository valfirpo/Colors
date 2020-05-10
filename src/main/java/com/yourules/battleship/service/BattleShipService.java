package com.yourules.battleship.service;

import java.util.HashMap;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;

import com.yourules.battleship.bean.BattleShip;
import com.yourules.battleship.bean.Cell;
import com.yourules.battleship.bean.Constants.CellStatus;
import com.yourules.battleship.bean.Coordinates;
import com.yourules.service.LobbyService;
import com.yourules.util.Status;

public class BattleShipService {
	
	@Autowired
	LobbyService lobbyService;

	public void updateBoats(String owner, String username, HashMap<String, String> boats) {
		
		
		BattleShip game = (BattleShip)lobbyService.getGame(owner);
		HashMap<String, Cell[]> boatCells = convertMap(boats);
		
		game.printBattleShipGame();
		
		if(game.getPlayer1().equals(username)){
			game.getPlayer1Set().setBoatCells(boatCells);
		}else if(game.getPlayer2().equals(username)){
			game.getPlayer2Set().setBoatCells(boatCells);
		}  
		
		game.printBattleShipGame();
		lobbyService.put(owner, game);
	}
	
	private HashMap<String, Cell[]> convertMap(HashMap<String, String> boatStr) {
		
		HashMap<String, Cell[]> boatCells = new HashMap<String, Cell[]>();
		
		for(Entry<String, String> entry : boatStr.entrySet()){
			boatCells.put(entry.getKey(), convertCellArr(entry.getValue()));
		}
		
		return boatCells;
	}

	private Cell[] convertCellArr(String str) {
		
		String[] arr = str.split("\"coordinate\":");
		Cell[] cells = new Cell[arr.length - 1];
		
		for(int i = 0; i < cells.length; i++){
			cells[i] = convertCell(arr[i + 1].substring(1,14)
					.replace("\"", "").replace(":", "").replace("y", "").replace("x", ""));
		}
		
		return cells;
	}

	public Cell convertCell(String str) {
		
		String[] arr = str.split(",");
		return new Cell(new Coordinates(arr[0], Integer.valueOf(arr[1])), CellStatus.boat);
	}

	public void updateP2(BattleShip game, String username) {
				
		if(game.getPlayer2().equals(username)){
			game.getPlayer2Set().setPlayer(username);
		}
	}

	public void setPlayerReady(String owner, String username) {
		
		BattleShip game = (BattleShip)lobbyService.getGame(owner);
				
		if(game.getPlayer1().equals(username)){
			game.getPlayer1Set().setPlayerReady(true);
		}else if(game.getPlayer2().equals(username)){
			game.getPlayer2Set().setPlayerReady(true);
		}  
		
		if(game.getPlayer1Set().isPlayerReady() && game.getPlayer2Set().isPlayerReady()){
			game.setStatus(Status.STARTED2);
		}
		
		game.printBattleShipGame();
		lobbyService.put(owner, game);
	}
	
	public void setPlayerNotReady(String owner, String username) {
		
		BattleShip game = (BattleShip)lobbyService.getGame(owner);
		
		game.printBattleShipGame();
		
		if(game.getPlayer1().equals(username)){
			game.getPlayer1Set().setPlayerReady(false);
		}else if(game.getPlayer2().equals(username)){
			game.getPlayer2Set().setPlayerReady(false);
		}  
		
		game.printBattleShipGame();
		lobbyService.put(owner, game);
	}

	public BattleShip updateTicTacWoeGame(String owner, String turn, Cell pos) {
		
		BattleShip game = (BattleShip)lobbyService.getGame(owner);
		
		//String[] arr = pos.split(",");
		//Coordinates c = new Coordinates(arr[0], Integer.valueOf(arr[1]) - 1);
		Coordinates c = pos.getCoordinate();
		
		game.putInBoard(game.getTurn(), c);
		lobbyService.put(owner, game);
		
		game.printBattleShipGame();
		
		return game;
	}

	
}
