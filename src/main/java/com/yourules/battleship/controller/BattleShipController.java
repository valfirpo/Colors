package com.yourules.battleship.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.yourules.battleship.bean.Cell;
import com.yourules.battleship.service.BattleShipService;
import com.yourules.bean.GameTemplate;
import com.yourules.service.LobbyService;

@RestController
@RequestMapping(value = "/BattleShip/")
public class BattleShipController {
	
	@Autowired
	LobbyService lobbyService;
	
	@Autowired
	BattleShipService battleShipService;
	
	@RequestMapping(value = "updateGame.do", method=RequestMethod.POST, produces="application/json")
	@ResponseBody
	public GameTemplate updateGame(HttpServletRequest request)
	{
		String username = request.getParameter("username");
		String turn = request.getParameter("turn");
		String spot = request.getParameter("spot");
		Cell cell = battleShipService.convertCell(spot.substring(1,14).replace("\"", "").replace(":", "").replace("y", "").replace("x", ""));
		
		System.out.println(username);
		System.out.println(turn);
		System.out.println(cell.getCoordinate());
		
		return battleShipService.updateTicTacWoeGame(username, turn, cell);
	}
	
	@RequestMapping(value = "setPlayerReady.do", method=RequestMethod.POST, produces="application/json")
	@ResponseBody
	public GameTemplate setPlayerReady(HttpServletRequest request)
	{
		String owner = request.getParameter("owner");
		String username = request.getParameter("username");
		HashMap<String, String> boats = new HashMap<String, String>();
		boats.put("Bomber" ,request.getParameter("Bomber"));
		boats.put("Carrier" ,request.getParameter("Carrier"));
		boats.put("Cruiser" ,request.getParameter("Cruiser"));
		boats.put("Destroyer" ,request.getParameter("Destroyer"));
		boats.put("Submarine" ,request.getParameter("Submarine"));
		
		//battleShipService.updateP2((BattleShip)lobbyService.getGame(owner), username);	
		battleShipService.setPlayerReady(owner, username);				
		battleShipService.updateBoats(owner, username, boats);				

		return lobbyService.getGame(owner); 
	}
}
