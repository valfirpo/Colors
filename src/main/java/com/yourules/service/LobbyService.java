package com.yourules.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

@Transactional
public class LobbyService {

	private static HashMap<String, String> lobby = new HashMap<String, String>();
	
	public void createGame(String userName){
		lobby.put(userName, null);
	}

	public List<String> getLobby() {
		
		List<String> list = new ArrayList<String>();
		for(String key : lobby.keySet()){
			list.add(key);
		}
		return list;
	}
}
