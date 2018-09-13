package Fachkonzept;

import java.util.ArrayList;
import java.util.List;

public class Angebot{
	
	private float preis;
	private int id;
	private static int angebotsnummer = 0;
	private int menge;
	
	private Markteinheit markteinheit;
	
	private Markt markttyp;
	
	private static List<Angebot> alleAngebote = new ArrayList<>();

	//preis pro einheit
	public Angebot(Markteinheit m, int menge, float preis) {
		this.preis = preis;
		this.menge = menge;
		this.markteinheit = m;
		this.id = ++angebotsnummer;
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
	
	public float getPreis() {
		return this.preis;
	}
	
	public Angebot kaufen (int menge) {
		
		this.menge -= menge;
		
		if(menge <= 0) {
			return null;
		}
		return this;
		
		
	}
	public Markt getMarkttyp() {
		return markttyp;
	}
	public void setMarkttyp(Markt markttyp) {
		this.markttyp = markttyp;
	}
	public Markteinheit getMarkteinheit() {
		return markteinheit;
	}
	public void setMarkteinheit(Markteinheit markteinheit) {
		this.markteinheit = markteinheit;
	}
	public void setPreis(float preis) {
		this.preis = preis;
	}
	
	public static Angebot findeAngebot(int id) {
		for (int i = 0; i < alleAngebote.size(); i++) {
			if(alleAngebote.get(i).getId() == id)
				return alleAngebote.get(i);
		}
		return null;
	}
	
	
}