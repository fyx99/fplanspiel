package fachkonzept;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import fachkonzept.marketing.Fernsehwerbung;
import fachkonzept.marketing.Marketingmaßnahme;
import fachkonzept.marketing.MessenKampagne;
import fachkonzept.marketing.PRKampagne;
import fachkonzept.marketing.Plakatwerbung;
import fachkonzept.marketing.Radiowerbung;
import fachkonzept.marketing.Sponsoring;
import fachkonzept.markt.Absatzmarkt;
import fachkonzept.markt.Arbeitsmarkt;
import fachkonzept.markt.Beschaffungsmarkt;
import fachkonzept.markt.Finanzmarkt;
import fachkonzept.markt.Maschinenmarkt;
import fachkonzept.util.KreditArt;
import fachkonzept.util.MaschinenArt;
import fachkonzept.util.MaterialArt;
import fachkonzept.util.MitarbeiterFachgebiet;
import fachkonzept.util.ProduktArt;
import fachkonzept.util.ProduktTyp;
import fachkonzept.util.SimulationsKonstanten;

public class Simulation {

	private static List<String> simlog = new ArrayList();

	public static void simuliereSpielstart(Spiel s) {

		Iterator<Unternehmen> i = s.getUnternehmen().iterator();
		while (i.hasNext()) {
			Unternehmen n = i.next();
			n.setKapital(100000);
			n.setBmarkt(beschaffungsmarktDemoDaten(n));
			n.setMmarkt(maschinenmarktDemoDaten(n));
			n.setFmarkt(finanzmarktDemoDaten());
			n.setAmarkt(arbeitsmarktDemoDaten(n));
			n.setVmarkt(absatzmarktDemoDaten());
		}

	}

	public static void simuliere(Spiel s) {

		// rest
		Iterator<Unternehmen> i = s.getUnternehmen().iterator();
		while (i.hasNext()) {
			Unternehmen n = i.next();
			simuliereKredittilgung(n);
			simuliereLohnzahlung(n);
			simuliereMarketingmix(n);
			simuliereBeschaffungsmarkt(n.getBmarkt(), s.getRunde());
			simuliereFinanzmarkt(n.getFmarkt());
			simuliereMaschinenmarkt(n.getMmarkt(), s.getRunde());
			simuliereArbeitsmarkt(n.getAmarkt());
			n.rundenReset();
		}
		// gemeinsame konkurrenz dinge
		simuliereAbsatzmarkt(s.getUnternehmen());

	}

	private static void simuliereKredittilgung(Unternehmen u) {

		Iterator<Verbindlichkeit> iter = u.getVerbindlichkeiten().iterator();

		while (iter.hasNext()) {
			Verbindlichkeit v = iter.next();
			// jeder kredit muss getilgt werden

			u.kosten(
					v.getKredit().getZinssatz() * v.getVerbleibendeSumme() + v
							.tilgen(v.getVerbleibendeSumme() / (v.getKredit().getLaufzeit() - v.getAktuelleLaufzeit())),
					"Kreditkosten");
			// irgendwie nicht die richtige formel :D
			if (v.getAktuelleLaufzeit() == v.getKredit().getLaufzeit() || v.getVerbleibendeSumme() <= 0) {
				iter.remove();
			}
		}
	}

	private static void simuliereLohnzahlung(Unternehmen u) {
		for (Arbeitskraft ak : u.getMitarbeiter()) {
			u.kosten(ak.getM().getLohnkosten(), "Lohnkosten");
		}
	}

	private static void simuliereMarketingmix(Unternehmen u) {
		double marketingsEinflussFaktor = u.getStandort().getMarketingEinfluss(); // abhängig vom Standort

		int marketingPunkte = 0;
		Marketingmaßnahme m = null;
		int volumen = 0;
		for (int i = 0; i < u.getMarketingmix().getMarketingType(Fernsehwerbung.class.getName()).size(); i++) {
			m = u.getMarketingmix().getMarketingType(Fernsehwerbung.class.getName()).get(i);
			volumen += m.getBudget();
		}
		if (m != null && volumen > m.getVolumen()) {
			marketingPunkte += m.getEffektivitaet();
		} else if (m != null) {
			marketingPunkte += m.getEffektivitaet() * ((double) volumen / (double) m.getVolumen());
		}
		volumen = 0;
		m = null;

		for (int i = 0; i < u.getMarketingmix().getMarketingType(Sponsoring.class.getName()).size(); i++) {
			m = u.getMarketingmix().getMarketingType(Sponsoring.class.getName()).get(i);
			volumen += m.getBudget();
		}
		if (m != null && volumen > m.getVolumen()) {
			marketingPunkte += m.getEffektivitaet();
		} else if (m != null) {
			marketingPunkte += m.getEffektivitaet() * ((double) volumen / (double) m.getVolumen());
		}
		volumen = 0;
		m = null;

		for (int i = 0; i < u.getMarketingmix().getMarketingType(Plakatwerbung.class.getName()).size(); i++) {
			m = u.getMarketingmix().getMarketingType(Plakatwerbung.class.getName()).get(i);
			volumen += m.getBudget();
		}
		if (m != null && volumen > m.getVolumen()) {
			marketingPunkte += m.getEffektivitaet();
		} else if (m != null) {
			marketingPunkte += m.getEffektivitaet() * ((double) volumen / (double) m.getVolumen());
		}
		volumen = 0;
		m = null;

		for (int i = 0; i < u.getMarketingmix().getMarketingType(Radiowerbung.class.getName()).size(); i++) {
			m = u.getMarketingmix().getMarketingType(Radiowerbung.class.getName()).get(i);
			volumen += m.getBudget();
		}
		if (m != null && volumen > m.getVolumen()) {
			marketingPunkte += m.getEffektivitaet();
		} else if (m != null) {
			marketingPunkte += m.getEffektivitaet() * ((double) volumen / (double) m.getVolumen());
		}
		volumen = 0;
		m = null;

		for (int i = 0; i < u.getMarketingmix().getMarketingType(PRKampagne.class.getName()).size(); i++) {
			m = u.getMarketingmix().getMarketingType(PRKampagne.class.getName()).get(i);
			volumen += m.getBudget();
		}
		if (m != null && volumen > m.getVolumen()) {
			marketingPunkte += m.getEffektivitaet();
		} else if (m != null) {
			marketingPunkte += m.getEffektivitaet() * ((double) volumen / (double) m.getVolumen());
		}
		volumen = 0;
		m = null;

		for (int i = 0; i < u.getMarketingmix().getMarketingType(MessenKampagne.class.getName()).size(); i++) {
			m = u.getMarketingmix().getMarketingType(MessenKampagne.class.getName()).get(i);
			volumen += m.getBudget();
		}
		if (m != null && volumen > m.getVolumen()) {
			marketingPunkte += m.getEffektivitaet();
		} else if (m != null) {
			marketingPunkte += m.getEffektivitaet() * ((double) volumen / (double) m.getVolumen());
		}
		marketingPunkte = (int) (marketingPunkte * marketingsEinflussFaktor);
		u.getMarketingmix().setMarketingStaerke(marketingPunkte);

	}

	private static void simuliereAbsatzmarkt(List<Unternehmen> us) {
		for (ProduktArt produktArt : ProduktArt.values()) {
			// pro produkt gehen wir den spaß jetzt mal durch
			Map<Angebot, Unternehmen> produkt_angebote = new HashMap<Angebot, Unternehmen>();
			for (Unternehmen u : us) {

				List<Angebot> unternehmenAngebote = u.getVmarkt().getAngeboteByProduktArt(produktArt);

				produkt_angebote.putAll(unternehmenAngebote.stream().collect(Collectors.toMap(a -> a, a -> u)));
				// hier werden hoffentlich die angebote gesammelt :D

			}
			// jetzt haben wir alle angebote der speziellen produkt art

			if (produkt_angebote.isEmpty())
				continue;

			simuliereEinzelnesProdukt(produkt_angebote,
					us.get(0).getVmarkt().getProduktVolumen().get(produktArt) * us.size(),
					SimulationsKonstanten.getProduktMarktpreis(produktArt));
		}
		//noch die nachfrage am ende anpassen
		us.forEach(u -> simuliereNachfrageAbsatzmarkt(u.getVmarkt(), u.getSpiel().getRunde()));
		
	}

	private static void simuliereEinzelnesProdukt(Map<Angebot, Unternehmen> angebote, int nachfrage,
			double grundpreis) {
		// als erstes die liste nach angebotsstärke sortieren
		// hier jetzt nach standort, marketing und preis
		// in der sortierten liste sind attraktivitaeten gesetzt
		List<Angebot> sortierteAngebote = angebote.keySet().stream()
				.sorted((Angebot u1, Angebot u2) -> Double.compare(u1.getAttraktivitaet(angebote.get(u1)), u2.getAttraktivitaet(angebote.get(u2))))
				.collect(Collectors.toList());
		int verbleibendeNachfrage = nachfrage;

		for (int i = 0; i < sortierteAngebote.size(); i++) {
			// bevor was gemacht wird sollte natürlich geschaut werden, ob der marktpreis
			// überschritten ist und ob die nachfrage angepasst werden muss
			if (sortierteAngebote.get(i).getPreis() > grundpreis) {
				verbleibendeNachfrage -= nachfrageAnpassen(nachfrage, grundpreis, sortierteAngebote.get(i).getPreis());
			}
			if (verbleibendeNachfrage <= 0) {
				break;
			}
			List<Angebot> auswahl = angbotsAuswahl(sortierteAngebote.subList(i, sortierteAngebote.size()));
			i += auswahl.size() - 1;

			// -> ab hier einfach abkaufen
			while (verbleibendeNachfrage > 0 && !auswahl.isEmpty() && verbleibendeNachfrage > auswahl.size()) {
				verbleibendeNachfrage = nachfrageVerteilen(auswahl, angebote, verbleibendeNachfrage);

			}

		}

	}

	private static int nachfrageAnpassen(int gesamtNachfrage, double grundpreis, double aktPreis) {
		// funktion die den preis verringert
		double kappungsfaktor = 3; // 1% drüber -> 3 runter
		double preisDifferenz = 1 - aktPreis / grundpreis; // in prozent über grundpreis
		return (int)(gesamtNachfrage * (preisDifferenz * kappungsfaktor));

	}

	public static double getPeriodenverschiebung(int runde) { // um die nachfragemenge zu verändern
		double randomStart = Math.random() * 2;
		return 1 + Math.sin(0.125 * Math.PI * runde + randomStart * Math.PI) * 0.5;
	}

	private static List<Angebot> angbotsAuswahl(List<Angebot> angebote) {
		// wählt unter den angeboten das/ evt. die günstigste/n aus
		List<Angebot> ret = new ArrayList<Angebot>();
		int referenzAttr = (int) angebote.get(0).getAttraktivitaet();
		for (Angebot a : angebote) {
			if (((int) a.getAttraktivitaet()) <= referenzAttr) {
				ret.add(a);
			} else {
				break;
			}
		}

		return ret;
	}

	private static int nachfrageVerteilen(List<Angebot> angebote, Map<Angebot, Unternehmen> unternehmen,
			int nachfrage) {
		// hier wird auf eine liste mit n angeboten die gleichwertig sind die nachfrage
		// verteilt

		int verbleibendeNachfrage = nachfrage;
		int durchschnittlicheMenge = verbleibendeNachfrage / angebote.size();
		int minMengeAngebote = angebote.stream().min(Comparator.comparing(Angebot::getMenge)).get().getMenge();
		durchschnittlicheMenge = minMengeAngebote < durchschnittlicheMenge ? minMengeAngebote : durchschnittlicheMenge;
		Iterator<Angebot> it = angebote.iterator();
		while (it.hasNext()) {
			Angebot a = it.next();
			verbleibendeNachfrage -= durchschnittlicheMenge;
			Unternehmen verkaeufer = unternehmen.get(a);
			if (verkaeufer.getVmarkt().verkaufen(a, durchschnittlicheMenge, verkaeufer) == null) {
				it.remove();
			}
		}

		// return wert verbleibende nachfrage
		return verbleibendeNachfrage;
	}
	
	private static void simuliereNachfrageAbsatzmarkt(Absatzmarkt b, int runde) {
		int gesamt = 0;
		for (ProduktArt a : ProduktArt.values()) {
			// wv pro produkt verkauft wurde (menge)
			gesamt += Absatzmarkt.getUmsaetzeByProduktArt(a, runde).stream().mapToInt(u -> u.getMenge()).sum();
		}
		
		if(gesamt < ProduktArt.values().length)	//macht sonst wegen rundung keinen sinn
			return;
		double durchschnittsMenge = gesamt / ProduktArt.values().length;
		

		// was ist viel
		// -> über alle drüber und
		for(ProduktArt a : ProduktArt.values()) {
			int artMenge = b.getProduktVolumen().get(a);
			double mengenVerhältnis = (double) (artMenge - durchschnittsMenge) / durchschnittsMenge;
			Map<ProduktArt, Integer> neueWerte = b.getProduktVolumen();
			neueWerte.put(a, (artMenge + (int)(artMenge * SimulationsKonstanten.ABSATZ_MARKT_NACHFRAGEANPASSUNG * mengenVerhältnis)));
			b.setProduktVolumen(neueWerte);
		}
	}

	private static void simuliereBeschaffungsmarkt(Beschaffungsmarkt b, int runde) {
		int gesamt = 0;
		for (MaterialArt a : MaterialArt.values()) {
			// wv pro produkt verkauft wurde (menge)
			gesamt += Beschaffungsmarkt.umsatzProMaterialArt(a, runde).stream().mapToInt(u -> u.getMenge()).sum();
		}
		
		if(gesamt < MaterialArt.values().length)	//macht sonst wegen rundung keinen sinn
			return;
		double durchschnittsMenge = gesamt / MaterialArt.values().length;
		

		// was ist viel
		// -> über alle drüber und
		for (Angebot a : b.getAngebote()) {
			int artMenge = Beschaffungsmarkt
					.umsatzProMaterialArt(((Material) a.getMarkteinheit()).getMaterialArt(), runde).stream()
					.mapToInt(u -> u.getMenge()).sum();
			double mengenVerhältnis = (double) (artMenge - durchschnittsMenge) / durchschnittsMenge;
			// bei 50% drüber soll preis 5% steigen
			a.setMenge(SimulationsKonstanten.MATERIAL_MARKT_MENGE);
			a.setPreis(a.getPreis()
					+ (a.getPreis() * SimulationsKonstanten.MATERIAL_MARKT_PREISANPASSUNG * mengenVerhältnis));

		}
	}

	private static void simuliereMaschinenmarkt(Maschinenmarkt b, int runde) {
		int gesamt = 0;
		for (MaschinenArt a : MaschinenArt.values()) {
			// wv pro produkt verkauft wurde
			gesamt += Maschinenmarkt.umsatzProMaschinenArt(a, runde).stream().mapToInt(u -> u.getMenge()).sum();
		}
		if(gesamt < MaschinenArt.values().length)	//macht sonst wegen rundung keinen sinn
			return;
		
		double schnittMenge = gesamt / MaschinenArt.values().length;
		// was ist viel
		// -> über alle drüber und
		for (Angebot a : b.getAngebote()) {
			int artMenge = Maschinenmarkt
					.umsatzProMaschinenArt(((Maschine) a.getMarkteinheit()).getMaschinenArt(), runde).stream()
					.mapToInt(u -> u.getMenge()).sum();
			double mengenVerhältnis = (artMenge - schnittMenge) / schnittMenge;
			// bei 50% drüber soll preis 5% steigen
			a.setMenge(SimulationsKonstanten.MASCHINEN_MARKT_MENGE);
			a.setPreis(a.getPreis()
					+ (a.getPreis() * SimulationsKonstanten.MASCHINEN_MARKT_PREISANPASSUNG * mengenVerhältnis));

		}
	}

	private static void simuliereFinanzmarkt(Finanzmarkt b) {
		for (Angebot a : b.getAngebote()) {
			a.setMenge(SimulationsKonstanten.MATERIAL_MARKT_MENGE);

		}
	}

	private static void simuliereArbeitsmarkt(Arbeitsmarkt b) {
		for (Angebot a : b.getAngebote()) {

			a.setMenge(SimulationsKonstanten.MATERIAL_MARKT_MENGE);

		}
	}

	private static Beschaffungsmarkt beschaffungsmarktDemoDaten(Unternehmen n) {
		double standortfaktor_material = n.getStandort().getMaterialKosten();
		Material holz = new Material(MaterialArt.Holz);
		Material stoff = new Material(MaterialArt.Stoff);
		Material leder = new Material(MaterialArt.Leder);
		Material glas = new Material(MaterialArt.Glas);
		Material kunststoff = new Material(MaterialArt.Kunststoff);
		Material edelstahl = new Material(MaterialArt.Edelstahl);

		Beschaffungsmarkt b = new Beschaffungsmarkt();
		b.anbieten(new Angebot(holz, SimulationsKonstanten.MATERIAL_MARKT_MENGE, 3 * standortfaktor_material));
		b.anbieten(new Angebot(stoff, SimulationsKonstanten.MATERIAL_MARKT_MENGE, 3.50 * standortfaktor_material));
		b.anbieten(new Angebot(leder, SimulationsKonstanten.MATERIAL_MARKT_MENGE, 9 * standortfaktor_material));
		b.anbieten(new Angebot(glas, SimulationsKonstanten.MATERIAL_MARKT_MENGE, 14 * standortfaktor_material));
		b.anbieten(new Angebot(kunststoff, SimulationsKonstanten.MATERIAL_MARKT_MENGE, 1 * standortfaktor_material));
		b.anbieten(new Angebot(edelstahl, SimulationsKonstanten.MATERIAL_MARKT_MENGE, 6 * standortfaktor_material));

		return b;
	}

	private static Finanzmarkt finanzmarktDemoDaten() {
		Kredit k1 = new Kredit(100000, 0.05, 4, KreditArt.Ultra_Cash);
		Kredit k2 = new Kredit(5000, 0.02, 2, KreditArt.Kurzes_Cash);
		Kredit k3 = new Kredit(200000, 0.03, 8, KreditArt.Mehr_Cash);

		Finanzmarkt fm = new Finanzmarkt();
		fm.anbieten(new Angebot(k1, 1, 0));
		fm.anbieten(new Angebot(k2, 10, 0));
		fm.anbieten(new Angebot(k3, 4, 0));

		return fm;
	}

	private static Arbeitsmarkt arbeitsmarktDemoDaten(Unternehmen n) {
		double standortfaktor_mitarbeiterkosten = n.getStandort().getMitarbeiterKosten();
		Mitarbeiter ma1 = new Mitarbeiter("Mitarbeiter 1", 150 * standortfaktor_mitarbeiterkosten, 60000,
				MitarbeiterFachgebiet.MASCHINE);
		Mitarbeiter ma2 = new Mitarbeiter("Mitarbeiter 2", 300 * standortfaktor_mitarbeiterkosten, 120000,
				MitarbeiterFachgebiet.MASCHINE);

		Mitarbeiter ma3 = new Mitarbeiter("Mitarbeiter 3", 200 * standortfaktor_mitarbeiterkosten, 100000,
				MitarbeiterFachgebiet.VERWALTUNG);
		Mitarbeiter ma4 = new Mitarbeiter("Mitarbeiter 4", 250 * standortfaktor_mitarbeiterkosten, 120000,
				MitarbeiterFachgebiet.VERWALTUNG);

		Mitarbeiter ma5 = new Mitarbeiter("Mitarbeiter 5", 200 * standortfaktor_mitarbeiterkosten, 100000,
				MitarbeiterFachgebiet.VERTRIEB);
		Mitarbeiter ma6 = new Mitarbeiter("Mitarbeiter 6", 300 * standortfaktor_mitarbeiterkosten, 120000,
				MitarbeiterFachgebiet.VERTRIEB);

		Arbeitsmarkt am = new Arbeitsmarkt();

		am.anbieten(new Angebot(ma1, 10, 20));
		am.anbieten(new Angebot(ma2, 10, 20));
		am.anbieten(new Angebot(ma3, 10, 20));
		am.anbieten(new Angebot(ma4, 10, 20));
		am.anbieten(new Angebot(ma5, 10, 20));
		am.anbieten(new Angebot(ma6, 10, 20));

		return am;
	}

	private static Maschinenmarkt maschinenmarktDemoDaten(Unternehmen n) {

		double fertigungsKostenFaktor = n.getStandort().getFertigungsKosten();

		Material holz = new Material(MaterialArt.Holz);
		Material stoff = new Material(MaterialArt.Stoff);
		Material leder = new Material(MaterialArt.Leder);
		Material glas = new Material(MaterialArt.Glas);
		Material kunststoff = new Material(MaterialArt.Kunststoff);
		Material edelstahl = new Material(MaterialArt.Edelstahl);

		Produkt holzstuhl = new Produkt(ProduktArt.Holzstuhl, ProduktTyp.Stuhl);
		Map<String, Integer> map_hst = new HashMap<String, Integer>(); // für jedes Produkt Map mit benötigten
																		// Ressourcen
		map_hst.put(holz.getName(), 10);// 10 Holz

		Produkt stoffstuhl = new Produkt(ProduktArt.Stoffstuhl, ProduktTyp.Stuhl);
		Map<String, Integer> map_sst = new HashMap<String, Integer>();
		map_sst.put(holz.getName(), 8);
		map_sst.put(stoff.getName(), 4);

		Produkt lederstuhl = new Produkt(ProduktArt.Lederstuhl, ProduktTyp.Stuhl);
		Map<String, Integer> map_lst = new HashMap<String, Integer>();
		map_lst.put(holz.getName(), 8);
		map_lst.put(leder.getName(), 4);

		Produkt holztisch = new Produkt(ProduktArt.Holztisch, ProduktTyp.Tisch);
		Map<String, Integer> map_ht = new HashMap<String, Integer>();
		map_ht.put(holz.getName(), 32);

		Produkt glastisch = new Produkt(ProduktArt.Glastisch, ProduktTyp.Tisch);
		Map<String, Integer> map_gt = new HashMap<String, Integer>();
		map_gt.put(edelstahl.getName(), 10);
		map_gt.put(glas.getName(), 18);

		Produkt kunststofftisch = new Produkt(ProduktArt.Kunststofftisch, ProduktTyp.Tisch);
		Map<String, Integer> map_kt = new HashMap<String, Integer>();
		map_kt.put(kunststoff.getName(), 32);

		Produkt holzschrank = new Produkt(ProduktArt.Holzschrank, ProduktTyp.Schrank);
		Map<String, Integer> map_hsc = new HashMap<String, Integer>();
		map_hsc.put(holz.getName(), 80);

		Produkt edelstahlschrank = new Produkt(ProduktArt.Edelstahlschrank, ProduktTyp.Schrank);
		Map<String, Integer> map_esc = new HashMap<String, Integer>();
		map_esc.put(holz.getName(), 60);
		map_esc.put(edelstahl.getName(), 20);

		Produkt glasschrank = new Produkt(ProduktArt.Glasschrank, ProduktTyp.Schrank);
		Map<String, Integer> map_gsc = new HashMap<String, Integer>();
		map_gsc.put(holz.getName(), 60);
		map_gsc.put(glas.getName(), 20);

		// Map für jedes Produkt mit den benötigten Ressourcen
		// Produktionsmatrix pm = new Produktionsmatrix(map);

		// Stühle
		Maschine m1 = new Maschine(MaschinenArt.Holzstuhlmaschine, 100, holzstuhl, new Produktionsmatrix(map_hst),
				15 * fertigungsKostenFaktor, 1);
		Maschine m2 = new Maschine(MaschinenArt.Stoffstuhlmaschine, 57, stoffstuhl, new Produktionsmatrix(map_sst),
				20 * fertigungsKostenFaktor, 2);
		Maschine m3 = new Maschine(MaschinenArt.Lederstuhlmaschine, 50, lederstuhl, new Produktionsmatrix(map_lst),
				25 * fertigungsKostenFaktor, 3);

		// Tische
		Maschine m4 = new Maschine(MaschinenArt.Holztischmaschine, 50, holztisch, new Produktionsmatrix(map_ht),
				100 * fertigungsKostenFaktor, 4);
		Maschine m5 = new Maschine(MaschinenArt.Glastischmaschine, 35, glastisch, new Produktionsmatrix(map_gt),
				125 * fertigungsKostenFaktor, 5);
		Maschine m6 = new Maschine(MaschinenArt.Kunststofftischmaschine, 180, kunststofftisch,
				new Produktionsmatrix(map_kt), 20 * fertigungsKostenFaktor, 6);

		// Schränke
		Maschine m7 = new Maschine(MaschinenArt.Holzschrankmaschine, 65, holzschrank, new Produktionsmatrix(map_hsc),
				150 * fertigungsKostenFaktor, 7);
		Maschine m8 = new Maschine(MaschinenArt.Edelstahlschrankmaschine, 50, edelstahlschrank,
				new Produktionsmatrix(map_esc), 185 * fertigungsKostenFaktor, 8);
		Maschine m9 = new Maschine(MaschinenArt.Glasschrankmaschine, 38, glasschrank, new Produktionsmatrix(map_gsc),
				215 * fertigungsKostenFaktor, 9);

		// Maschinen auf Maschinenmarkt anbieten
		Maschinenmarkt b = new Maschinenmarkt();
		b.anbieten(new Angebot(m1, SimulationsKonstanten.MASCHINEN_MARKT_MENGE,
				SimulationsKonstanten.getMaschinenPreise(m1.getMaschinenArt())));
		b.anbieten(new Angebot(m2, SimulationsKonstanten.MASCHINEN_MARKT_MENGE,
				SimulationsKonstanten.getMaschinenPreise(m2.getMaschinenArt())));
		b.anbieten(new Angebot(m3, SimulationsKonstanten.MASCHINEN_MARKT_MENGE,
				SimulationsKonstanten.getMaschinenPreise(m3.getMaschinenArt())));
		b.anbieten(new Angebot(m4, SimulationsKonstanten.MASCHINEN_MARKT_MENGE,
				SimulationsKonstanten.getMaschinenPreise(m4.getMaschinenArt())));
		b.anbieten(new Angebot(m5, SimulationsKonstanten.MASCHINEN_MARKT_MENGE,
				SimulationsKonstanten.getMaschinenPreise(m5.getMaschinenArt())));
		b.anbieten(new Angebot(m6, SimulationsKonstanten.MASCHINEN_MARKT_MENGE,
				SimulationsKonstanten.getMaschinenPreise(m6.getMaschinenArt())));
		b.anbieten(new Angebot(m7, SimulationsKonstanten.MASCHINEN_MARKT_MENGE,
				SimulationsKonstanten.getMaschinenPreise(m7.getMaschinenArt())));
		b.anbieten(new Angebot(m8, SimulationsKonstanten.MASCHINEN_MARKT_MENGE,
				SimulationsKonstanten.getMaschinenPreise(m8.getMaschinenArt())));
		b.anbieten(new Angebot(m9, SimulationsKonstanten.MASCHINEN_MARKT_MENGE,
				SimulationsKonstanten.getMaschinenPreise(m9.getMaschinenArt())));

		return b;
	}
	
	private static Absatzmarkt absatzmarktDemoDaten() {
		Absatzmarkt am = new Absatzmarkt();
		am.setProduktVolumen( Arrays.asList(ProduktArt.values()).stream().collect(Collectors.toMap(pa -> pa, pa -> SimulationsKonstanten.getProduktMarktvolumen(pa))));
		return am;
	}

	public static List<String> getLog() {
		return simlog;
	}

	public static void log(String a) {
		simlog.add(a);
	}

}
