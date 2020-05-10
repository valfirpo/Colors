package com.yourules.battleship.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.yourules.battleship.bean.BattleShip;
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
		Integer spot = Integer.valueOf(request.getParameter("spot"));
		
		System.out.println(username);
		System.out.println(turn);
		System.out.println(spot);
		
		
		//lobbyService.updateTicTacWoeGame(username, turn, spot);
		
		
		return lobbyService.getGame(username); 
	}
	
	@RequestMapping(value = "setPlayerReady2.do", method=RequestMethod.POST, produces="application/json")
	@ResponseBody
	public GameTemplate setPlayerReady2(HttpServletRequest request)
	{
		String owner = request.getParameter("owner");
		String username = request.getParameter("username");
		HashMap<String, String> boats = new HashMap<String, String>();
		boats.put("Bomber" ,request.getParameter("Bomber"));
		boats.put("Carrier" ,request.getParameter("Carrier"));
		boats.put("Cruiser" ,request.getParameter("Cruiser"));
		boats.put("Destroyer" ,request.getParameter("Destroyer"));
		boats.put("Submarine" ,request.getParameter("Submarine"));
		
		battleShipService.updateP2((BattleShip)lobbyService.getGame(owner), username);	
		battleShipService.updateBoats(owner, username, boats);				
		
		return lobbyService.getGame(owner); 
	}
	
	@RequestMapping(value = "setPlayerReady.do", method=RequestMethod.POST, produces="application/json")
	@ResponseBody
	public GameTemplate setPlayerReady(HttpServletRequest request)
	{
		String owner = request.getParameter("owner");
		String username = request.getParameter("username");

		//battleShipService.updateP2((BattleShip)lobbyService.getGame(owner), username);	
		battleShipService.setPlayerReady(owner, username);				
		
		return lobbyService.getGame(owner); 
	}
}
