package dto;

import java.util.List;

import dto.mapper.MarketingmaßnahmenMapper;
import dto.mapper.ProduktMapper;
import dto.mapper.UmsatzMapper;
import fachkonzept.GuV;
import fachkonzept.Unternehmen;
import fachkonzept.Verbindlichkeit;
import fachkonzept.util.MitarbeiterFachgebiet;

public class RundenResultatDTO {

	private String name;
	private Double kapital; 
	private GuV guv; 
	private double gewinn;
	private double rundenGewinn;
	private Integer mitarbeiterAnzahl;
	
	// offene (nicht benötigte) Mitarbeiterkapazität in Minuten
	private Integer mitarbeiterKapazitMaschine;
	private Integer mitarbeiterKapazitVertieb;
	private Integer mitarbeiterKapazitVerwaltung;

	private List<ProduktDTO> produkte;
	
	private List<Verbindlichkeit> verbindlichkeiten;
	private List<UmsatzDTO> umsatzHistorie;

	private List<MarketingmaßnahmeDTO> marketingMaßnahmen;

	public RundenResultatDTO(Unternehmen u) {

		this.name = u.getName();
		this.kapital = u.getKapital();
		this.guv = u.getGuv();
		this.gewinn = u.getGuv().rundenErgebnis();
		this.rundenGewinn = u.getGuv().rundenErgebnis(u.getSpiel().getRunde()-1);	//Gewinn der letzten Runde
		this.mitarbeiterAnzahl = u.getMitarbeiter().size();
		this.mitarbeiterKapazitMaschine = u.getMitarbeiterKapazitaeten(MitarbeiterFachgebiet.MASCHINE);
		this.mitarbeiterKapazitVertieb = u.getMitarbeiterKapazitaeten(MitarbeiterFachgebiet.VERTRIEB);
		this.mitarbeiterKapazitVerwaltung = u.getMitarbeiterKapazitaeten(MitarbeiterFachgebiet.VERWALTUNG);
		this.produkte = ProduktMapper.toDTO(u.getProdukte());
		this.verbindlichkeiten = u.getVerbindlichkeiten();
		this.umsatzHistorie = UmsatzMapper.toDTO(u.getVmarkt().getUmsatzHistorie(u));

		this.marketingMaßnahmen = MarketingmaßnahmenMapper.toDTO(u.getMarketingmix().getMarketing());
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

	public double getRundenGewinn() {
		return rundenGewinn;
	}

	public void setRundenGewinn(double rundenGewinn) {
		this.rundenGewinn = rundenGewinn;
	}

	public void setKapital(Double kapital) {
		this.kapital = kapital;
	}

	public GuV getGuv() {
		return guv;
	}

	public double getGewinn() {
		return gewinn;
	}

	public void setGewinn(double gewinn) {
		this.gewinn = gewinn;
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

	public List<ProduktDTO> getProdukte() {
		return produkte;
	}

	public void setProdukte(List<ProduktDTO> produkte) {
		this.produkte = produkte;
	}

	public List<Verbindlichkeit> getVerbindlichkeiten() {
		return verbindlichkeiten;
	}

	public void setVerbindlichkeiten(List<Verbindlichkeit> verbindlichkeiten) {
		this.verbindlichkeiten = verbindlichkeiten;
	}

	public List<UmsatzDTO> getUmsatzHistorie() {
		return umsatzHistorie;
	}

	public void setUmsatzHistorie(List<UmsatzDTO> umsatzHistorie) {
		this.umsatzHistorie = umsatzHistorie;
	}

	public List<MarketingmaßnahmeDTO> getMarketingMaßnahmen() {
		return marketingMaßnahmen;
	}

	public void setMarketingMaßnahmen(List<MarketingmaßnahmeDTO> marketingMaßnahmen) {
		this.marketingMaßnahmen = marketingMaßnahmen;
	}

	// etwas zum absatzmarkt

	// evt sowas wie 7 neue angebote auf dem markt für material ...

	// das wurde mit der menge verkauft für den und den preis

	// kostenaufstellung

}
