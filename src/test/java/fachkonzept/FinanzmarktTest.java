package fachkonzept;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import fachkonzept.markt.Finanzmarkt;

class FinanzmarktTest {

    @Test
    void init() {
        Finanzmarkt fmTest = new Finanzmarkt();
        assertNotNull(fmTest.getAngebote());
    }
    
    @Test
    void kaufen() {
        Kredit k = new Kredit(1000, 0.05, 10, "Bienen-Kredit");
        Angebot beispiel = new Angebot(k, 22, 10);
        
    }
    

}
