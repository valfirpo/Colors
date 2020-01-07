package com.yourules.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yourules.bean.GameTemplate;
import com.yourules.bean.Status;
import com.yourules.bean.StringGame;


public class LobbyService {

	private static HashMap<String, GameTemplate> lobby;
	
	public LobbyService(){
		lobby = new HashMap<String, GameTemplate>();
	}
	
	public void createGame(String userName){
		StringGame game = new StringGame(userName, null, Status.WAITING);
		lobby.put(userName, game);
	}

	public List<String> getLobby() {
		
		List<String> list = new ArrayList<String>();
		for(String key : lobby.keySet()){
			list.add(key);
		}
		return list;
	}

	public GameTemplate isGameMatched(String username) {
		
		
		if(lobby.containsKey(username)){
			return lobby.get(username);
		} else {
			return null;
		}
		
		//return list;
	}

	public GameTemplate join(String username, String userToJoin) {
		
//		System.out.println("lobby.size(): " + lobby.size());
//		for(Map.Entry<String, GameTemplate> entry : lobby.entrySet()){
//			System.out.println(entry.getKey() + " - " + entry.getValue());
//		}
		
		if(lobby.containsKey(userToJoin)){
			GameTemplate tempGame = lobby.get(userToJoin);
			tempGame.setOponent(username);
			lobby.put(userToJoin, tempGame);
			return tempGame;
			
		} else {
			return null;
		}
	}
}
