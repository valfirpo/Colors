package com.yourules.bean;

import java.util.ArrayList;

import com.yourules.util.GamesAvailable;
import com.yourules.util.Status;

public class TicTacWoeGame extends GameTemplate{
	
	String [] board;
	Integer count;
	
	ArrayList<Integer []> winning;

	public TicTacWoeGame(String player1, String player2, Status status) {
		super(player1, player2, status);
		type = GamesAvailable.Tic_Tac_Woe;
		boardInit();
		winningInit();
		count = 0;
	}
	
	public String[] getBoard() {
		return board;
	}

	public void setBoard(String[] board) {
		this.board = board;
	}

	public String getWinner() {
		return winner;
	}

	public void setWinner(String winner) {
		this.winner = winner;
	}

	public ArrayList<Integer[]> getWinning() {
		return winning;
	}

	public void setWinning(ArrayList<Integer[]> winning) {
		this.winning = winning;
	}
	
	private void winningInit() {
		winning = new ArrayList<>();
		
		Integer [] r1 = {0,1,2};
		Integer [] r2 = {3,4,5};
		Integer [] r3 = {6,7,8};
		
		Integer [] c1 = {0,3,6};
		Integer [] c2 = {1,4,7};
		Integer [] c3 = {2,5,8};
		
		Integer [] d1 = {0,4,8};
		Integer [] d2 = {2,4,6};
		
		winning.add(r1);
		winning.add(r2);
		winning.add(r3);
		
		winning.add(c1);
		winning.add(c2);
		winning.add(c3);
		
		winning.add(d1);
		winning.add(d2);
	}

	private void boardInit(){
		board = new String[9];
		for(int i = 0; i < board.length; i++){
			board[i] = "  " + i + "  ";
		}
	}
	
	public void putInBoard(String name, int position){
		board[position] = name;
		count++;
		
		swap();
//		if(turn.equals(player1)){
//			turn = player2;
//		} else {
//			turn = player1;
//		}
	}
	
	public void printGame(){
		System.out.println("+++++++++++++++++++++++++++++++++++");
		System.out.println("\t" +board[0] + "\t" + board[1] + "\t" + board[2]);;
		System.out.println("\t" +board[3] + "\t" + board[4] + "\t" + board[5]);;
		System.out.println("\t" +board[6] + "\t" + board[7] + "\t" + board[8]);;
		System.out.println("+++++++++++++++++++++++++++++++++++");

	}
	
	public boolean isGameOver(){
		
		for(Integer [] arr : winning){
			if(board[arr[0]].equals(player1) && board[arr[1]].equals(player1) && board[arr[2]].equals(player1)){
				winner = player1;
				status = Status.OVER;
				return true;
			}
			
			if(board[arr[0]].equals(player2) && board[arr[1]].equals(player2) && board[arr[2]].equals(player2)){
				winner = player2;
				status = Status.OVER;
				return true;
			}
		}
		
		if(count == 9){
			status = Status.TIED;
		}

		return false;
	}
}
