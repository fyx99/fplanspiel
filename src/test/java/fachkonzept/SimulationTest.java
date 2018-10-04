package fachkonzept;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SimulationTest {
    Spiel s;
    Unternehmen u1;
    Unternehmen u2;
    Unternehmen u3;
    @BeforeEach
    void setUp() {
        
       s= new Spiel();
       s.unternehmenHinzufuegen(u1 = new Unternehmen("tst", s, "A"));
       s.unternehmenHinzufuegen(u2 = new Unternehmen("ts2t", s, "A"));
       s.unternehmenHinzufuegen(u3 = new Unternehmen("t3st", s, "A"));
       s.rundenStart();
    }
    
    @Test
    void kreditTilgung() {
        Verbindlichkeit v1 = new Verbindlichkeit(new Kredit(50000,0.05,7, "Test Kredit"), 40000, 2);
        u1.verbindlichkeitHinzu(v1);
        u1.setKapital(1000000);
        assertEquals(0, u1.getGuv().rundenErgebnis());
        Simulation.simuliereKredittilgung(u1);
        assertEquals(1, u1.getGuv().getAusgaben().size());
        assertEquals(32000, u1.getVerbindlichkeiten().get(0).getVerbleibendeSumme());
        assertEquals(Integer.valueOf(3), u1.getVerbindlichkeiten().get(0).getAktuelleLaufzeit());
        assertEquals(10000, u1.getGuv().getAusgaben().get(0).getSumme());
        assertEquals("Kreditkosten", u1.getGuv().getAusgaben().get(0).getBeschreibung());
        assertEquals(990000, u1.getKapital());
        assertEquals(-10000, u1.getGuv().rundenErgebnis());

        Simulation.simuliereKredittilgung(u1);
        assertEquals(24000, u1.getVerbindlichkeiten().get(0).getVerbleibendeSumme());
        assertEquals(Integer.valueOf(4), u1.getVerbindlichkeiten().get(0).getAktuelleLaufzeit());
        assertEquals(9600, u1.getGuv().getAusgaben().get(1).getSumme());
        assertEquals(980400, u1.getKapital());
        assertEquals(-19600, u1.getGuv().rundenErgebnis());
        
        Simulation.simuliereKredittilgung(u1);
        assertEquals(16000, u1.getVerbindlichkeiten().get(0).getVerbleibendeSumme());
        assertEquals(Integer.valueOf(5), u1.getVerbindlichkeiten().get(0).getAktuelleLaufzeit());
        assertEquals(9200, u1.getGuv().getAusgaben().get(2).getSumme());
        assertEquals(971200, u1.getKapital());
        assertEquals(-28800, u1.getGuv().rundenErgebnis());

        Simulation.simuliereKredittilgung(u1);
        assertEquals(8000, u1.getVerbindlichkeiten().get(0).getVerbleibendeSumme());
        assertEquals(Integer.valueOf(6), u1.getVerbindlichkeiten().get(0).getAktuelleLaufzeit());
        assertEquals(8800, u1.getGuv().getAusgaben().get(3).getSumme());
        assertEquals(-37600, u1.getGuv().rundenErgebnis());
        
        Simulation.simuliereKredittilgung(u1);
        assertEquals(8400, u1.getGuv().getAusgaben().get(4).getSumme());
        assertEquals(-46000, u1.getGuv().rundenErgebnis());
        //jetzt ist der eigentlich getilgt
        assertEquals(0, u1.getVerbindlichkeiten().size());
        
        Simulation.simuliereKredittilgung(u1);

        assertEquals(-46000, u1.getGuv().rundenErgebnis());
        
    }

}
