package DTOs;

import Fachkonzept.Produkt;

public class ProduktDTO {

	public ProduktDTO(Produkt p, int menge) {
		this.menge = menge;
		this.p=p;
	}
	
	private Produkt p;
	private int menge;
	public Produkt getP() {
		return p;
	}
	public void setP(Produkt p) {
		this.p = p;
	}
	public int getMenge() {
		return menge;
	}
	public void setMenge(int menge) {
		this.menge = menge;
	}
	
	

}
