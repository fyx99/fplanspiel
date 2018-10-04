package fachkonzept;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import fachkonzept.util.MaterialArt;

class MaterialTest {
    
    @Test
    void init() {
        
        Material m = new Material( MaterialArt.Glas);
        int testid = m.getId();
        assertEquals("Glas", m.getName());
        m.setName("neuer Name2349824");
        assertEquals("neuer Name2349824", m.getName());
        

        Material m2 = new Material( MaterialArt.Stoff);
        assertEquals(1+ testid, m2.getId());
    }
    
    @Test
    void markteinheit() {

        Material m = new Material( MaterialArt.Stoff);
        Material m2 = new Material( MaterialArt.Holz);
        Material m3 = new Material( MaterialArt.Glas);
        int testid = m3.getId();
        Material m4 = new Material( MaterialArt.Leder);
        assertEquals(m4, m.findeMarkteinheit(testid + 1));
        
    }
    
    @Test
    void materialArt() {
        Material m = new Material( MaterialArt.Stoff);
        MaterialArt ma = MaterialArt.Edelstahl;
        m.setMaterialArt(ma);
        assertEquals(MaterialArt.Edelstahl, m.getMaterialArt() );
    }

}
