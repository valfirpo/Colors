package com.yourules.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yourules.bean.GameTemplate;
import com.yourules.bean.StringGame;
import com.yourules.service.LobbyService;

@Controller
@RequestMapping(value = "/Game/StringGame/")
public class StringGameController {
	
	@Autowired
	LobbyService lobbyService;

	@RequestMapping(value = "updateGame.do", method=RequestMethod.POST, produces="application/json")
	@ResponseBody
	public GameTemplate updateGame(HttpServletRequest request)
	{
		String username = request.getParameter("username");
		String newWord = request.getParameter("newWord");
		
		lobbyService.updateStringGame(username, newWord);
		
		return lobbyService.getGame(username); 
	}
}
