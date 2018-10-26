package fachkonzept;

import java.util.List;

public class Standort {
	private double mitarbeiterKosten;
	private double materialKosten;
	private double marketingEinfluss;
	private double fertigungsKosten;
	private double angebotsAttraktivitaet;
	
	public Standort(List<Double> faktoren) {
		this.mitarbeiterKosten = faktoren.get(0);
		this.materialKosten = faktoren.get(1);
		this.marketingEinfluss = faktoren.get(2);
		this.fertigungsKosten = faktoren.get(3);
		this.angebotsAttraktivitaet = faktoren.get(4);
	}

	public double getMitarbeiterKosten() {
		return mitarbeiterKosten;
	}


	public double getMaterialKosten() {
		return materialKosten;
	}


	public double getMarketingEinfluss() {
		return marketingEinfluss;
	}



	public double getFertigungsKosten() {
		return fertigungsKosten;
	}

	public double getAngebotsAttraktivitaet() {
		return angebotsAttraktivitaet;
	}

	
	
	
	
}
