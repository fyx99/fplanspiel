package fachkonzept;

import java.util.Map;

import fachkonzept.markt.Markteinheit;

public class Maschine extends Markteinheit{
	
	private String name;
	private int kapazitaet;
	private int auslastung;
	private double fertigungskosten;
	private int arbeitszeit;     //jede maschine hat einen bestimmten menschlichen arbeitsaufwand
	        //könnte man jetzt untergliederin in maschinen bestücker usw.
	//!!! pro produzierter einheit in minuten
	
	private Produkt p;
		
	public Maschine(String bez, int k, Produkt pt, Produktionsmatrix m, double fertigungskosten, int minuten) {
		super();
		
		this.name= bez;
		this.kapazitaet = k;
		this.p = pt;
		this.matrix = m;
		this.fertigungskosten = fertigungskosten;
		this.arbeitszeit = minuten;
	}
	
	public Maschine(Maschine angebot) {
	    this.name = angebot.name;
	    this.kapazitaet = angebot.kapazitaet;
	    this.fertigungskosten = angebot.fertigungskosten;
	    this.auslastung = angebot.auslastung;
	    this.matrix = angebot.matrix;
	    this.p = angebot.p;
	    this.arbeitszeit = angebot.arbeitszeit;
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
	    u.beschaeftigeMitarbeiter(MitarbeiterFachgebiet.MASCHINE, menge * arbeitszeit);
		u.produktHinzu(this.p, menge);
        u.kosten(this.fertigungskosten * menge, "Fertigungskosten");
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

    public double getFertigungskosten() {
        return fertigungskosten;
    }

    public void setFertigungskosten(double fertigungskosten) {
        this.fertigungskosten = fertigungskosten;
    }

    public int getArbeitsstunden() {
        return arbeitszeit;
    }

    public void setArbeitsstunden(int arbeitsstunden) {
        this.arbeitszeit = arbeitsstunden;
    }

    public int getArbeitszeit() {
        return arbeitszeit;
    }

    public void setArbeitszeit(int arbeitszeit) {
        this.arbeitszeit = arbeitszeit;
    }

    public void setAuslastung(int auslastung) {
        this.auslastung = auslastung;
    }

    public void setP(Produkt p) {
        this.p = p;
    }
	
}