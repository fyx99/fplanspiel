package fachkonzept;

import java.util.List;

public class Standort {
	private double mitarbeiterKosten;
	private double materialKosten;
	private double marketingEinfluss;
	private double fertigungsKosten;
	
	public Standort(List<Double> faktoren) {
		this.mitarbeiterKosten = faktoren.get(0);
		this.materialKosten = faktoren.get(1);
		this.marketingEinfluss = faktoren.get(2);
		this.fertigungsKosten = faktoren.get(3);
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

	
	
}
