package com.yourules.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yourules.service.GameService;
import com.yourules.service.LobbyService;

@Controller
@RequestMapping(value = "/Lobby/")
public class LobbyController {
	
	@Autowired
	LobbyService lobbyService;

	@RequestMapping(value = "Create.do", method=RequestMethod.GET, produces="application/json")
	@ResponseBody
	public void createGame(HttpServletRequest request)
	{
		String tempName = request.getParameter("name");
		
		//Integer tempUserId = Integer.parseInt(request.getParameter("userId"));
	
		lobbyService.createGame(tempName);
	}
	
	@RequestMapping(value = "getLobby.do", method=RequestMethod.GET, produces="application/json")
	@ResponseBody
	public List<String> getLobby(HttpServletRequest request)
	{
		String tempName = request.getParameter("name");
		String tempUserId = request.getParameter("userId");
		
		//Integer tempUserId = Integer.parseInt(request.getParameter("userId"));
	
		return lobbyService.getLobby();
	}
}
