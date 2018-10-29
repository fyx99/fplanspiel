package dto;

import java.util.Map;

import fachkonzept.util.ProduktTyp;

public class UnternehmenStatDTO {
    
	public UnternehmenStatDTO(double umsatz, double kapital, String name, double gewinn, Map<ProduktTyp, Integer> marktanteile, Integer mitarbeiter) {
        super();
        this.umsatz = umsatz;
        this.kapital = kapital;
        this.name = name;
        this.gewinn = gewinn;
        this.marktanteile = marktanteile;
        this.mitarbeiter = mitarbeiter;
    }
	private String name;
    private double umsatz;
	private double kapital;
	private double gewinn;		//Gesamter Gewinn
	private int mitarbeiter;
	private Map<ProduktTyp, Integer> marktanteile;
	
    public double getUmsatz() {
        return umsatz;
    }
    public void setUmsatz(double umsatz) {
        this.umsatz = umsatz;
    }
    public double getKapital() {
        return kapital;
    }
    public void setKapital(double kapital) {
        this.kapital = kapital;
    }
    public double getGewinn() {
        return gewinn;
    }
    public void setGewinn(double gewinn) {
        this.gewinn = gewinn;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
	public Map<ProduktTyp, Integer> getMarktanteile() {
		return marktanteile;
	}
	public void setMarktanteile(Map<ProduktTyp, Integer> marktanteile) {
		this.marktanteile = marktanteile;
	}
	public int getMitarbeiter() {
		return mitarbeiter;
	}
	public void setMitarbeiter(int mitarbeiter) {
		this.mitarbeiter = mitarbeiter;
	}
	

	
	
	

}
