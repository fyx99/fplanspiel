package fachkonzept;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import fachkonzept.markt.Absatzmarkt;
import fachkonzept.markt.Arbeitsmarkt;
import fachkonzept.markt.Beschaffungsmarkt;
import fachkonzept.markt.Finanzmarkt;
import fachkonzept.markt.Maschinenmarkt;

class MarktTest {
    static Unternehmen u;
    static Maschinenmarkt m;
    static Beschaffungsmarkt b;
    static Arbeitsmarkt a;
    static Absatzmarkt v;
    static Finanzmarkt f;

    @BeforeAll
    static void setup() {
        u = new Unternehmen("Testing U", new Spiel());
        m = new Maschinenmarkt();
        b = new Beschaffungsmarkt();
        a = new Arbeitsmarkt();
        v = new Absatzmarkt();
        f = new Finanzmarkt();
    }
    
    @Test
    void marktErstellen() {
        Beschaffungsmarkt bm = new Beschaffungsmarkt();
        Maschinenmarkt mm = new Maschinenmarkt();
        
    }
    
    @Test
    void maschinenMarkt() {
        assertEquals(0, m.getAngebote().size());
        m.anbieten(new Angebot(new Material(1, "Hölchen"), 77, 77777.64));
        assertEquals(1, m.getAngebote().size());
        assertNotNull(m.getAngebote().get(0));
        assertNotNull(m.getAngebote().get(0).getMarkteinheit());
        assertNotNull(m.getAngebote().get(0).getId());
    }
}
