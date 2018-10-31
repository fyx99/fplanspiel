package fachkonzept;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dto.MaschinenGesamtDTO;
import dto.ProdukteGesamtDTO;
import fachkonzept.marketing.Marketingmaßnahme;
import fachkonzept.marketing.Marketingmix;
import fachkonzept.markt.Absatzmarkt;
import fachkonzept.markt.Arbeitsmarkt;
import fachkonzept.markt.Beschaffungsmarkt;
import fachkonzept.markt.Finanzmarkt;
import fachkonzept.markt.Markteinheit;
import fachkonzept.markt.Maschinenmarkt;
import fachkonzept.util.MitarbeiterFachgebiet;

public class Unternehmen {

	private Spiel spiel;
	
	private String name;

	private Beschaffungsmarkt bmarkt = new Beschaffungsmarkt();
	private Absatzmarkt vmarkt = new Absatzmarkt();
	private Maschinenmarkt mmarkt = new Maschinenmarkt();
	private Finanzmarkt fmarkt = new Finanzmarkt();
	private Arbeitsmarkt amarkt = new Arbeitsmarkt();

	private double umsatz = 0;
	
	private Marketingmix marketingmix = new Marketingmix();
	private GuV guv = new GuV();
	private Standort standort;

	private List<Maschine> maschinen = new ArrayList<Maschine>(); // jeweils mit mengen
	private Map<String, Integer> materialien = new HashMap<String, Integer>(); // für den anfang string achtung nichts
	private Map<Produkt, Integer> produkte = new HashMap<Produkt, Integer>();		// falsches einfügen :D
    private List<Arbeitskraft> mitarbeiter = new ArrayList<Arbeitskraft>();
    private List<Verbindlichkeit> verbindlichkeiten = new ArrayList();

	public Unternehmen(String name, Spiel s, Standort st) {
		spiel = s;
		this.name = name;
		this.standort = st;
	}

	private double kapital = 0;

	public double getKapital() {
		return kapital;
	}

	public void setKapital(double d) {
		this.kapital = d;
	}

	public void verringereKapital(double d) {
		this.kapital -= d;
	}

	public String getName() {
		return name;
	}

	public Standort getStandort() {
		return standort;
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

    public Finanzmarkt getFmarkt() {
        return fmarkt;
    }

    public void setFmarkt(Finanzmarkt fmarkt) {
        this.fmarkt = fmarkt;
    }

    public Arbeitsmarkt getAmarkt() {
        return amarkt;
    }

    public void setAmarkt(Arbeitsmarkt amarkt) {
        this.amarkt = amarkt;
    }

    public void maschineHinzu(Maschine m, Integer menge) {
		for(int i = 0; i < menge; i++) {
		    this.maschinen.add(new Maschine(m));
        }
	}

	public void materialHinzu(Material m, Integer menge) {
	    Spiel.log(m.getId() + " am hinzufügen " + m.getName());
		if (this.materialien.containsKey(m.getName())) {
			this.materialien.replace(m.getName(), menge + this.materialien.get(m.getName()));
		} else
			this.materialien.putIfAbsent(m.getName(), menge);
	}

    public void produktHinzu(Produkt m, Integer menge) {
        if (this.produkte.containsKey(m)) {
            this.produkte.replace(m, menge + this.produkte.get(m));
        } else
            this.produkte.putIfAbsent(m, menge);
    }

    
    public void verbindlichkeitHinzu(Verbindlichkeit v) {
        verbindlichkeiten.add(v);
        this.kapital += v.getVerbleibendeSumme();
    }
    
    public void arbeitskraftHinzu(Arbeitskraft ak) {
        mitarbeiter.add(ak);
    }

	public void maschineEntfernen(Maschine m) {
		maschinen.remove(m);
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
        if (this.produkte.containsKey(m) && this.produkte.get(m) - menge > 0) {
            this.produkte.replace(m, this.produkte.get(m) - menge);
        }
        else if (this.produkte.containsKey(m) && this.produkte.get(m) - menge <= 0) {
            this.produkte.remove(m);
        }
    }
    
    public void arbeitskraftEntfernen(Arbeitskraft ak) {
        mitarbeiter.remove(ak);
    }
    
    public void arbeitskraftEntfernen(int id) {
		for (Arbeitskraft a : this.mitarbeiter) {
			if (a.getM().getId() == id) {
				this.arbeitskraftEntfernen(a);
				return;
			}
		}
    }
    
    public void verbindlichkeitEntfernen(Verbindlichkeit v) {
        verbindlichkeiten.remove(v);
    }
	
	public void markteinheitEntfernen(Markteinheit m, Integer menge) {
	    //hier will ich irgendwas auf den markt werfen
	    //menge ist nur bei material und produkten angebracht
		if(m instanceof Maschine) {
			maschineEntfernen((Maschine)m);
		}
		else if (m instanceof Material) {

			materialEntfernen((Material)m, menge);
			
		}
        else if (m instanceof Produkt) {

            produktEntfernen((Produkt)m, menge);
        }   
	
	}
	
	public Map<String, Integer> getMaterialien() {
		return materialien;
	}

	public List<Maschine> getMaschinen() {
        return maschinen;
    }

	public Map<Produkt, Integer> getProdukte() {
		return produkte;
	}
    
    public List<Arbeitskraft> getMitarbeiter() {
        return mitarbeiter;
    }


    public List<Verbindlichkeit> getVerbindlichkeiten() {
        return verbindlichkeiten;
    }
   

    public void umsatz(double summe, String beschreibung) {
		this.umsatz += summe;
		this.kapital += summe;    //soll ja auch geld geben
        this.guv.neueEinnahme(new Zahlung(summe, this.getSpiel().getRunde(), beschreibung));
	}

    public void kosten(double kosten, String beschreibung) {
        this.verringereKapital(kosten);
        //sollte das ganze natürlich noch in einer form überblicksweise haben
        this.guv.neueAusgabe(new Zahlung(kosten, this.spiel.getRunde(), beschreibung));
        
    }
    
    public boolean beschaeftigeMitarbeiter(MitarbeiterFachgebiet mfg, int minuten) {
        //einfach mal bei a anfangen und z aufhören
        int verteilteZeit = minuten;
        for(Arbeitskraft arbeitskraft : this.mitarbeiter) {
            if(arbeitskraft.getM().getMfg().compareTo(mfg) == 0 && !arbeitskraft.isAusgelastet()) {
                int tatZeit = Math.min(minuten, (arbeitskraft.getM().getArbeitszeit() - arbeitskraft.getAuslastung())); //was noch geht wenn zuviel
                arbeitskraft.auslastungErhoeen(tatZeit);
                verteilteZeit -= tatZeit;
                if(verteilteZeit <= 0)
                    return true;
            }
        }
        Spiel.log("Arbeitszeit konnte nicht verteilt werden " + verteilteZeit);
        return false;
        
    }
    
    public int getMitarbeiterKapazitaeten(MitarbeiterFachgebiet mfg){
        int vorhandeneZeit = 0;
        for(Arbeitskraft ak : this.mitarbeiter) {
            if(mfg.name().equals(ak.getM().getMfg().name())) {
                vorhandeneZeit += (ak.getM().getArbeitszeit() - ak.getAuslastung());
            }
        }
        
        return vorhandeneZeit;
    }
    
    public Map<MitarbeiterFachgebiet, Integer> getMitarbeiterKapazitaeten(){
        Map<MitarbeiterFachgebiet, Integer> map = new HashMap<MitarbeiterFachgebiet, Integer>();
        map.put(MitarbeiterFachgebiet.MASCHINE, getMitarbeiterKapazitaeten(MitarbeiterFachgebiet.MASCHINE));
        map.put(MitarbeiterFachgebiet.VERWALTUNG, getMitarbeiterKapazitaeten(MitarbeiterFachgebiet.VERWALTUNG));
        map.put(MitarbeiterFachgebiet.VERTRIEB, getMitarbeiterKapazitaeten(MitarbeiterFachgebiet.VERTRIEB));
        return map;
    }

	public double getUmsatz() {
		return umsatz;
	}
	
	public  Map<String, Integer> zeigeMaterialien(){
		return this.materialien;
	}

	public MaschinenGesamtDTO zeigeMaschinen() {
		return new MaschinenGesamtDTO(this.maschinen);
	}
	public  ProdukteGesamtDTO zeigeProdukte() {
		return new ProdukteGesamtDTO(produkte);
	}

	public Spiel getSpiel() {
		return spiel;
	}
	
	public GuV getGuv() {
        return guv;
    }
	

	
	public void rundenReset() {
	    //nach jeder runde müssen bestimmte werte zurückgesetzt werden
	    //1. mitarbeiter werden entlastet
	    for(Arbeitskraft ak : mitarbeiter) {
            ak.setAuslastung(0);
        }
	    //2. maschinen auslastung
	    for(Maschine m : maschinen) {
	        m.setAuslastung(0);
	    }
	    
	    this.getMarketingmix().setMarketing(new ArrayList<Marketingmaßnahme>());
  
	}
	

}
