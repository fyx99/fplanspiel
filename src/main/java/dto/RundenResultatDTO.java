package dto;


import java.util.List;
import java.util.Map;

import fachkonzept.GuV;
import fachkonzept.Produkt;
import fachkonzept.Umsatz;
import fachkonzept.Unternehmen;
import fachkonzept.Verbindlichkeit;
import fachkonzept.marketing.Marketingmaßnahme;
import fachkonzept.util.MitarbeiterFachgebiet;

public class RundenResultatDTO {
    
    private String name;    //name des unternehmens
    private Double kapital; //wv geld
    private GuV guv;		//Gewinn
    private Integer mitarbeiterAnzahl;
    //offene (nicht benötigte) Mitarbeiterkapazität in Minuten
    private Integer mitarbeiterKapazitMaschine; 
    private Integer mitarbeiterKapazitVertieb;
    private Integer mitarbeiterKapazitVerwaltung;
    private Map<Produkt, Integer> produkte;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getKapital() {
		return kapital;
	}

	public void setKapital(Double kapital) {
		this.kapital = kapital;
	}

	public GuV getGuv() {
		return guv;
	}

	public void setGuv(GuV guv) {
		this.guv = guv;
	}

	public Integer getMitarbeiterAnzahl() {
		return mitarbeiterAnzahl;
	}

	public void setMitarbeiterAnzahl(Integer mitarbeiterAnzahl) {
		this.mitarbeiterAnzahl = mitarbeiterAnzahl;
	}

	public Integer getMitarbeiterKapazitMaschine() {
		return mitarbeiterKapazitMaschine;
	}

	public void setMitarbeiterKapazitMaschine(Integer mitarbeiterKapazitMaschine) {
		this.mitarbeiterKapazitMaschine = mitarbeiterKapazitMaschine;
	}

	public Integer getMitarbeiterKapazitVertieb() {
		return mitarbeiterKapazitVertieb;
	}

	public void setMitarbeiterKapazitVertieb(Integer mitarbeiterKapazitVertieb) {
		this.mitarbeiterKapazitVertieb = mitarbeiterKapazitVertieb;
	}

	public Integer getMitarbeiterKapazitVerwaltung() {
		return mitarbeiterKapazitVerwaltung;
	}

	public void setMitarbeiterKapazitVerwaltung(Integer mitarbeiterKapazitVerwaltung) {
		this.mitarbeiterKapazitVerwaltung = mitarbeiterKapazitVerwaltung;
	}

	public Map<Produkt, Integer> getProdukte() {
		return produkte;
	}

	public void setProdukte(Map<Produkt, Integer> produkte) {
		this.produkte = produkte;
	}

	public List<Verbindlichkeit> getVerbindlichkeiten() {
		return verbindlichkeiten;
	}

	public void setVerbindlichkeiten(List<Verbindlichkeit> verbindlichkeiten) {
		this.verbindlichkeiten = verbindlichkeiten;
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
    
    //etwas zum absatzmarkt
    
    //evt sowas wie 7 neue angebote auf dem markt für material ...
    
    //das wurde mit der menge verkauft für den und den preis
    
    //kostenaufstellung
    
    



}
