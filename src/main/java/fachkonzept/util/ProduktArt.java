package fachkonzept.util;

import java.util.ArrayList;
import java.util.List;

public enum ProduktArt {
	Holzstuhl, Stoffstuhl, Lederstuhl, Holztisch, Glastisch, Kunststofftisch, Holzschrank, Edelstahlschrank, Glasschrank;
	
	

	public static List<String> getProduktArten(){
		ProduktArt[] e = ProduktArt.values();
		List<String> returnStrings = new ArrayList<String>();
		for (int i = 0; i < e.length; i++) {
			returnStrings.add("" + e[i]);
		}
		return returnStrings;
	}
}