package fachkonzept;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fachkonzept.util.MaschinenArt;
import fachkonzept.util.MitarbeiterFachgebiet;
import fachkonzept.util.ProduktArt;
import fachkonzept.util.ProduktTyp;
import fachkonzept.util.SimulationsKonstanten;
import fachkonzept.util.StandortArt;

class MaschineTest {
    Produktionsmatrix pm ;
    Produkt p;
    Maschine m;
    @BeforeEach
    void setup() {
        Map map = new HashMap<String, Integer>();
        map.put("Holz", 68);
        map.put("Glas", 4);
        map.put("Edelstahl", 434);
        map.put("Stoff", 74);
        map.put("Leder", 1);
        map.put("Kunststoff", 0);
        pm = new Produktionsmatrix(map);
        p= new Produkt(ProduktArt.Edelstahlschrank, ProduktTyp.Schrank);
        m = new Maschine(MaschinenArt.Holzschrankmaschine, 600, p, pm, 50, 50);
    }
   
    @Test
    void init() {
        
        Maschine mTest = new Maschine(MaschinenArt.Holzstuhlmaschine, 487, p, pm, 15, 34);
        mTest.setArbeitszeit(34);
        assertEquals(34, mTest.getArbeitszeit());
        mTest.setAuslastung(0);
        assertEquals(0, mTest.getAuslastung());
        mTest.setFertigungskosten(15);
        assertEquals(15, mTest.getFertigungskosten());
        mTest.setKapazitaet(487);
        assertEquals(487, mTest.getKapazitaet());
        assertNotNull(mTest.getId());
        assertEquals(pm, mTest.getMatrix());
        mTest.setMaschinenArt(MaschinenArt.Holzstuhlmaschine);
        mTest.setP(p);
        assertEquals(MaschinenArt.Holzstuhlmaschine, mTest.getMaschinenArt());
        assertEquals("Holzstuhlmaschine", mTest.getName());
        assertEquals(ProduktArt.Edelstahlschrank, mTest.getP().getProduktArt());
        assertEquals(6, mTest.getMatrix().getMatrix().size());
        
        Maschine m2Test = new Maschine(m);
        assertEquals(50, m2Test.getArbeitszeit());
        assertEquals(0, m2Test.getAuslastung());
        assertEquals(50, m2Test.getFertigungskosten());
        assertEquals(600, m2Test.getKapazitaet());
        assertNotNull(m2Test.getId());
        assertEquals(pm, m2Test.getMatrix());
        assertEquals(MaschinenArt.Holzschrankmaschine, m2Test.getMaschinenArt());
        assertEquals("Holzschrankmaschine", m2Test.getName());
        assertEquals(ProduktArt.Edelstahlschrank, m2Test.getP().getProduktArt());
        assertEquals(6, m2Test.getMatrix().getMatrix().size());
    }
    
    @Test
    void produziere() {
        Unternehmen u = new Unternehmen("a", new Spiel(), new Standort(SimulationsKonstanten.getStandortFaktoren(StandortArt.NEUTRAL)));
        u.arbeitskraftHinzu(new Arbeitskraft(0, new Mitarbeiter("name test", 777.88, 70000, MitarbeiterFachgebiet.MASCHINE)));
        u.setKapital(50000);
        Produkt p = m.produziere(9, u);
        assertNotNull(p);
        assertEquals(9, m.getAuslastung());
        assertEquals(1, u.getProdukte().size());
        //assertEquals(Integer.valueOf(9), u.getProdukte().get(p));
        assertEquals(1, u.getProdukte().size());

        assertEquals(49550, u.getKapital());
        assertEquals(1, u.getGuv().getAusgaben().size());
        assertEquals(450, u.getGuv().getAusgaben().get(0).getSumme());
        assertEquals("Fertigungskosten", u.getGuv().getAusgaben().get(0).getBeschreibung());
//       assertEquals(450, u.getMitarbeiter().get(0).getAuslastung());
        
        
    }
    
    
    
    
    
    
    
    
    

}
