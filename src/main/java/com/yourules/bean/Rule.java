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
@Table(name  = "yr_rule")
public class Rule 
{
	@Id
	@Column(name = "rule_id")
	@SequenceGenerator(name = "YR_RULE_SEQ", sequenceName = "YR_RULE_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "YR_RULE_SEQ")
	private int id;
	
	@Column(name = "rule_rule")
	private String rule;
	
	@Column(name = "rule_plays")
	private int used;
	
	@Column(name = "rule_drinks")
	private int drink;
	
	@Column(name = "rule_game_id")
	private int gameId;
	
	public Rule() {}
	
	public Rule(String rule, int used, int drink, int gameId) {
		this.rule = rule;
		this.used = used;
		this.drink = drink;
		this.gameId = gameId;
	}
	
	public Rule(int id, String rule, int used, int drink, int gameId) {
		this.id = id;
		this.rule = rule;
		this.used = used;
		this.drink = drink;
		this.gameId = gameId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRule() {
		return rule;
	}

	public void setRule(String rule) {
		this.rule = rule;
	}

	public int getUsed() {
		return used;
	}

	public void setUsed(int used) {
		this.used = used;
	}

	public int getDrink() {
		return drink;
	}

	public void setDrink(int drink) {
		this.drink = drink;
	}

	public int getGameId() {
		return gameId;
	}

	public void setGameId(int gameId) {
		this.gameId = gameId;
	}

	@Override
	public String toString() {
		return "Rule [id=" + id + ", rule=" + rule + ", used=" + used + ", drink=" + drink + ", gameId=" + gameId + "]";
	}
}


