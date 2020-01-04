package com.yourules.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yourules.bean.Game;
import java.lang.String;

public interface GameRepository extends JpaRepository<Game, Integer>
{
	public List<Game> findAll();
	
	public Game findByName(String name);
	
	public List<Game> findByUserId(int userId);
	
	public List<Game> findByPub(int pub);
}
