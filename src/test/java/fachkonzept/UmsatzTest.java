package fachkonzept;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import fachkonzept.util.ProduktArt;
import fachkonzept.util.ProduktTyp;
import fachkonzept.util.SimulationsKonstanten;
import fachkonzept.util.StandortArt;

class UmsatzTest {

    @Test
    void init() {
        Angebot beispiel = new Angebot(new Produkt(ProduktArt.Holzstuhl, ProduktTyp.Stuhl), 100, 999.88);
        Umsatz testUmsatz = new Umsatz(beispiel, 49, 9, null);
        Umsatz testUmsatz2 = new Umsatz(beispiel, 49, 9, new Unternehmen("beispiel unternehmen aggg", new Spiel(), new Standort(SimulationsKonstanten.getStandortFaktoren(StandortArt.NEUTRAL))));
        assertEquals(9, testUmsatz.getRunde());
        assertEquals(49, testUmsatz.getMenge());
        assertEquals("Holzstuhl", testUmsatz.getAngebot().getMarkteinheit().getName());
        assertEquals(100, testUmsatz.getAngebot().getMenge());
        assertEquals(999.88, testUmsatz.getAngebot().getPreis());
        assertNull(testUmsatz.getVerkaeufer());
        assertEquals("beispiel unternehmen aggg", testUmsatz2.getVerkaeufer().getName());
    }
    
    @Test
    void ttters() {
        Angebot beispiel = new Angebot(new Produkt(ProduktArt.Holzstuhl, ProduktTyp.Stuhl), 100, 999.88);
        Umsatz testUmsatz = new Umsatz(beispiel, 49, 9, null);
        
        testUmsatz.setAngebot(new Angebot(new Produkt(ProduktArt.Holzstuhl, ProduktTyp.Stuhl), 80, 9.88));
        assertEquals(80, testUmsatz.getAngebot().getMenge());
        assertEquals(9.88, testUmsatz.getAngebot().getPreis());
        
        assertNotEquals(testUmsatz.getAngebot(), beispiel);
        
        testUmsatz.setMenge(0);
        assertEquals(0, testUmsatz.getMenge());
    }

}
