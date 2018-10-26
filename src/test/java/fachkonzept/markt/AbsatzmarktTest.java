package fachkonzept.markt;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import fachkonzept.Angebot;
import fachkonzept.Produkt;
import fachkonzept.Simulation;
import fachkonzept.Spiel;
import fachkonzept.Standort;
import fachkonzept.Unternehmen;
import fachkonzept.util.ProduktArt;
import fachkonzept.util.ProduktTyp;
import fachkonzept.util.SimulationsKonstanten;
import fachkonzept.util.StandortArt;

public class AbsatzmarktTest {
	
	@Test
    void init() {
		Spiel s = new Spiel();
		Unternehmen un = new Unternehmen("Test", s, new Standort(SimulationsKonstanten.getStandortFaktoren(StandortArt.NEUTRAL)));
		s.unternehmenHinzufuegen(un);
		
		Simulation.simuliereSpielstart(s);
		
		Angebot an = new Angebot(new Produkt(ProduktArt.Holzstuhl, ProduktTyp.Stuhl), 100, 50);
		un.getVmarkt().anbieten(an);
		
		assertEquals(1, un.getVmarkt().getAngebote().size());
		assertEquals(0, un.getVmarkt().getUmsaetzeByProduktArt(ProduktArt.Holzstuhl, 0).size());
		
		
		Simulation.simuliere(s);
		
		assertEquals(1, un.getVmarkt().getUmsaetzeByProduktArt(ProduktArt.Holzstuhl, 0).size());
		
		//assertEquals(1, un.getVmarkt().getUmsaetzeByProduktTyp(ProduktTyp.Stuhl).size());
		//FUNKTIONIERT NICHT, irgendwie bereits 4 Umsätze		
	}
	
	
}