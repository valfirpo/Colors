package com.yourules.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import com.yourules.battleship.bean.BattleShip;
import com.yourules.bean.GameTemplate;
import com.yourules.bean.StringGame;
import com.yourules.bean.TicTacWoeGame;
import com.yourules.util.GamesAvailable;
import com.yourules.util.Status;


public class LobbyService {

	private static HashMap<String, GameTemplate> lobby;
	
	public LobbyService(){
		lobby = new HashMap<String, GameTemplate>();
	}
	
	public GameTemplate createGame(String userName){
		StringGame game = new StringGame(userName, null, Status.WAITING);
		lobby.put(userName, game);
		return game;
	}
	
	public GameTemplate createGame(String userName, String gameType){
		GameTemplate game = null;
		
		String gameType_ = gameType.replace(" ", "_");
		
		if(gameType_.equals(GamesAvailable.String_Game.toString())){
			System.out.println("String game");
			 game = new StringGame(userName, null, Status.WAITING);
		} else if(gameType_.equals(GamesAvailable.Tic_Tac_Woe.toString())){
			System.out.println("Tic Tac Woe Game");
			 game = new TicTacWoeGame(userName, null, Status.WAITING);
		} else if(gameType_.equals(GamesAvailable.Battle_Ship.toString())){
			System.out.println("Battale Ship");
			 game = new BattleShip(userName, null, Status.WAITING);
		}		
		
		if(game != null){
			lobby.put(userName, game);
		}
		printLobby();
		return game;
	}
	
	public void put(String username, GameTemplate game){
		lobby.put(username, game);
	}
	
	public List<GameTemplate> getLobby() {
		
		List<GameTemplate> list = new ArrayList<GameTemplate>();
		for(Entry<String, GameTemplate> entry : lobby.entrySet()){
			if(entry.getValue().getStatus().equals(Status.WAITING)){
				list.add(entry.getValue());
			}
		}
		return list;
	}

	public GameTemplate getGame(String username) {
		
		
		if(lobby.containsKey(username)){
			return lobby.get(username);
		} else {
			return null;
		}
		
	}

	public GameTemplate joinGame(String username, String userToJoin) {
		
//		System.out.println("lobby.size(): " + lobby.size());
//		for(Map.Entry<String, GameTemplate> entry : lobby.entrySet()){
//			System.out.println(entry.getKey() + " - " + entry.getValue());
//		}
		
		if(!username.equals(userToJoin)){
			
			if (lobby.containsKey(userToJoin)) {
				GameTemplate tempGame = lobby.get(userToJoin);
				tempGame.setPlayer2(username);
				if (tempGame.getStatus().equals(Status.WAITING)) {
					tempGame.setStatus(Status.JOINED);
					lobby.put(userToJoin, tempGame);
					return tempGame;
				} else {
					return null;
				}

			} else {
				return null;
			}
			
		} else {
			return null;
		}
		
	}

	public GameTemplate setGameStarted(String username) {
		
		if(lobby.containsKey(username)){
			GameTemplate tempGame = lobby.get(username);
			if(tempGame.getStatus().equals(Status.JOINED)){
				tempGame.setTurn(tempGame.getPlayer1());
				tempGame.setStatus(Status.STARTED);
			}
			lobby.put(username, tempGame);
			return tempGame;
			
		} else {
			System.out.println("Game not found");
			return null;
		}
	}
	
	public GameTemplate setGameOver(String username) {
		
		if(lobby.containsKey(username)){
			GameTemplate tempGame = lobby.get(username);
			tempGame.setPlayer2(username);
			tempGame.setStatus(Status.OVER);
			lobby.put(username, tempGame);
			return tempGame;
			
		} else {
			System.out.println("Game not found");
			return null;
		}
	}

	public void updateStringGame(String username, String newWord) {
		
		if(lobby.containsKey(username)){
			StringGame tempGame = (StringGame)lobby.get(username);
			
			tempGame.addWord(newWord);
			
			if(tempGame.getTurn().equals(tempGame.getPlayer1())){
				tempGame.setTurn(tempGame.getPlayer2());
			} else {
				tempGame.setTurn(tempGame.getPlayer1());
			}
			
			if(tempGame.getWords().size() == tempGame.getMaxWords()){
				tempGame.setStatus(Status.OVER);
			}
			
			lobby.put(username, tempGame);
			
		} else {
			System.out.println("Game not found");
		}
		
	}

	public void updateTicTacWoeGame(String username, String turn, Integer spot) {
		if(lobby.containsKey(username)){
			TicTacWoeGame tempGame = (TicTacWoeGame)lobby.get(username);
			
			
			tempGame.printGame();

			tempGame.putInBoard(turn, spot);
			
			tempGame.printGame();

			lobby.put(username, tempGame);
			
		} else {
			System.out.println("Game not found");
		}
	}
	
	public void updateBattleShip(String username, String turn, Integer spot) {
		if(lobby.containsKey(username)){
			BattleShip tempGame = (BattleShip)lobby.get(username);
			
		

			lobby.put(username, tempGame);
			
		} else {
			System.out.println("Game not found");
		}
	}

	public void printLobby() {
		for(Entry<String, GameTemplate> entry : lobby.entrySet()){
			System.out.println(entry.getKey() + " " + entry.getValue().getType() +" " + entry.getValue().getStatus());
		}
		
	}
}
