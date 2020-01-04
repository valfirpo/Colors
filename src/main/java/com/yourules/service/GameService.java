package com.yourules.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.yourules.bean.Game;
import com.yourules.dao.GameRepository;

@Transactional
public class GameService 
{
	@Autowired
	GameRepository gRep;
	
	public List<Game> getGameAll()
	{
		return gRep.findAll();
	}

	public List<Game> getGameByUserId(int userId)
	{
		return gRep.findByUserId(userId);
	}
	
	public void addGame(String gameName, int userId)
	{
		Game game = new Game(gameName, 0, 0, userId);
		gRep.save(game);
	}
	
	public void addGame(Game game)
	{
		gRep.save(game);
	}
	
	public List<Game> getPublicGames()
	{
		List<Game> games= gRep.findByPub(1);
		
		for(Game g : games)
		{
			g.setUserId(-1);
		}
		
		return games;
	}
}
