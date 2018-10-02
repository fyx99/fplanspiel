package fachkonzept;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class UmsatzTest {

    @Test
    void init() {
        Angebot beispiel = new Angebot(new Produkt(ProduktArt.Holzstuhl, ProduktTyp.Stuhl), 100, 999.88);
        Umsatz testUmsatz = new Umsatz(beispiel, 49, 9);
        assertEquals(9, testUmsatz.getRunde());
        assertEquals(49, testUmsatz.getMenge());
        assertEquals("Holzstuhl", testUmsatz.getAngebot().getMarkteinheit().getName());
        assertEquals(100, testUmsatz.getAngebot().getMenge());
        assertEquals(999.88, testUmsatz.getAngebot().getPreis());
        
    }
    
    @Test
    void ttters() {
        Angebot beispiel = new Angebot(new Produkt(ProduktArt.Holzstuhl, ProduktTyp.Stuhl), 100, 999.88);
        Umsatz testUmsatz = new Umsatz(beispiel, 49, 9);
        
        testUmsatz.setAngebot(new Angebot(new Produkt(ProduktArt.Holzstuhl, ProduktTyp.Stuhl), 80, 9.88));
        assertEquals(80, testUmsatz.getAngebot().getMenge());
        assertEquals(9.88, testUmsatz.getAngebot().getPreis());
        
        assertNotEquals(testUmsatz.getAngebot(), beispiel);
        
        testUmsatz.setMenge(0);
        assertEquals(0, testUmsatz.getMenge());
    }

}
