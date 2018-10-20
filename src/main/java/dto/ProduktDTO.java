package dto;

import fachkonzept.Produkt;

public class ProduktDTO {

	public ProduktDTO(Produkt p, int menge) {
		this.menge = menge;
		this.produkt=p;
	}
	
	private Produkt produkt;
	private int menge;
	
	public Produkt getP() {
		return produkt;
	}
	public void setP(Produkt p) {
		this.produkt = p;
	}
	public int getMenge() {
		return menge;
	}
	public void setMenge(int menge) {
		this.menge = menge;
	}
	
	

}
