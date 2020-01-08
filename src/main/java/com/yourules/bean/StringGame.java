package com.yourules.bean;

import java.util.ArrayList;

public class StringGame extends GameTemplate{

	private ArrayList<String> words;
	
	public StringGame(String player, String opponent, Status status) {
		super(player, opponent, status);
		words = new ArrayList<>();
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
}
