package dto;

import java.util.Map;

import fachkonzept.marketing.Marketingmix;

public class UnternehmenDTO {

	private String name;
	
	private String standort;

	private MarktDTO bmarkt;

	private MarktDTO vmarkt;

	private MarktDTO mmarkt;
	
	private Marketingmix marketingmix;

	private double umsatz = 0;
	private double kapital = 0;

	private Map<String, Integer> materialien;
	private MaschinenGesamtDTO maschinen;
	private  Map<String, Integer> produkte;
	private MitarbeiterGesamtDTO mitarbeiter;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public void setStandort(String standort) {
		this.standort = standort;
	}
	
	public String getStandort() {
		return this.standort;
	}
	
	public MarktDTO getBmarkt() {
		return bmarkt;
	}
	public void setBmarkt(MarktDTO bmarkt) {
		this.bmarkt = bmarkt;
	}
	public MarktDTO getVmarkt() {
		return vmarkt;
	}
	public void setVmarkt(MarktDTO vmarkt) {
		this.vmarkt = vmarkt;
	}
	public MarktDTO getMmarkt() {
		return mmarkt;
	}
	public void setMmarkt(MarktDTO mmarkt) {
		this.mmarkt = mmarkt;
	}
	public double getUmsatz() {
		return umsatz;
	}
	public void setUmsatz(float umsatz) {
		this.umsatz = umsatz;
	}
	public double getKapital() {
		return kapital;
	}
	public void setKapital(double kapital) {
		this.kapital = kapital;
	}
	public MaschinenGesamtDTO getMaschinen() {
		return maschinen;
	}
	public void setMaschinen(MaschinenGesamtDTO maschinen) {
		this.maschinen = maschinen;
	}
    public Marketingmix getMarketingmix() {
        return marketingmix;
    }
    public void setMarketingmix(Marketingmix marketingmix) {
        this.marketingmix = marketingmix;
    }
    public MitarbeiterGesamtDTO getMitarbeiter() {
        return mitarbeiter;
    }
    public void setMitarbeiter(MitarbeiterGesamtDTO mitarbeiter) {
        this.mitarbeiter = mitarbeiter;
    }
    public void setUmsatz(double umsatz) {
        this.umsatz = umsatz;
    }
    public Map<String, Integer> getMaterialien() {
        return materialien;
    }
    public void setMaterialien(Map<String, Integer> materialien) {
        this.materialien = materialien;
    }
    public Map<String, Integer> getProdukte() {
        return produkte;
    }
    public void setProdukte(Map<String, Integer> produkte) {
        this.produkte = produkte;
    }
	
	

}
