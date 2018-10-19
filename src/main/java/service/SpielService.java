package service;

import java.util.List;
import java.util.Map;

import javax.annotation.security.PermitAll;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import dto.MarketingmixDTO;
import dto.MarktDTO;
import dto.MaschinenGesamtDTO;
import dto.ProdukteGesamtDTO;
import dto.RundenResultatDTO;
import dto.UnternehmenDTO;
import dto.ZwischenstandDTO;
import fachkonzept.Angebot;
import fachkonzept.Arbeitskraft;
import fachkonzept.Kredit;
import fachkonzept.Maschine;
import fachkonzept.Material;
import fachkonzept.Mitarbeiter;
import fachkonzept.Produkt;
import fachkonzept.Spiel;
import fachkonzept.Umsatz;
import fachkonzept.Unternehmen;
import fachkonzept.Verbindlichkeit;
import fachkonzept.marketing.Fernsehwerbung;
import fachkonzept.marketing.Marketingmaßnahme;
import fachkonzept.marketing.Marketingmix;
import fachkonzept.marketing.MessenKampagne;
import fachkonzept.marketing.PRKampagne;
import fachkonzept.marketing.Plakatwerbung;
import fachkonzept.marketing.Radiowerbung;
import fachkonzept.marketing.Sponsoring;
import fachkonzept.markt.Markteinheit;
import fachkonzept.util.MarketingmaßnahmenArt;

@PermitAll
@Path("spiel")
public class SpielService {
//
	@DefaultValue("0")
	@QueryParam("spielId")
	private int spielId;
	private static Spiel s;

	@GET
	@Path("ping")
	@Produces(MediaType.TEXT_PLAIN) // Application_Json
	public static String ping(@DefaultValue("1") @QueryParam("ping") String p) {
		return p;
	}

	@GET
	@Path("neuesspiel")
	@Produces(MediaType.TEXT_PLAIN) // Application_Json
	public static void neuesSpiel() {
		s = new Spiel();
	}

	@GET
	@Path("neuesunternehmen")
	@Produces(MediaType.TEXT_PLAIN) // Application_Json
	public static String neuesUnternehmen(@QueryParam("name") String name, @QueryParam("standort") String standort) {

		if (s != null) {

			for (Unternehmen u : s.getUnternehmen()) {
				if (u.getName() == name) {
					// doppelter name soll nicht sein
					return "Doppelter Name";
				}
			}

			s.unternehmenHinzufuegen(new Unternehmen(name, s));
		}

		return name;
	}

	@GET
	@Path("starten")
	@Produces(MediaType.APPLICATION_JSON)
	public static void spielStart(@DefaultValue("10") @QueryParam("rundenZahl") Integer rundenAnzahl) {
		if (s != null) {
			for (Unternehmen u : s.getUnternehmen())
				u.setSpiel(s);
			s.setRundenAnzahl(rundenAnzahl);
			s.rundenStart();
		}
	}

	@GET
	@Path("zugbeendet")
	@Produces(MediaType.APPLICATION_JSON)
	public static Integer zugBeendet() {
		// -1: spiel zu ende
		// 0: weitere spieler in der runde
		// n: runden nummer bei neuer runde
		return s.zugBeendet();

	}

	@GET
	@Path("unternehmen")
	@Produces(MediaType.APPLICATION_JSON)
	public static UnternehmenDTO getUnternehmen() {
		if (s != null && s.getAktuellesUnternehmen() != null)
			return Unternehmen.getDTO(s.getAktuellesUnternehmen());
		return null;

	}

	@GET
	@Path("bmarkt") // Beschaffungsmarkt
	@Produces(MediaType.APPLICATION_JSON)
	public static MarktDTO getBMarkt() {
		return new MarktDTO(s.getAktuellesUnternehmen().getBmarkt().getAngebote());
	}

	@GET
	@Path("vmarkt") // Vertriebs-/Absatzmarkt
	@Produces(MediaType.APPLICATION_JSON)
	public static MarktDTO getVMarkt() {
		return new MarktDTO(s.getAktuellesUnternehmen().getVmarkt().getAngebote());
	}

	@GET
	@Path("fmarkt") // Finanzmarkt
	@Produces(MediaType.APPLICATION_JSON)
	public static MarktDTO getFMarkt() {
		return new MarktDTO(s.getAktuellesUnternehmen().getFmarkt().getAngebote());
	}

	@GET
	@Path("amarkt") // Arbeitsmarkt
	@Produces(MediaType.APPLICATION_JSON)
	public static MarktDTO getAMarkt() {
		return new MarktDTO(s.getAktuellesUnternehmen().getAmarkt().getAngebote());
	}

	@GET
	@Path("mmarkt") // Maschinenmarkt
	@Produces(MediaType.APPLICATION_JSON)
	public static MarktDTO getMMarkt() {
		return new MarktDTO(s.getAktuellesUnternehmen().getMmarkt().getAngebote());
	}

	@GET
	@Path("angebotkaufen")
	@Produces(MediaType.APPLICATION_JSON)
	public static boolean kaufeAngebot(@DefaultValue("0") @QueryParam("menge") int menge,
			@QueryParam("angebotsid") int id) {
		// erstmal bezahlen

		Angebot angebot = Angebot.findeAngebot(id);
		if (angebot == null)
			Spiel.log("kein anbgebot mit der id");

		int tatsaechlichemenge = menge;
		if (angebot.getMenge() < menge)
			tatsaechlichemenge = angebot.getMenge(); // maximal was angeboten wird
			
		if (angebot.getMarkteinheit() instanceof Maschine) {
			s.getAktuellesUnternehmen().getMmarkt().kaufen(angebot, tatsaechlichemenge, s.getAktuellesUnternehmen());
			return true;
		} else if (angebot.getMarkteinheit() instanceof Material) {
			s.getAktuellesUnternehmen().getBmarkt().kaufen(angebot, tatsaechlichemenge, s.getAktuellesUnternehmen());
			return true;
		} else if (angebot.getMarkteinheit() instanceof Mitarbeiter) {
			s.getAktuellesUnternehmen().getAmarkt().kaufen(angebot, tatsaechlichemenge, s.getAktuellesUnternehmen());
			return true;
		} else if (angebot.getMarkteinheit() instanceof Kredit) {
			s.getAktuellesUnternehmen().getFmarkt().kaufen(angebot, tatsaechlichemenge, s.getAktuellesUnternehmen());
			return true;
		} else {
			return false;
		}

		//Spiel.log("gekauft " + tatsaechlichemenge + " von " + angebot.getMarkteinheit().getName());

	}

	@GET
	@Path("produziere")
	@Produces(MediaType.APPLICATION_JSON)
	public static void produziere(@QueryParam("menge") int menge, @QueryParam("maschinenid") int id) {
		Maschine m = (Maschine) Markteinheit.findeMarkteinheit(id);
		Produkt p = m.produziere(menge, s.getAktuellesUnternehmen());
	}

	@GET
	@Path("anbieten")
	@Produces(MediaType.APPLICATION_JSON)
	public static Object angebotErstellen(@QueryParam("menge") int menge, @QueryParam("produktid") int id,
			@QueryParam("preis") int preis) {

		Markteinheit m = Markteinheit.findeMarkteinheit(id);
		if (m instanceof Material) {
			Angebot a = new Angebot((Material) m, menge, preis);
			s.getAktuellesUnternehmen().getBmarkt().anbieten(a, s.getAktuellesUnternehmen());
		} else if (m instanceof Maschine) {
			Angebot a = new Angebot((Maschine) m, menge, preis);
			s.getAktuellesUnternehmen().getMmarkt().anbieten(a, s.getAktuellesUnternehmen());
		} else if (m instanceof Produkt) {
			Angebot a = new Angebot((Produkt) m, menge, preis);
			s.getAktuellesUnternehmen().getVmarkt().anbieten(a, s.getAktuellesUnternehmen());
		}

		return null;
	}

	@GET
	@Path("angebotentfernen")
	@Produces(MediaType.APPLICATION_JSON)
	public static void angebotEntfernen(@QueryParam("id") int id) {
		Angebot a = Angebot.findeAngebot(id);
		s.getAktuellesUnternehmen().getVmarkt().angebotEntfernen(a);
	}

	@GET
	@Path("maschinen")
	@Produces(MediaType.APPLICATION_JSON)
	public static MaschinenGesamtDTO getMaschinen() {
		return s.getAktuellesUnternehmen().zeigeMaschinen();
	}

	@GET
	@Path("produkte")
	@Produces(MediaType.APPLICATION_JSON)
	public static ProdukteGesamtDTO getProdukte() {
		return s.getAktuellesUnternehmen().zeigeProdukte();
	}

	@GET
	@Path("materialien")
	@Produces(MediaType.APPLICATION_JSON)
	public static Map<String, Integer> getMaterialien() {
		return s.getAktuellesUnternehmen().zeigeMaterialien();
	}

	@GET
	@Path("kredite")
	@Produces(MediaType.APPLICATION_JSON)
	public static List<Verbindlichkeit> getKredite() {
		return s.getAktuellesUnternehmen().getVerbindlichkeiten();
	}

	@GET
	@Path("mitarbeiter")
	@Produces(MediaType.APPLICATION_JSON)
	public static List<Arbeitskraft> getMitarbeiter() {
		return s.getAktuellesUnternehmen().getMitarbeiter();
	}

	@GET
	@Path("umsatzhistorievmarkt")
	@Produces(MediaType.APPLICATION_JSON)
	public static List<Umsatz> getUmsatzHistorie() {
		return s.getAktuellesUnternehmen().getVmarkt().getUmsatzHistorie();
	}

	@GET
	@Path("marketingmix")
	@Produces(MediaType.APPLICATION_JSON)
	public static Marketingmix getMarketingmix() {
		return s.getAktuellesUnternehmen().getMarketingmix();
	}

	@GET
	@Path("marketingangebote")
	@Produces(MediaType.APPLICATION_JSON)
	public static List<String> getMarketingAngebote() {
		return MarketingmaßnahmenArt.getMarketingmaßnahmenArten();
	}

	@GET
	@Path("buchenmarketingmaßnahme")
	@Produces(MediaType.APPLICATION_JSON)
	public static MarketingmixDTO erstelleMarketingmaßnahme(@QueryParam("art") String art,
			@QueryParam("budget") double budget, @QueryParam("name") String name) {
		Marketingmaßnahme m = null;

		switch (MarketingmaßnahmenArt.valueOf(art)) {
		case Sponsoring:
			m = new Sponsoring(name, budget);
			break;
		case Fernsehwerbung:
			m = new Fernsehwerbung(name, budget);
			break;
		case MessenKampagne:
			m = new MessenKampagne(name, budget);
			break;
		case Plakatwerbung:
			m = new Plakatwerbung(name, budget);
			break;
		case PRKampagne:
			m = new PRKampagne(name, budget);
			break;
		case Radiowerbung:
			m = new Radiowerbung(name, budget);
			break;
		default:
			return null;

		}
		s.getAktuellesUnternehmen().getMarketingmix().marketingHinzu(m);
		return new MarketingmixDTO(s.getAktuellesUnternehmen().getMarketingmix());
	}

	@GET
	@Path("zwischenstand")
	@Produces(MediaType.APPLICATION_JSON)
	// Zwischenstand aller UN nach einer Runde
	public static ZwischenstandDTO getZwischenstand() {
		if (s == null)
			return null;
		return new ZwischenstandDTO(s.getRunde(), s.getUnternehmen());

	}

	@GET
	@Path("rundenresultat")
	@Produces(MediaType.APPLICATION_JSON)
	// Übersicht für das eigene Unternehmen nach der Runde
	public static Object getRundenResultat() {
		if (s == null)
			return null;
		return new RundenResultatDTO(s.getAktuellesUnternehmen());
	}

	@GET
	@Path("spielende")
	@Produces(MediaType.APPLICATION_JSON)
	public static List<Unternehmen> getSpielende() {
		// @Jonas Breuer - bisher eine rangliste nach gewinn
		return s.getRangliste();
	}

	@GET
	@Path("materialdetails")
	@Produces(MediaType.APPLICATION_JSON)
	public static List<String> getMaterialDetails(@QueryParam("material") String material) {
		// String to Enum MaschinenArt
		// MaterialArt.valueOf(material);
		// return
		// s.getAktuellesUnternehmen().getMmarkt().getMaterialDetails(MaterialArt.valueOf(material),
		// s);
		return null;
	}

	public static Spiel getSpiel() {
		return s;
	}
}
