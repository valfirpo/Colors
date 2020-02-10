package com.yourules.util;

import java.util.ArrayList;

public class StaticMethods {

	public static Object[] getGamesAvailable(){
		ArrayList<String> list = new ArrayList<String>();
		
		for(GamesAvailable val : GamesAvailable.values()){
			list.add(val.toString().replace("_", " "));
		}
		
		return list.toArray();
	}
}
