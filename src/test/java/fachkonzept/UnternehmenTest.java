package fachkonzept;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class UnternehmenTest {
    static Unternehmen testUnternehmen;
    @BeforeAll
    static void setUp() {
        Spiel s = new Spiel();
        s.unternehmenHinzufuegen(testUnternehmen = new Unternehmen("TestU", s, "A"));
        s.rundenStart();    //-> anfangs simulation triggern
    }
    
    @Test
    void unternehmenErstellen() {
        Unternehmen u = new Unternehmen("Test  Name", new Spiel(), "A");
        u.setKapital(10000.99);
        
        assertEquals("Test  Name", u.getName());
        assertEquals(10000.99, u.getKapital());
        
    }
    
    
    @Test
    void verringKapital() {
        testUnternehmen.setKapital(1000.88);
        assertEquals(1000.88, testUnternehmen.getKapital());
        
        testUnternehmen.kosten(250.48, "..s-.Test Beschreibung");
        assertEquals(750.40, testUnternehmen.getKapital());
        assertNotNull(testUnternehmen.getGuv());
        assertNotNull(testUnternehmen.getGuv().getAusgaben());
        
        assertEquals(1, testUnternehmen.getGuv().getAusgaben().size());
        assertEquals(250.48, testUnternehmen.getGuv().getAusgaben().get(0).getSumme());
        assertEquals("..s-.Test Beschreibung", testUnternehmen.getGuv().getAusgaben().get(0).getBeschreibung());
        
    }
    
    @Test
    void erhoeheKapital() {
        testUnternehmen.setKapital(2000.88);
        assertEquals(2000.88, testUnternehmen.getKapital());
        
        testUnternehmen.umsatz(250.04, "..s-.Test Beschreibung22");
        assertEquals(2250.92, testUnternehmen.getKapital());
        assertNotNull(testUnternehmen.getGuv());
        assertNotNull(testUnternehmen.getGuv().getEinnahmen());
        
        assertEquals(1, testUnternehmen.getGuv().getEinnahmen().size());
        assertEquals(250.04, testUnternehmen.getGuv().getEinnahmen().get(0).getSumme());
        assertEquals("..s-.Test Beschreibung22", testUnternehmen.getGuv().getEinnahmen().get(0).getBeschreibung());
        

        testUnternehmen.umsatz(1.00000, "1€");
        
        assertEquals(2, testUnternehmen.getGuv().getEinnahmen().size());
        assertEquals(1, testUnternehmen.getGuv().getEinnahmen().get(1).getSumme());
        assertEquals("1€", testUnternehmen.getGuv().getEinnahmen().get(1).getBeschreibung());
    }
    
}
