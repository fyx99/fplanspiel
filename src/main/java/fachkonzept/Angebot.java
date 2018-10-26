package fachkonzept;

import java.util.ArrayList;
import java.util.List;

import fachkonzept.markt.Markteinheit;
import fachkonzept.util.Werkzeuge;

public class Angebot{
    
    
    private int menge;
    /*
     * 
     * !!! außer bei material und produkt menge = 1!!
     * 
     * */
	private double attraktivitaet;
	private double preis;
	private int id;
	private static int angebotsnummer = 0;

	
	private Markteinheit markteinheit;
	
	//private Markt markttyp;
	
	private static List<Angebot> alleAngebote = new ArrayList<>();

	//preis pro einheit
	public Angebot(Markteinheit m, int menge, double preis) {
		this.preis = Werkzeuge.runde2Stellen(preis);
		this.menge = menge;
		this.markteinheit = m;
		this.id = angebotsnummer;
		angebotsnummer ++;
		alleAngebote.add(this);
	}
	
	public int getId() {
		return this.id;
	}
	
	public int getMenge() {
		return this.menge;
	}
	
	public void setMenge(int menge) {
		this.menge = menge;
	}
	
	public double getPreis() {
		return this.preis;
	}
	
	public Angebot kaufen (int menge) {
		
		this.menge -= menge;
		
		if(this.menge <= 0) {
			return null;
		}
		return this;
		
		
	}
	
	

	public Markteinheit getMarkteinheit() {
		return markteinheit;
	}

	public void setPreis(double preis) {
		this.preis = Werkzeuge.runde2Stellen(preis);
	}//
	
	public static Angebot findeAngebot(int id) {
		for (int i = 0; i < alleAngebote.size(); i++) {
			if(alleAngebote.get(i).getId() == id)
				return alleAngebote.get(i);
		}
		return null;
	}
	
	public void updateAttraktivitaet(Unternehmen anbieter) {
		this.attraktivitaet = this.preis * ((1000 - anbieter.getMarketingmix().getMarketingStaerke() + anbieter.getStandort().getAngebotsAttraktivitaet()) / 1000);
	}

	public double getAttraktivitaet(Unternehmen anbieter) {
		return this.preis * ((1000 - anbieter.getMarketingmix().getMarketingStaerke() + anbieter.getStandort().getAngebotsAttraktivitaet()) / 1000);
	}	
	
	public double getAttraktivitaet() {
		return attraktivitaet;
	}

	
	
	
	
}