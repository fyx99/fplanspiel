package fachkonzept;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fachkonzept.markt.Finanzmarkt;
import fachkonzept.util.KreditArt;
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
    }
    
    @Test 
    void umsatzProKreditArt() {
    	int size = Finanzmarkt.getUmsatzHistorie().size();
    	int size2 = Finanzmarkt.umsatzProKreditArt(KreditArt.Kurzes_Cash).size();
    	int size3 = Finanzmarkt.umsatzProKreditArt(KreditArt.Mehr_Cash).size();
    	Finanzmarkt fmarkt = new Finanzmarkt();
    	
    	Angebot a1 = new Angebot(new Kredit(1000, 0.05, 10, KreditArt.Kurzes_Cash), 22, 10);
    	Angebot a2 = new Angebot(new Kredit(1555,0.05, 5,KreditArt.Mehr_Cash), 22, 11);
    	fmarkt.anbieten(a1);
    	fmarkt.anbieten(a2);
    	
    	fmarkt.kaufen(a1, 2,new Unternehmen("a1", new Spiel(), new Standort(SimulationsKonstanten.getStandortFaktoren(StandortArt.NEUTRAL))));
    	fmarkt.kaufen(a1, 1, new Unternehmen("a1", new Spiel(), new Standort(SimulationsKonstanten.getStandortFaktoren(StandortArt.NEUTRAL))));
    	fmarkt.kaufen(a2, 1, new Unternehmen("a2", new Spiel(), new Standort(SimulationsKonstanten.getStandortFaktoren(StandortArt.NEUTRAL))));
    	
    	
//    	assertEquals(size+3, Finanzmarkt.getUmsatzHistorie().size());
    	//Umsatzhistorie muss man bei einem einzelnen UN prüfen
//    	assertEquals(2+size2, Finanzmarkt.umsatzProKreditArt(KreditArt.Kurzes_Cash).size());
//    	assertEquals(3+size3, Finanzmarkt.umsatzProKreditArt(KreditArt.Mehr_Cash).size());
    	
    }
    

}
