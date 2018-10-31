package fachkonzept.marketing;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fachkonzept.Arbeitskraft;
import fachkonzept.Mitarbeiter;
import fachkonzept.Simulation;
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
		Fernsehwerbung test11 = new Fernsehwerbung("Werbespot", 0.544);
		Radiowerbung test22 = new Radiowerbung("Werbespot", 44);
		PRKampagne test33 = new PRKampagne("Werbespot", 44);
		Sponsoring test44 = new Sponsoring("Werbespot", 14.544);
		MessenKampagne test55 = new MessenKampagne("Werbespot", 14.544);
		Plakatwerbung test66 = new Plakatwerbung("Werbespot", 14.544);
		u1.getMarketingmix().marketingBuchen(test1, u1);

		assertEquals(1420.544, u1.getMarketingmix().getMarketing().get(0).getBudget());
		assertEquals(55, u1.getMarketingmix().getMarketing().get(0).getEffektivitaet());
		assertEquals(16000, u1.getMarketingmix().getMarketing().get(0).getVolumen());

		assertEquals(Integer.valueOf(13716), u1.getMitarbeiterKapazitaeten().get(MitarbeiterFachgebiet.VERWALTUNG));
		
		u1.getMarketingmix().marketingEntfernen(test1);
		assertEquals(0, u1.getMarketingmix().getMarketing().size());
		u1.getMarketingmix().marketingBuchen(test2, u1);
		u1.getMarketingmix().marketingBuchen(test3, u1);
		u1.getMarketingmix().marketingBuchen(test4, u1);
		u1.getMarketingmix().marketingBuchen(test5, u1);
		u1.getMarketingmix().marketingBuchen(test6, u1);		

		u1.getMarketingmix().marketingBuchen(test11, u1);
		u1.getMarketingmix().marketingBuchen(test22, u1);
		u1.getMarketingmix().marketingBuchen(test33, u1);
		u1.getMarketingmix().marketingBuchen(test44, u1);
		u1.getMarketingmix().marketingBuchen(test55, u1);
		u1.getMarketingmix().marketingBuchen(test66, u1);
		Simulation.simuliere(s);

		assertEquals(11, u1.getMarketingmix().getMarketing().size());
	}
}

