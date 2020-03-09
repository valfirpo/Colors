package com.yourules.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yourules.bean.GameTemplate;
import com.yourules.service.LobbyService;

@Controller
@RequestMapping(value = "/Game/TicTacWoe/")
public class TicTacWoeController {

	@Autowired
	LobbyService lobbyService;
	
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
		
		
		lobbyService.updateTicTacWoeGame(username, turn, spot);
		
		return lobbyService.getGame(username); 
	}
}
