package fachkonzept;

import java.util.Map;

import fachkonzept.markt.Markteinheit;
import fachkonzept.util.MaschinenArt;
import fachkonzept.util.MitarbeiterFachgebiet;
import fachkonzept.util.Werkzeuge;

public class Maschine extends Markteinheit{
	
	private MaschinenArt maschinenArt;
	private int kapazitaet;
	private int auslastung;
	private Produktionsmatrix matrix;
	private double fertigungskosten;
	private int arbeitszeit;     
	//jede maschine hat einen bestimmten menschlichen arbeitsaufwand
	//!!! pro produzierter einheit in minuten
	
	private Produkt p;
		
	public Maschine(MaschinenArt ma, int k, Produkt pt, Produktionsmatrix m, double fertigungskosten, int minuten) {
		super();
		this.maschinenArt = ma;
		this.setName(ma.name());
		this.kapazitaet = k;
		this.p = pt;
		this.matrix = m;
		this.fertigungskosten = Werkzeuge.runde2Stellen(fertigungskosten);
		this.arbeitszeit = minuten;
	}
	
	public Maschine(Maschine angebot) {
	    this.maschinenArt = angebot.maschinenArt;
	    this.setName(angebot.getName());
	    this.kapazitaet = angebot.kapazitaet;
	    this.fertigungskosten = angebot.fertigungskosten;
	    this.matrix = angebot.matrix;
	    this.p = angebot.p;
	    this.arbeitszeit = angebot.arbeitszeit;
	}
	
	

	public Produktionsmatrix getMatrix() {
		return matrix;
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

	public Produkt produziere(int menge, Unternehmen eigentuemer){
		
		this.auslastung += menge;

	    for (Map.Entry<String, Integer> ein : this.matrix.getMatrix().entrySet()) {
	        eigentuemer.materialEntfernen(ein.getKey(), ein.getValue() * menge);
	    }
	    eigentuemer.beschaeftigeMitarbeiter(MitarbeiterFachgebiet.MASCHINE, menge * arbeitszeit);
	    eigentuemer.produktHinzu(this.p, menge);
	    eigentuemer.kosten(this.fertigungskosten * menge, "Fertigungskosten");
		return this.p;
	}
	
    public double getFertigungskosten() {
        return fertigungskosten;
    }

    public void setFertigungskosten(double fertigungskosten) {
        this.fertigungskosten = fertigungskosten;
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

    public MaschinenArt getMaschinenArt() {
        return maschinenArt;
    }

    public void setMaschinenArt(MaschinenArt maschinenArt) {
        this.maschinenArt = maschinenArt;
        this.setName(maschinenArt.name());
    }
    
	
}