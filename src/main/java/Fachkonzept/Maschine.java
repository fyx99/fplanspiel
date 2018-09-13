package Fachkonzept;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Maschine extends Markteinheit{
	
	private String name;
	private int kapazitaet;
	private int auslastung;
	
	private Produkt p;
		
	public Maschine(String bez, int k, Produkt pt, Produktionsmatrix m) {
		super();
		
		this.name= bez;
		this.kapazitaet = k;
		this.p = pt;
		this.matrix = m;
	}
	
	private Produktionsmatrix matrix;

	public Produktionsmatrix getMatrix() {
		return matrix;
	}

	public void setMatrix(Produktionsmatrix matrix) {
		this.matrix = matrix;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getKapazitaet() {
		return kapazitaet;
	}

	public void setKapazitaet(int kapazitaet) {
		this.kapazitaet = kapazitaet;
	}

	
	public int getAuslastung() {
		return auslastung;
	}

	public Produkt getP() {
		return p;
	}

	public Produkt produziere(int menge, Unternehmen u){
		
		this.auslastung += menge;
		//hier müsste man evt. die auslastung angeben
		
		//und das unternehmen soll rohstoffe bereitstellen
	    
	    for (Map.Entry<String, Integer> ein : this.matrix.getMatrix().entrySet()) {
	    	u.materialEntfernen(ein.getKey(), ein.getValue() * menge);
	    	//materialien verbrauchen
	    }
	    //die produkte in den bestand
		u.produktHinzu(this.p, menge);
		return this.p;
	}
	
	public static Maschine findeMaschine(String name) {
		for(Markteinheit m : Markteinheit.alleMarkteinheiten) {
			if(m instanceof Maschine && ((Maschine) m).getName() == name) {
				return (Maschine)m;
			}
		}
		return null;
		
	}

	
	
	
	
	
}