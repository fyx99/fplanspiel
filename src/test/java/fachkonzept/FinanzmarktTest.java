package fachkonzept;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fachkonzept.markt.Finanzmarkt;
import fachkonzept.util.KreditArt;

class FinanzmarktTest {

    Unternehmen testU;
    @BeforeEach
    void setup() {
        testU = new Unternehmen("Test", new Spiel(), "A");
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
    

}
