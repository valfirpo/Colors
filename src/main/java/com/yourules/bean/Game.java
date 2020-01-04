package com.yourules.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name  = "yr_game")
public class Game 
{
	@Id
	@Column(name = "game_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "YR_GAME_SEQ")
	@SequenceGenerator(name = "YR_GAME_SEQ", sequenceName = "YR_GAME_SEQ")
	private int id;
	
	@Column(name = "game_name")
	private String name;
	
	@Column(name = "game_plays")
	private int plays;
	
	@Column(name = "game_public")
	private int pub;
	
	@Column(name = "game_user_id")
	private int userId;
	
	public Game(int id, String name, int plays, int pub, int userId) {
		this.id = id;
		this.name = name;
		this.plays = plays;
		this.pub = pub;
		this.userId = userId;
	}
	
	public Game(String name, int plays, int pub, int userId) {
		this.name = name;
		this.plays = plays;
		this.pub = pub;
		this.userId = userId;
	}
	
	public Game(int id, String name, int plays, int pub, int userId, int drinksThisRound) {
		super();
		this.id = id;
		this.name = name;
		this.plays = plays;
		this.pub = pub;
		this.userId = userId;
	}
	
	public Game() {}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPlays() {
		return plays;
	}
	public void setPlays(int plays) {
		this.plays = plays;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getPub() {
		return pub;
	}

	public void setPub(int pub) {
		this.pub = pub;
	}

	@Override
	public String toString() {
		return "Game [id=" + id + ", name=" + name + ", plays=" + plays + ", pub=" + pub + ", userId=" + userId + "]";
	}
}
