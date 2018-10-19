package fachkonzept.util;

import java.util.ArrayList;
import java.util.List;

public enum MarketingmaßnahmenArt {
	Fernsehwerbung, MessenKampagne, Plakatwerbung, PRKampagne, Radiowerbung, Sponsoring;
	
	public static List<String> getMarketingmaßnahmenArten(){
		MarketingmaßnahmenArt[] e = MarketingmaßnahmenArt.values();
		List<String> returnStrings = new ArrayList<String>();
		for (int i = 0; i < e.length; i++) {
			returnStrings.add("" + e[i]);
		}
		return returnStrings;
	}
}
