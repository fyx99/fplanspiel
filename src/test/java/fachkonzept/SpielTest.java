package fachkonzept;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fachkonzept.util.SimulationsKonstanten;
import fachkonzept.util.StandortArt;

class SpielTest {
    Spiel testSpiel;

    @BeforeEach

    void vorbereiten() {
        testSpiel = new Spiel(10);
        testSpiel.unternehmenHinzufuegen(new Unternehmen("UnternehmenTest1", testSpiel, new Standort(SimulationsKonstanten.getStandortFaktoren(StandortArt.NEUTRAL))));
        testSpiel.unternehmenHinzufuegen(new Unternehmen("UnternehmenTest2", testSpiel, new Standort(SimulationsKonstanten.getStandortFaktoren(StandortArt.NEUTRAL))));
        testSpiel.unternehmenHinzufuegen(new Unternehmen("UnternehmenTest3", testSpiel, new Standort(SimulationsKonstanten.getStandortFaktoren(StandortArt.NEUTRAL))));
        testSpiel.rundenStart();
    }

    @Test
    void spielErstellenRundenAnzahl() {
        int rundenAnzahl = 9;
        Spiel spiel = new Spiel(rundenAnzahl);
        assertEquals(rundenAnzahl, spiel.getRundenAnzahl());
    }

    @Test
    void unternehmenHinzu() {
        Spiel spiel = new Spiel();
        Unternehmen u1 = new Unternehmen("test", spiel, new Standort(SimulationsKonstanten.getStandortFaktoren(StandortArt.NEUTRAL)));
        spiel.unternehmenHinzufuegen(u1);
        spiel.rundenStart();
        assertEquals(1, spiel.getUnternehmen().size());
        assertTrue(spiel.getAktuellesUnternehmen().equals(u1));
        assertNotNull(spiel.getAktuellesUnternehmen());
        assertNotNull(spiel.getUnternehmen());
        assertNotNull(spiel.getAktuellesUnternehmen().getSpiel());
    }

    @Test
    void zugBeendet() {
        assertEquals("UnternehmenTest1", testSpiel.getAktuellesUnternehmen().getName());
        // runde 1
        assertEquals(1, testSpiel.getRunde());

        assertEquals(Integer.valueOf(0), testSpiel.zugBeendet());
        assertEquals(1, testSpiel.getRunde());
        assertEquals("UnternehmenTest2", testSpiel.getAktuellesUnternehmen().getName());

        assertEquals(Integer.valueOf(0), testSpiel.zugBeendet());
        assertEquals(1, testSpiel.getRunde());
        assertEquals("UnternehmenTest3", testSpiel.getAktuellesUnternehmen().getName());

        // runde 2
        assertEquals(Integer.valueOf(2), testSpiel.zugBeendet());
        assertEquals(2, testSpiel.getRunde());
        assertEquals("UnternehmenTest1", testSpiel.getAktuellesUnternehmen().getName());

        assertEquals(Integer.valueOf(0), testSpiel.zugBeendet());
        assertEquals(2, testSpiel.getRunde());
        assertEquals("UnternehmenTest2", testSpiel.getAktuellesUnternehmen().getName());

        assertEquals(Integer.valueOf(0), testSpiel.zugBeendet());
        assertEquals(2, testSpiel.getRunde());
        assertEquals("UnternehmenTest3", testSpiel.getAktuellesUnternehmen().getName());

        // runde 3

        testSpiel.zugBeendet();
        testSpiel.zugBeendet();
        testSpiel.zugBeendet();

        testSpiel.zugBeendet();
        assertNotNull(testSpiel.getAktuellesUnternehmen());
        testSpiel.zugBeendet();

        assertNotNull(testSpiel.getAktuellesUnternehmen());
        testSpiel.zugBeendet();

        assertNotNull(testSpiel.getAktuellesUnternehmen());

        // 5
        testSpiel.zugBeendet();
        testSpiel.zugBeendet();
        testSpiel.zugBeendet();

        testSpiel.zugBeendet();
        testSpiel.zugBeendet();
        testSpiel.zugBeendet();

        testSpiel.zugBeendet();
        testSpiel.zugBeendet();
        testSpiel.zugBeendet();
        // 8
        assertEquals(Integer.valueOf(8), testSpiel.zugBeendet());
        assertEquals(8, testSpiel.getRunde());
        assertEquals("UnternehmenTest1", testSpiel.getAktuellesUnternehmen().getName());

        assertEquals(Integer.valueOf(0), testSpiel.zugBeendet());
        assertEquals(8, testSpiel.getRunde());
        assertEquals("UnternehmenTest2", testSpiel.getAktuellesUnternehmen().getName());

        assertEquals(Integer.valueOf(0), testSpiel.zugBeendet());
        assertEquals(8, testSpiel.getRunde());
        assertEquals("UnternehmenTest3", testSpiel.getAktuellesUnternehmen().getName());

        // 9
        testSpiel.zugBeendet();
        testSpiel.zugBeendet();
        testSpiel.zugBeendet();
        // 10 soll noch gespielt werden, dann ende
        testSpiel.zugBeendet();
        testSpiel.zugBeendet();
        // bei 10 muss der dritte noch beenden<
        testSpiel.zugBeendet();
        assertEquals(Integer.valueOf(-1), testSpiel.zugBeendet());
        assertEquals(Integer.valueOf(-1), testSpiel.zugBeendet());
        assertEquals(Integer.valueOf(-1), testSpiel.zugBeendet());
        assertEquals(Integer.valueOf(-1), testSpiel.zugBeendet());

    }
    
    @Test
    void rangliste() {
        testSpiel.getUnternehmen().get(0).kosten(17, "Test");
        testSpiel.getUnternehmen().get(1).umsatz(17, "Test");
        testSpiel.rundenStart();
        assertEquals(3, testSpiel.getRangliste().size());
        assertEquals(17, testSpiel.getRangliste().get(0).getGuv().rundenErgebnis());
        assertEquals(0, testSpiel.getRangliste().get(1).getGuv().rundenErgebnis());
        assertEquals(-17, testSpiel.getRangliste().get(2).getGuv().rundenErgebnis());
        assertEquals("UnternehmenTest2", testSpiel.getRangliste().get(0).getName());
        assertEquals("UnternehmenTest3", testSpiel.getRangliste().get(1).getName());
        assertEquals("UnternehmenTest1", testSpiel.getRangliste().get(2).getName());
        
    }

}
