package Fachkonzept;

import java.util.ArrayList;
import java.util.List;

public class Beschaffungsmarkt extends Markt {
	
	//hier angebote die der spieler kaufen kann - 
	
	public Beschaffungsmarkt() {
		// TODO Auto-generated constructor stub
		
	}
	
	@Override
	public void kaufen(Angebot a, int menge, Unternehmen k) {
		// TODO Auto-generated method stub
		super.kaufen(a, menge, k);
		
		k.materialHinzu((Material)a.getMarkteinheit(), menge);
	}
	

}
