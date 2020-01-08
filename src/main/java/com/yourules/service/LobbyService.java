package com.yourules.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.yourules.bean.GameTemplate;
import com.yourules.bean.Status;
import com.yourules.bean.StringGame;


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

	public List<String> getLobby() {
		
		List<String> list = new ArrayList<String>();
		for(String key : lobby.keySet()){
			list.add(key);
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
		
		if(lobby.containsKey(userToJoin)){
			GameTemplate tempGame = lobby.get(userToJoin);
			tempGame.setOpponent(username);
			if(tempGame.getStatus().equals(Status.WAITING)){
				tempGame.setStatus(Status.JOINED);
			}
			lobby.put(userToJoin, tempGame);
			return tempGame;
			
		} else {
			return null;
		}
	}

	public GameTemplate setGameStarted(String username) {
		
		if(lobby.containsKey(username)){
			GameTemplate tempGame = lobby.get(username);
			if(tempGame.getStatus().equals(Status.JOINED)){
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
			tempGame.setOpponent(username);
			tempGame.setStatus(Status.OVER);
			lobby.put(username, tempGame);
			return tempGame;
			
		} else {
			System.out.println("Game not found");
			return null;
		}
	}
}
