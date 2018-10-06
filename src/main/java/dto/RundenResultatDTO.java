package dto;


import java.util.List;
import java.util.Map;

import fachkonzept.GuV;
import fachkonzept.Umsatz;
import fachkonzept.Unternehmen;
import fachkonzept.Verbindlichkeit;
import fachkonzept.marketing.Marketingmaßnahme;
import fachkonzept.util.MitarbeiterFachgebiet;

public class RundenResultatDTO {
    
    private String name;    //name des unternehmens
    private double kapital; //wv geld
    private GuV guv;		//Gewinn
    private int mitarbeiterAnzahl;
    //offene (nicht benötigte) Mitarbeiterkapazität in Minuten
    private int mitarbeiterKapazitMaschine; 
    private int mitarbeiterKapazitVertieb;
    private int mitarbeiterKapazitVerwaltung;
    private Map<String, Integer> produkte;
    private List<Verbindlichkeit> verbindlichkeiten;
    private List<Umsatz> umsatzHistorie;
    private List<Marketingmaßnahme> marketingMaßnahmen;
    
    public RundenResultatDTO(Unternehmen u) {
        
        this.name = u.getName();
        this.kapital = u.getKapital();
        this.guv = u.getGuv();
        this.mitarbeiterAnzahl = u.getMitarbeiter().size();
        this.mitarbeiterKapazitMaschine = u.getMitarbeiterKapazitaeten(MitarbeiterFachgebiet.MASCHINE);
        this.mitarbeiterKapazitVertieb = u.getMitarbeiterKapazitaeten(MitarbeiterFachgebiet.VERTRIEB);
        this.mitarbeiterKapazitVerwaltung = u.getMitarbeiterKapazitaeten(MitarbeiterFachgebiet.VERWALTUNG);
        this.produkte = u.getProdukte();
        this.verbindlichkeiten = u.getVerbindlichkeiten();
        this.umsatzHistorie = u.getVmarkt().getUmsatzHistorie();
        this.marketingMaßnahmen = u.getMarketingmix().getMarketing();
    }
    
    //etwas zum absatzmarkt
    
    //evt sowas wie 7 neue angebote auf dem markt für material ...
    
    //das wurde mit der menge verkauft für den und den preis
    
    //kostenaufstellung
    
    public int getMitarbeiterAnzahl() {
		return mitarbeiterAnzahl;
	}

	public void setMitarbeiterAnzahl(int mitarbeiterAnzahl) {
		this.mitarbeiterAnzahl = mitarbeiterAnzahl;
	}

	public List<Umsatz> getUmsatzHistorie() {
		return umsatzHistorie;
	}

	public void setUmsatzHistorie(List<Umsatz> umsatzHistorie) {
		this.umsatzHistorie = umsatzHistorie;
	}

	public List<Marketingmaßnahme> getMarketingMaßnahmen() {
		return marketingMaßnahmen;
	}

	public void setMarketingMaßnahmen(List<Marketingmaßnahme> marketingMaßnahmen) {
		this.marketingMaßnahmen = marketingMaßnahmen;
	}

	public List<Verbindlichkeit> getVerbindlichkeiten() {
		return verbindlichkeiten;
	}


	public void setVerbindlichkeiten(List<Verbindlichkeit> verbindlichkeiten) {
		this.verbindlichkeiten = verbindlichkeiten;
	}


	
	
//	public void getVerkaeufe(Unternehmen u) {
//		List<Umsatz> umsatzHistorie = u.getVmarkt().getUmsatzHistorie();
//	}
    
    
	public int getMitarbeiteranzahl() {
		return mitarbeiterAnzahl;
	}

	public void setMitarbeiterKapazitMaschine(int mitarbeiterKapazitMaschine) {
		this.mitarbeiterKapazitMaschine = mitarbeiterKapazitMaschine;
	}

	public int getMitarbeiterKapazitVertieb() {
		return mitarbeiterKapazitVertieb;
	}

	public void setMitarbeiterKapazitVertieb(int mitarbeiterKapazitVertieb) {
		this.mitarbeiterKapazitVertieb = mitarbeiterKapazitVertieb;
	}

	public int getMitarbeiterKapazitVerwaltung() {
		return mitarbeiterKapazitVerwaltung;
	}

	public void setMitarbeiterKapazitVerwaltung(int mitarbeiterKapazitVerwaltung) {
		this.mitarbeiterKapazitVerwaltung = mitarbeiterKapazitVerwaltung;
	}

	public Map<String, Integer> getProdukte() {
		return produkte;
	}

	public void setProdukte(Map<String, Integer> produkte) {
		this.produkte = produkte;
	}

    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public double getKapital() {
        return kapital;
    }
    public void setKapital(double kapital) {
        this.kapital = kapital;
    }
    public GuV getGuv() {
        return guv;
    }
    public void setGuv(GuV guv) {
        this.guv = guv;
    }

    public int getMitarbeiterKapazitMaschine() {
        return mitarbeiterKapazitMaschine;
    }
    



}
