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

	private Beschaffungsmarkt bmarkt = new Beschaffungsmarkt();

	private Verkaufsmarkt vmarkt = new Verkaufsmarkt();

	private Maschinenmarkt mmarkt = new Maschinenmarkt();

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
	public Beschaffungsmarkt getBmarkt() {
		return bmarkt;
	}
	public void setBmarkt(Beschaffungsmarkt bmarkt) {
		this.bmarkt = bmarkt;
	}
	public Verkaufsmarkt getVmarkt() {
		return vmarkt;
	}
	public void setVmarkt(Verkaufsmarkt vmarkt) {
		this.vmarkt = vmarkt;
	}
	public Maschinenmarkt getMmarkt() {
		return mmarkt;
	}
	public void setMmarkt(Maschinenmarkt mmarkt) {
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
