package com.yourules.bean;

public abstract class GameTemplate {

	private String player;
	private String opponent;
	private Status status;
	
	public GameTemplate(String player, String opponent, Status status) {
		super();
		this.player = player;
		this.opponent = opponent;
		this.status = status;
	}

	public String getPlayer() {
		return player;
	}
	
	public void setPlayer(String player) {
		this.player = player;
	}

	public String getOpponent() {
		return opponent;
	}

	public void setOpponent(String opponent) {
		this.opponent = opponent;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "GameTemplate [player=" + player + ", opponent=" + opponent + ", status=" + status + "]";
	}
}
