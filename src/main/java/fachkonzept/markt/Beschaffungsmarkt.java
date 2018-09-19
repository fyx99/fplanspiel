package fachkonzept.markt;

import fachkonzept.Angebot;
import fachkonzept.Material;
import fachkonzept.Unternehmen;

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
