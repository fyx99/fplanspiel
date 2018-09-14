package DTOs;

import java.util.HashMap;
import java.util.Map;

import Fachkonzept.Beschaffungsmarkt;
import Fachkonzept.Maschinenmarkt;
import Fachkonzept.Spiel;
import Fachkonzept.Unternehmen;
import Fachkonzept.Verkaufsmarkt;

public class UnternehmenDTO {

	private String name;

	private MarktDTO bmarkt;

	private MarktDTO vmarkt;

	private MarktDTO mmarkt;

	private float umsatz = 0;
	private float kapital = 0;

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
	public float getUmsatz() {
		return umsatz;
	}
	public void setUmsatz(float umsatz) {
		this.umsatz = umsatz;
	}
	public float getKapital() {
		return kapital;
	}
	public void setKapital(float kapital) {
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
	
	

}
