package fachkonzept;

public class Standort{
	
	public Standort(double mmarkt, double bmarkt, double vmarkt, double fix) {
		
		
	}
	
	private String name;
	private double faktor_mitarbeiterkosten = 1;
	private double faktor_materialkosten = 1;
	private double faktor_vertriebskosten = 1;
	private double faktor_fixkosten = 1;
	
	public Standort(String standort) {
		switch(standort){
		case "A":  
				name ="A";
				faktor_mitarbeiterkosten = 0.8;
				faktor_materialkosten = 1;
				faktor_vertriebskosten =1.2; 
				faktor_fixkosten = 0.8;
				 break;
		case "B": 
				name ="B";
				faktor_mitarbeiterkosten = 1.2;
				faktor_materialkosten = 0.8;
				faktor_vertriebskosten =0.8; 
				faktor_fixkosten = 1;
				break;
		case "C": 
				name ="C";
				faktor_mitarbeiterkosten = 1;
				faktor_materialkosten = 0.8;
				faktor_vertriebskosten =0.8; 
				faktor_fixkosten = 1.2;
				break;
		case "D": 
				name ="D";
				faktor_mitarbeiterkosten = 1.2;
				faktor_materialkosten = 1;
				faktor_vertriebskosten =0.8; 
				faktor_fixkosten = 0.8;
		break;
		}
	}

	public double getFaktor_mitarbeiterkosten() {
		return faktor_mitarbeiterkosten;
	}


	public double getFaktor_materialkosten() {
		return faktor_materialkosten;
	}


	public double getFaktor_vertriebskosten() {
		return faktor_vertriebskosten;
	}


	public double getFaktor_fixkosten() {
		return faktor_fixkosten;
	}
	
	public String getName() {
		return name;
	}

	
	
	
	
}