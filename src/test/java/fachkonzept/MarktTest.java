package fachkonzept;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;

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
        u = new Unternehmen("Testing U", new Spiel(), "A");
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
        HashMap map = new HashMap<String, Integer>();
        map.put("Holz", 68);
        map.put("Glas", 44);
        Produktionsmatrix pm = new Produktionsmatrix(map);
        assertNotNull(pm);
        assertEquals(2, pm.getMatrix().size());
        assertTrue(pm.getMatrix().containsKey("Holz"));
        assertTrue(pm.getMatrix().containsKey("Glas"));
        assertEquals(Integer.valueOf(68), pm.getMatrix().get("Holz"));
        assertEquals(Integer.valueOf(44), pm.getMatrix().get("Glas"));

        Maschine m2 = new Maschine("TetsMaschine", 1040, new Produkt(ProduktArt.Edelstahlschrank, ProduktTyp.Schrank),pm,45,12);
        Maschine m3 = new Maschine("TetsMaschine3", 458, new Produkt(ProduktArt.Holztisch, ProduktTyp.Tisch),pm,23.99,2);
        Angebot aa1 = new Angebot(m2, 77, 77777.64);
        m.anbieten(aa1);
        m.anbieten(new Angebot(m3, 222, 77.64));

        assertEquals(3, m.getAngebote().size());
        assertEquals("TetsMaschine3", m.getAngebote().get(2).getMarkteinheit().getName());
        assertEquals("TetsMaschine", m.getAngebote().get(1).getMarkteinheit().getName());
        assertEquals(77.64, m.getAngebote().get(2).getPreis());
        assertEquals(222, m.getAngebote().get(2).getMenge());
        assertTrue(m.getAngebote().get(2).getMarkteinheit() instanceof Maschine);
        
        assertEquals(1040, m2.getKapazitaet());
        assertEquals(1040, ((Maschine)m.getAngebote().get(1).getMarkteinheit()).getKapazitaet());
        assertEquals(12, ((Maschine)m.getAngebote().get(1).getMarkteinheit()).getArbeitszeit());
        assertEquals(45, ((Maschine)m.getAngebote().get(1).getMarkteinheit()).getFertigungskosten());
        assertEquals(0, ((Maschine)m.getAngebote().get(1).getMarkteinheit()).getAuslastung());
        assertEquals(pm, ((Maschine)m.getAngebote().get(1).getMarkteinheit()).getMatrix());
        assertEquals("Edelstahlschrank", ((Maschine)m.getAngebote().get(1).getMarkteinheit()).getP().getName());
        assertEquals("Edelstahlschrank", ((Maschine)m.getAngebote().get(1).getMarkteinheit()).getP().getProduktArt().name());
        
        //jetzt mal was mit produzieren :D
        u.setMmarkt(m);
        
        u.getMmarkt().anbieten(new Angebot(m2, 33, 1000.5005));
        assertEquals(4, u.getMmarkt().getAngebote().size());
        
        assertEquals(0, u.getMaschinen().size());
        assertEquals(0, u.getGuv().getAusgaben().size());
        int tid = u.getMmarkt().getAngebote().get(1).getId();
        //hier könnte man noch die id asserten
        assertNotNull(Angebot.findeAngebot(tid));
        assertEquals(aa1, Angebot.findeAngebot(tid));
        assertEquals(77, Angebot.findeAngebot(tid).getMenge());
        assertEquals(77777.64, Angebot.findeAngebot(tid).getPreis());
        Angebot kaAn = Angebot.findeAngebot(tid);
        assertEquals(kaAn, aa1);
        
        u.getMmarkt().kaufen(kaAn, 7, u);
        assertEquals(70, kaAn.getMenge());
        assertEquals(70, aa1.getMenge());
        assertEquals(77777.64, aa1.getPreis());
        
        
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
