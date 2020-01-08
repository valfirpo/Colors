package com.yourules.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yourules.bean.GameTemplate;
import com.yourules.service.LobbyService;

@Controller
@RequestMapping(value = "/Lobby/")
public class LobbyController {
	
	@Autowired
	LobbyService lobbyService;

	@RequestMapping(value = "Create.do", method=RequestMethod.POST, produces="application/json")
	@ResponseBody
	public void createGame(HttpServletRequest request)
	{
		String username = request.getParameter("username");
				
		lobbyService.createGame(username);
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
	
	@RequestMapping(value = "Join.do", method=RequestMethod.POST, produces="application/json")
	@ResponseBody
	public GameTemplate join(HttpServletRequest request)
	{
		String username = request.getParameter("username");
		String userToJoin = request.getParameter("userToJoin");
		
		return lobbyService.join(username, userToJoin); 
	}
}
