package com.yourules.main;

import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.yourules.bean.TicTacWoeGame;
import com.yourules.util.Status;

public class Driver {

	public static void main(String[] args) 
	{
		Scanner scan = new Scanner(System.in);
		Integer pos;
		TicTacWoeGame game = new TicTacWoeGame("me", null, Status.WAITING);
		
		game.setPlayer2("nat");
		game.setStatus(Status.JOINED);
		game.setTurn(game.getPlayer1());
		
		System.out.println(game.toString());
		game.printGame();
		
		while(!game.isGameOver() && !game.getStatus().equals(Status.TIED)){
			System.out.print(game.getTurn() + ": ");
			pos = Integer.valueOf(scan.nextLine());
			game.putInBoard(game.getTurn(), pos);
			game.printGame();
		}
		
		if(game.getStatus().equals(Status.OVER)){
			System.out.println(game.getWinner() + " wins!!");
		} else if(game.getStatus().equals(Status.TIED)){
			System.out.println("Game fm tied");
		}

		scan.close();
	}

}
