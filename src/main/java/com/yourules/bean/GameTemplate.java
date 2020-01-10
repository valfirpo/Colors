package com.yourules.bean;

public abstract class GameTemplate {

	protected String player1;
	protected String player2;
	protected String turn;
	protected Status status;


	public GameTemplate(String player1, String player2, Status status) {
		super();
		this.player1 = player1;
		this.player2 = player2;
		this.turn = null;
		this.status = status;
	}
	public String getPlayer1() {
		return player1;
	}
	public void setPlayer1(String player1) {
		this.player1 = player1;
	}
	public String getPlayer2() {
		return player2;
	}
	public void setPlayer2(String player2) {
		this.player2 = player2;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public String getTurn() {
		return turn;
	}
	public void setTurn(String turn) {
		this.turn = turn;
	}
	@Override
	public String toString() {
		return "GameTemplate [player1=" + player1 + ", player2=" + player2 + ", turn=" + turn + ", status=" + status
				+ "]";
	}
}
