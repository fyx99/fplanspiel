package fachkonzept;

import java.util.HashMap;
import java.util.Map;

import dto.MarktDTO;
import dto.MaschinenGesamtDTO;
import dto.MaterialienGesamtDTO;
import dto.ProdukteGesamtDTO;
import dto.UnternehmenDTO;
import fachkonzept.marketing.Marketingmix;
import fachkonzept.markt.Absatzmarkt;
import fachkonzept.markt.Beschaffungsmarkt;
import fachkonzept.markt.Markteinheit;
import fachkonzept.markt.Maschinenmarkt;

public class Unternehmen {

	private Spiel spiel;
	
	private String name;

	private Beschaffungsmarkt bmarkt = new Beschaffungsmarkt();

	private Absatzmarkt vmarkt = new Absatzmarkt(this);

	private Maschinenmarkt mmarkt = new Maschinenmarkt();
	
	private float umsatz = 0;
	
	private Marketingmix marketingmix;

	private Map<String, Integer> maschinen = new HashMap<String, Integer>(); // jeweils mit mengen
	private Map<String, Integer> materialien = new HashMap<String, Integer>(); // für den anfang string achtung nichts
																				// falsches einfügen :D
	private Map<String, Integer> produkte = new HashMap<String, Integer>();

	public Unternehmen(String name, Spiel s) {
		spiel = s;
		this.name = name;
	}

	private float kapital = 0;

	public float getKapital() {
		return kapital;
	}

	public void setKapital(float kapital) {
		this.kapital = kapital;
	}

	public void verringereKapital(double d) {
		this.kapital -= d;
	}

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

	public Absatzmarkt getVmarkt() {
		return vmarkt;
	}

	public void setVmarkt(Absatzmarkt vmarkt) {
		this.vmarkt = vmarkt;
	}

	public Maschinenmarkt getMmarkt() {
		return mmarkt;
	}

	public void setMmarkt(Maschinenmarkt mmarkt) {
		this.mmarkt = mmarkt;
	}

    public Marketingmix getMarketingmix() {
        return marketingmix;
    }

    public void maschineHinzu(Maschine m, Integer menge) {
		if (this.maschinen.containsKey(m.getName())) {
			this.maschinen.replace(m.getName(), menge + this.maschinen.get(m.getName()));
		} else
			this.maschinen.putIfAbsent(m.getName(), menge);
	}

	public void materialHinzu(Material m, Integer menge) {
		if (this.materialien.containsKey(m.getName())) {
			this.materialien.replace(m.getName(), menge + this.materialien.get(m.getName()));
		} else
			this.materialien.putIfAbsent(m.getName(), menge);
	}

	public void produktHinzu(Produkt m, Integer menge) {
		if (this.produkte.containsKey(m.getName())) {
			this.produkte.replace(m.getName(), menge + this.produkte.get(m.getName()));
		} else
			this.produkte.putIfAbsent(m.getName(), menge);
	}

	public void maschineEntfernen(Maschine m, Integer menge) {
		if (this.maschinen.containsKey(m.getName())) {
			this.maschinen.replace(m.getName(), this.maschinen.get(m.getName()) - menge);
		}
	}

	public void materialEntfernen(Material m, Integer menge) {
		if (this.materialien.containsKey(m.getName())) {
			this.materialien.replace(m.getName(), this.materialien.get(m.getName()) - menge);
		}
	}

	public void materialEntfernen(String s, Integer menge) {
		if (this.materialien.containsKey(s)) {
			this.materialien.replace(s, this.materialien.get(s) - menge);
		}
	}

	public void produktEntfernen(Produkt m, Integer menge) {
		if (this.produkte.containsKey(m.getName()) && this.produkte.get(m.getName()) - menge > 0) {
			this.produkte.replace(m.getName(), this.produkte.get(m.getName()) - menge);
		}
		else if (this.produkte.containsKey(m.getName()) && this.produkte.get(m.getName()) - menge <= 0) {
			this.produkte.remove(m.getName());
		}
	}
	
	public void markteinheitEntfernen(Markteinheit m, Integer menge) {
		if(m instanceof Maschine) {
			maschineEntfernen((Maschine)m, menge);
		}
		else if (m instanceof Material) {

			materialEntfernen((Material)m, menge);
			
		}
		else if (m instanceof Produkt) {

			produktEntfernen((Produkt)m, menge);
		}
	}

	public Map<String, Integer> getMaschinen() {
		return maschinen;
	}

	public void setMaschinen(Map<String, Integer> maschinen) {
		this.maschinen = maschinen;
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
	
	public void umsatz(double d) {
		this.umsatz += d;
	}

	public float getUmsatz() {
		// TODO Auto-generated method stub
		return umsatz;
	}
	
	public MaterialienGesamtDTO zeigeMaterialien(){
		/*Map<String, Integer> res = new HashMap<String, Integer>();
	    for (Map.Entry<String, Integer> ein : this.maschinen.entrySet()) {
	    	res.put(Maschine.findeMaschine(ein.getKey()).getId() + "", ein.getValue());
	    }
	    return res;*/

		return new MaterialienGesamtDTO(this.materialien);
	}

	public MaschinenGesamtDTO zeigeMaschinen() {
		return new MaschinenGesamtDTO(this.maschinen);
	}
	public ProdukteGesamtDTO zeigeProdukte() {
		return new ProdukteGesamtDTO(this.produkte);
	}

	public Spiel getSpiel() {
		return spiel;
	}

	public void setSpiel(Spiel sp) {
		this.spiel = sp;
	}
	
	public static UnternehmenDTO getDTO(Unternehmen u) {
		// TODO Auto-generated constructor stub
		UnternehmenDTO uu = new UnternehmenDTO();
		uu.setName(u.getName());
		uu.setBmarkt(new MarktDTO(u.getBmarkt().getAngebote()));
		uu.setVmarkt(new MarktDTO(u.getVmarkt().getAngebote()));
		uu.setMmarkt(new MarktDTO(u.getMmarkt().getAngebote()));
		
		uu.setUmsatz(u.getUmsatz());
		uu.setKapital(u.getKapital());
		uu.setMaterialien(new MaterialienGesamtDTO(u.getMaterialien()));
		uu.setMaschinen(new MaschinenGesamtDTO(u.getMaschinen()));
		uu.setProdukte(new ProdukteGesamtDTO(u.getProdukte()));
		return uu;
	}

    public void kosten(String string, double fertigungskosten) {
        this.verringereKapital(fertigungskosten);
        
    }
	
	

}
