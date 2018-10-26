package fachkonzept.marketing;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fachkonzept.Arbeitskraft;
import fachkonzept.Mitarbeiter;
import fachkonzept.Spiel;
import fachkonzept.Standort;
import fachkonzept.Unternehmen;
import fachkonzept.util.MitarbeiterFachgebiet;
import fachkonzept.util.SimulationsKonstanten;
import fachkonzept.util.StandortArt;

class MarketingMixTest {

	Spiel s;
	Unternehmen u1;
	@BeforeEach
	void setUp() {
		s = new Spiel();
		u1 = new Unternehmen("name", s, new Standort(SimulationsKonstanten.getStandortFaktoren(StandortArt.NEUTRAL)));
	}
	
	@Test
	void init() {

		u1.arbeitskraftHinzu(new Arbeitskraft(new Mitarbeiter("name", 111, 14000, MitarbeiterFachgebiet.VERWALTUNG )));
		Fernsehwerbung test1 = new Fernsehwerbung("Werbespot", 1420.544);
		Radiowerbung test2 = new Radiowerbung("Werbespot", 7879);
		PRKampagne test3 = new PRKampagne("Werbespot", 4666635);
		Sponsoring test4 = new Sponsoring("Werbespot", 23423.544);
		MessenKampagne test5 = new MessenKampagne("Werbespot", 14220.544);
		Plakatwerbung test6 = new Plakatwerbung("Werbespot", 14240.544);
		u1.getMarketingmix().marketingBuchen(test1, u1);
		assertEquals(1420.544, u1.getMarketingmix().getMarketing().get(0).getBudget());
		assertEquals(55, u1.getMarketingmix().getMarketing().get(0).getEffektivitaet());
		assertEquals(16000, u1.getMarketingmix().getMarketing().get(0).getVolumen());

		assertEquals(Integer.valueOf(13716), u1.getMitarbeiterKapazitaeten().get(MitarbeiterFachgebiet.VERWALTUNG));
		
		u1.getMarketingmix().marketingEntfernen(test1);
		assertEquals(0, u1.getMarketingmix().getMarketing().size());
	}
}

