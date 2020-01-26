package com.yourules.bean;

import java.util.ArrayList;

public class StringGame extends GameTemplate{

	private Integer maxWords;
	private ArrayList<String> words;
	
	public StringGame(String player1, String player2, Status status) {
		super(player1, player2, status);
		words = new ArrayList<>();
		maxWords = 5;
	}
	public ArrayList<String> getWords() {
		return words;
	}
	public void setWords(ArrayList<String> words) {
		this.words = words;
	}
	public void addWord(String word){
		words.add(word);
	}
	public Integer getMaxWords() {
		return maxWords;
	}
	public void setMaxWords(Integer maxWords) {
		this.maxWords = maxWords;
	}
	@Override
	public String toString() {
		return "StringGame [words=" + words + ", player1=" + player1 + ", player2=" + player2 + ", turn=" + turn
				+ ", status=" + status + "]";
	}
}
