package com.yourules.bean;

import com.yourules.util.GamesAvailable;
import com.yourules.util.Status;

public abstract class GameTemplate {

	protected String player1;
	protected String player2;
	protected String turn;
	protected Status status;
	protected String winner;
	public String getWinner() {
		return winner;
	}
	public void setWinner(String winner) {
		this.winner = winner;
	}
	protected GamesAvailable type;


	public GameTemplate(String player1, String player2, Status status) {
		super();
		this.player1 = player1;
		this.player2 = player2;
		this.turn = null;
		this.status = status;
		this.type = null;
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
		return "GameTemplate [player1=" + player1 + ", player2=" + player2 + ", turn=" + turn + ", status=" + status + ", type: " + type + ", winner: " + winner +  "]";
	}
	public GamesAvailable getType() {
		return type;
	}
	public void setType(GamesAvailable type) {
		this.type = type;
	}
	public void swap(){
		if(turn.equals(player1)){
		turn = player2;
	} else {
		turn = player1;
	}
	}
}
