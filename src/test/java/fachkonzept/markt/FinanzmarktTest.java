package fachkonzept.markt;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fachkonzept.Angebot;
import fachkonzept.Arbeitskraft;
import fachkonzept.Kredit;
import fachkonzept.Mitarbeiter;
import fachkonzept.Spiel;
import fachkonzept.Standort;
import fachkonzept.Unternehmen;
import fachkonzept.util.KreditArt;
import fachkonzept.util.MitarbeiterFachgebiet;
import fachkonzept.util.SimulationsKonstanten;
import fachkonzept.util.StandortArt;

class FinanzmarktTest {

    Unternehmen testU;
    @BeforeEach
    void setup() {
        testU = new Unternehmen("Test", new Spiel(), new Standort(SimulationsKonstanten.getStandortFaktoren(StandortArt.NEUTRAL)));
        testU.setKapital(2500.01);
    }
    
    @Test
    void init() {
        Finanzmarkt fmTest = new Finanzmarkt();
        assertNotNull(fmTest.getAngebote());
    }
    
    @Test
    void kaufen() {
		testU.arbeitskraftHinzu(new Arbeitskraft(new Mitarbeiter("name", 8878, 8600, MitarbeiterFachgebiet.VERWALTUNG )));
        Kredit k = new Kredit(1000, 0.05, 10, KreditArt.Kurzes_Cash);
        Angebot beispiel = new Angebot(k, 22, 10);
        
        assertNotNull(testU.getFmarkt());
        testU.getFmarkt().anbieten(beispiel);
        assertEquals(22, testU.getFmarkt().getAngebote().get(0).getMenge());
        assertEquals(10, testU.getFmarkt().getAngebote().get(0).getPreis());
        
        //jetzt kauf

        assertEquals(1, testU.getFmarkt().getAngebote().size());
        
        testU.getFmarkt().kaufen(beispiel, 1, testU);

        assertEquals(1, testU.getFmarkt().getAngebote().size());
        assertEquals(21, testU.getFmarkt().getAngebote().get(0).getMenge());
        assertEquals(10, testU.getFmarkt().getAngebote().get(0).getPreis());

        assertEquals(1000, testU.getVerbindlichkeiten().get(0).getVerbleibendeSumme());
        assertEquals(1000, testU.getVerbindlichkeiten().get(0).getKredit().getVolumen());
        assertEquals(8350, testU.getMitarbeiterKapazitaeten(MitarbeiterFachgebiet.VERWALTUNG));
    }
    
    @Test 
    void umsatzProKreditArt() {
    	Finanzmarkt finanzmarkt = testU.getFmarkt();
    	
    	int size1 = finanzmarkt.umsatzProKreditArt(KreditArt.Ultra_Cash).size();
    	assertEquals(1, size1);
    	
    	//hier bereits 1x Umsatz von der KreditArt.UltraCash aus den anderen Tests
    	
    	//Angebot Anbieten:
    	finanzmarkt.anbieten(new Angebot(new Kredit(5000, 5, 5, KreditArt.Ultra_Cash), 5, 500));
    	Angebot an = finanzmarkt.getAngebote().get(0);
    	
    	//Angebot kaufen
    	finanzmarkt.kaufen(an, 1, testU);
    	assertEquals(size1 + 1, finanzmarkt.umsatzProKreditArt(KreditArt.Ultra_Cash).size());
    	
    	
    	
    	
    	
    	
    	
    	
    }
    

}
