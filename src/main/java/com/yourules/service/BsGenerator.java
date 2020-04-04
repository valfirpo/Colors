package com.yourules.service;

import com.yourules.battleship.bean.BattleShip;
import com.yourules.bean.GameTemplate;
import com.yourules.util.Status;

public class BsGenerator {

	public GameTemplate bsBS(String username) {
		GameTemplate game = new BattleShip(username, "yemp", Status.STARTED);
		return game;
	}

	
}
