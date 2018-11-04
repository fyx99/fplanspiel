package service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
import dto.SpielDTO;
import dto.UnternehmenDTO;
import dto.ZwischenstandDTO;
import dto.mapper.MarktMapper;
import dto.mapper.UnternehmenMapper;
import fachkonzept.Angebot;
import fachkonzept.Arbeitskraft;
import fachkonzept.Kredit;
import fachkonzept.Maschine;
import fachkonzept.Material;
import fachkonzept.Mitarbeiter;
import fachkonzept.Produkt;
import fachkonzept.Spiel;
import fachkonzept.Standort;
import fachkonzept.Umsatz;
import fachkonzept.Unternehmen;
import fachkonzept.Verbindlichkeit;
import fachkonzept.marketing.Fernsehwerbung;
import fachkonzept.marketing.Marketingmaßnahme;
import fachkonzept.marketing.MessenKampagne;
import fachkonzept.marketing.PRKampagne;
import fachkonzept.marketing.Plakatwerbung;
import fachkonzept.marketing.Radiowerbung;
import fachkonzept.marketing.Sponsoring;
import fachkonzept.markt.Markteinheit;
import fachkonzept.util.MarketingmaßnahmenArt;
import fachkonzept.util.SimulationsKonstanten;
import fachkonzept.util.StandortArt;

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
	@Produces(MediaType.TEXT_PLAIN) 
	public static String ping(@DefaultValue("1") @QueryParam("ping") String p) {
		return p;
	}

	@GET
	@Path("neuesspiel")
	@Produces(MediaType.TEXT_PLAIN) 
	public static void neuesSpiel() {
		s = new Spiel();
	}

	@GET
	@Path("neuesunternehmen")
	@Produces(MediaType.TEXT_PLAIN)
	public static String neuesUnternehmen(@QueryParam("name") String name, @QueryParam("standort") String standort) {

		if (s != null) {

			for (Unternehmen u : s.getUnternehmen()) {
				if (u.getName() == name) {
					// doppelter name soll nicht sein
					return "Doppelter Name";
				}
			}
			StandortArt standortArt = StandortArt.valueOf(standort);
			Standort st;
			switch (standortArt) {
			case A:
				st = new Standort(SimulationsKonstanten.getStandortFaktoren(standortArt));
				break;
			case B:
				st = new Standort(SimulationsKonstanten.getStandortFaktoren(standortArt));
				break;
			case C:
				st = new Standort(SimulationsKonstanten.getStandortFaktoren(standortArt));
				break;
			case D:
				st = new Standort(SimulationsKonstanten.getStandortFaktoren(standortArt));
				break;
			default:
				st = new Standort(SimulationsKonstanten.getStandortFaktoren(standortArt));
			}

			s.unternehmenHinzufuegen(new Unternehmen(name, s, st));

		}

		return name;
	}

	@GET
	@Path("starten")
	@Produces(MediaType.APPLICATION_JSON)
	public static void spielStart(@DefaultValue("10") @QueryParam("rundenZahl") Integer rundenAnzahl,
			@DefaultValue("0") @QueryParam("szenario") Integer szenario) {
		if (s != null) {
			s.setRundenAnzahl(rundenAnzahl);
			s.setSzenario(szenario);
			s.rundenStart();
		}
	}

	@GET
	@Path("spiel")
	@Produces(MediaType.TEXT_PLAIN) 
	public static SpielDTO getSpielDTO() {
		return new SpielDTO(s);
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
			return UnternehmenMapper.toDTO(s.getAktuellesUnternehmen());
		return null;

	}

	@GET
	@Path("bmarkt") // Beschaffungsmarkt
	@Produces(MediaType.APPLICATION_JSON)
	public static MarktDTO getBMarkt() {
		return MarktMapper.toDTO(s.getAktuellesUnternehmen().getBmarkt());
	}

	@GET
	@Path("vmarkt") // Absatzmarkt
	@Produces(MediaType.APPLICATION_JSON)
	public static MarktDTO getVMarkt() {
		return MarktMapper.toDTO(s.getAktuellesUnternehmen().getVmarkt());
	}

	@GET
	@Path("fmarkt") // Finanzmarkt
	@Produces(MediaType.APPLICATION_JSON)
	public static MarktDTO getFMarkt() {
		return MarktMapper.toDTO(s.getAktuellesUnternehmen().getFmarkt());
	}

	@GET
	@Path("amarkt") // Arbeitsmarkt
	@Produces(MediaType.APPLICATION_JSON)
	public static MarktDTO getAMarkt() {
		return MarktMapper.toDTO(s.getAktuellesUnternehmen().getAmarkt());
	}

	@GET
	@Path("mmarkt") // Maschinenmarkt
	@Produces(MediaType.APPLICATION_JSON)
	public static MarktDTO getMMarkt() {
		return MarktMapper.toDTO(s.getAktuellesUnternehmen().getMmarkt());
	}

	@GET
	@Path("angebotkaufen")
	@Produces(MediaType.APPLICATION_JSON)
	public static boolean kaufeAngebot(@DefaultValue("-1") @QueryParam("menge") int menge,
			@QueryParam("angebotsid") int id) {
		// Bezahlung

		Angebot angebot = Angebot.findeAngebot(id);
		if (angebot == null) {
			Spiel.log("kein anbgebot mit der id");
			return false;
		}
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
	@Path("mitarbeiterEntfernen")
	@Produces(MediaType.APPLICATION_JSON)
	public static void mitarbeiterEntfernen(@QueryParam("id") int id) {
		s.getAktuellesUnternehmen().arbeitskraftEntfernen(id);
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
		return s.getAktuellesUnternehmen().getVmarkt().getUmsatzHistorie(s.getAktuellesUnternehmen());
	}

	@GET
	@Path("marketingmix")
	@Produces(MediaType.APPLICATION_JSON)
	public static MarketingmixDTO getMarketingmix() {
		return new MarketingmixDTO(s.getAktuellesUnternehmen().getMarketingmix());
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
		s.getAktuellesUnternehmen().getMarketingmix().marketingBuchen(m, s.getAktuellesUnternehmen());
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
	public static RundenResultatDTO getRundenResultat() {
		if (s == null)
			return null;
		return new RundenResultatDTO(s.getAktuellesUnternehmen());
	}

	@GET
	@Path("spielende")
	@Produces(MediaType.APPLICATION_JSON)
	public static List<UnternehmenDTO> getSpielende() {
		return s.getRangliste().stream().map(UnternehmenMapper::toDTO).collect(Collectors.toList());
	}

	public static Spiel getSpiel() {
		return s;
	}

}
