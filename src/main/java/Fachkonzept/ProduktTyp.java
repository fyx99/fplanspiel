package Fachkonzept;

import java.util.ArrayList;
import java.util.List;

public enum ProduktTyp {
	Stuhl, Tisch, Regal;
	
	

	public static List<String> getProduktTypen(){
		ProduktTyp[] e = ProduktTyp.values();
		List<String> returnStrings = new ArrayList<String>();
		for (int i = 0; i < e.length; i++) {
			returnStrings.add("" + e[i]);
		}
		
		return returnStrings;
	}
}
