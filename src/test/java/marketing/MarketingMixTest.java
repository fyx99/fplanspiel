package marketing;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fachkonzept.Arbeitskraft;
import fachkonzept.Mitarbeiter;
import fachkonzept.Simulation;
import fachkonzept.Spiel;
import fachkonzept.Standort;
import fachkonzept.Unternehmen;
import fachkonzept.marketing.Fernsehwerbung;
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
		u1.getMarketingmix().marketingBuchen(test1, u1);
		assertEquals(1420.544, u1.getMarketingmix().getMarketing().get(0).getBudget());
		assertEquals(55, u1.getMarketingmix().getMarketing().get(0).getEffektivitaet());
		assertEquals(16000, u1.getMarketingmix().getMarketing().get(0).getVolumen());

		assertEquals(Integer.valueOf(13290), u1.getMitarbeiterKapazitaeten().get(MitarbeiterFachgebiet.VERWALTUNG));
		
		
	}
}
