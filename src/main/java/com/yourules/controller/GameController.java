package com.yourules.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yourules.bean.Game;
import com.yourules.service.GameService;

@Controller
@RequestMapping(value = "/Game/")
public class GameController 
{
	@Autowired
	GameService gameService;
	
	@RequestMapping(value = "GetByUser.do", method=RequestMethod.GET, produces="application/json")
	@ResponseBody
	public List<Game> getGame(HttpServletRequest request)
	{
		String tempUserId = request.getParameter("id");
		
		return gameService.getGameByUserId(Integer.parseInt(tempUserId));
	}
	
	@RequestMapping(value = "GetByPublic.do", method=RequestMethod.GET, produces="application/json")
	@ResponseBody
	public List<Game> getGamePublic(HttpServletRequest request)
	{
		
		return gameService.getPublicGames();
	}
	
	@RequestMapping(value = "Create.do", method=RequestMethod.GET, produces="application/json")
	@ResponseBody
	public void createGame(HttpServletRequest request)
	{
		String tempName = request.getParameter("name");
		String tempUserId = request.getParameter("userId");
		
		//Integer tempUserId = Integer.parseInt(request.getParameter("userId"));
	
		gameService.addGame(tempName, Integer.parseInt(tempUserId));
	}
	
	@RequestMapping(value = "Update.do", method=RequestMethod.GET, produces="application/json")
	@ResponseBody
	public void updateGame(HttpServletRequest request)
	{
		String tempName = request.getParameter("newName");
		String tempGameId = request.getParameter("gameId");
		String tempGamePlays = request.getParameter("gamePlays");
		String tempPub = request.getParameter("gamePub");
		//String tempPub = "0";
		String tempUserId = request.getParameter("userId");
		
		Game tempGame = new Game(Integer.parseInt(tempGameId), tempName, Integer.parseInt(tempGamePlays), Integer.parseInt(tempPub), Integer.parseInt(tempUserId));
	
		gameService.addGame(tempGame);
	}
}
