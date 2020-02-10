package com.yourules.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yourules.bean.GameTemplate;
import com.yourules.service.LobbyService;
import com.yourules.util.StaticMethods;

@Controller
@RequestMapping(value = "/Lobby/")
public class LobbyController {
	
	@Autowired
	LobbyService lobbyService;

	@RequestMapping(value = "createGame.do", method=RequestMethod.POST, produces="application/json")
	@ResponseBody
	public GameTemplate createGame(HttpServletRequest request)
	{
		String username = request.getParameter("username");
				
		return lobbyService.createGame(username);
	}
	
	@RequestMapping(value = "getGame.do", method=RequestMethod.GET, produces="application/json")
	@ResponseBody
	public GameTemplate getGame(HttpServletRequest request)
	{
		String username = request.getParameter("username");
				
		return lobbyService.getGame(username);
	}
	
	@RequestMapping(value = "getLobby.do", method=RequestMethod.GET, produces="application/json")
	@ResponseBody
	public List<String> getLobby(HttpServletRequest request)
	{
		return lobbyService.getLobby();
	}
	
	@RequestMapping(value = "joinGame.do", method=RequestMethod.POST, produces="application/json")
	@ResponseBody
	public GameTemplate joinGame(HttpServletRequest request)
	{
		String username = request.getParameter("username");
		String userToJoin = request.getParameter("userToJoin");
		
		return lobbyService.joinGame(username, userToJoin); 
	}
	
	@RequestMapping(value = "setGameStarted.do", method=RequestMethod.POST, produces="application/json")
	@ResponseBody
	public GameTemplate setGameStarted(HttpServletRequest request)
	{
		String username = request.getParameter("username");
				
		return lobbyService.setGameStarted(username);
	}
	
	@RequestMapping(value = "setGameOver.do", method=RequestMethod.POST, produces="application/json")
	@ResponseBody
	public GameTemplate setGameOver(HttpServletRequest request)
	{
		String username = request.getParameter("username");
				
		return lobbyService.setGameOver(username);
	}
	
	@RequestMapping(value = "getAppStat.do", method=RequestMethod.GET, produces="application/json")
	@ResponseBody
	public ArrayList<Object[]> getAppStat(HttpServletRequest request)
	{
		ArrayList<Object[]> appStat = new ArrayList<Object[]>();
		
		appStat.add(StaticMethods.getGamesAvailable());
				
		return appStat;
	}
}
