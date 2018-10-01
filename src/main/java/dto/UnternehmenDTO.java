package dto;

import fachkonzept.marketing.Marketingmix;

public class UnternehmenDTO {

	private String name;

	private MarktDTO bmarkt;

	private MarktDTO vmarkt;

	private MarktDTO mmarkt;
	
	private Marketingmix marketingmix;

	private double umsatz = 0;
	private double kapital = 0;

	private MaterialienGesamtDTO materialien;
	private MaschinenGesamtDTO maschinen;
	private ProdukteGesamtDTO produkte;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public MaterialienGesamtDTO getMaterialien() {
		return materialien;
	}
	public void setMaterialien(MaterialienGesamtDTO materialien) {
		this.materialien = materialien;
	}
	public MaschinenGesamtDTO getMaschinen() {
		return maschinen;
	}
	public void setMaschinen(MaschinenGesamtDTO maschinen) {
		this.maschinen = maschinen;
	}
	public ProdukteGesamtDTO getProdukte() {
		return produkte;
	}
	public void setProdukte(ProdukteGesamtDTO produkte) {
		this.produkte = produkte;
	}
    public Marketingmix getMarketingmix() {
        return marketingmix;
    }
    public void setMarketingmix(Marketingmix marketingmix) {
        this.marketingmix = marketingmix;
    }
	
	

}
