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
		String[] cellsParam = request.getParameterValues("spot");
		//Cell[] cells = battleShipService.convertCellArr(spot);
		
		System.out.println("Con username: " + username);
		System.out.println("Con turn:" + turn);
		System.out.println("Con cellsParam : " + cellsParam.length);
		
		return battleShipService.updateBattleShipGame(username, turn, cellsParam);
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
