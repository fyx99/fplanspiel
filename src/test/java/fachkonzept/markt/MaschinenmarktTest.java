package fachkonzept.markt;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import fachkonzept.Angebot;
import fachkonzept.Simulation;
import fachkonzept.Spiel;
import fachkonzept.Standort;
import fachkonzept.Unternehmen;
import fachkonzept.util.MaschinenArt;
import fachkonzept.util.SimulationsKonstanten;
import fachkonzept.util.StandortArt;

public class MaschinenmarktTest {
	
	@Test
    void init() {
		Spiel s = new Spiel();
		Unternehmen un = new Unternehmen("Test", s, new Standort(SimulationsKonstanten.getStandortFaktoren(StandortArt.NEUTRAL)));
				
		s.unternehmenHinzufuegen(un);
		
		Simulation.simuliereSpielstart(s);
		
		Angebot an = un.getMmarkt().getAngebote().get(0);	//Holzstuhlmaschine
		int bisherigeSize = un.getMmarkt().umsatzProMaschinenArt(MaschinenArt.Holzstuhlmaschine).size();
		
		//Maschine kaufen
		un.getMmarkt().kaufen(an, 1, un);
		
		assertEquals(bisherigeSize + 1, un.getMmarkt().umsatzProMaschinenArt(MaschinenArt.Holzstuhlmaschine).size());
		assertEquals(7000, un.getMmarkt().umsatzProMaschinenArt(MaschinenArt.Holzstuhlmaschine).get(0).getAngebot().getPreis());
		
	}
	
	
}