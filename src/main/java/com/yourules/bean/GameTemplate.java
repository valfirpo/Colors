package com.yourules.bean;

public abstract class GameTemplate {

	private String player;
	private String oponent;
	private Status status;
	
	public GameTemplate(String player, String oponent, Status status) {
		super();
		this.player = player;
		this.oponent = oponent;
		this.status = status;
	}
	public String getPlayer() {
		return player;
	}
	public void setPlayer(String player) {
		this.player = player;
	}
	public String getOponent() {
		return oponent;
	}
	public void setOponent(String oponent) {
		this.oponent = oponent;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
}
