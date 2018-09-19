package fachkonzept.markt;

import fachkonzept.Angebot;
import fachkonzept.Maschine;
import fachkonzept.Unternehmen;

public class Maschinenmarkt extends Markt {

	public Maschinenmarkt() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void kaufen(Angebot a, int menge, Unternehmen k) {
		// TODO Auto-generated method stub
		super.kaufen(a, menge, k);
		
		k.maschineHinzu((Maschine)a.getMarkteinheit(), menge);
	}
	
	
	
	

}
