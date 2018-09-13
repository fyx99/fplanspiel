package Fachkonzept;

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
