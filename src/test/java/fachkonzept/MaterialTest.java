package fachkonzept;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import fachkonzept.markt.Markteinheit;
import fachkonzept.util.MaterialArt;

class MaterialTest {
    
    @Test
    void init() {
        
        Material m = new Material(1, MaterialArt.Glas);
        int testid = m.getId();
        assertEquals(1, m.getQuality());
        assertEquals("Glas", m.getName());
        m.setName("neuer Name2349824");
        assertEquals("neuer Name2349824", m.getName());
        

        Material m2 = new Material(2, MaterialArt.Stoff);
        assertEquals(1+ testid, m2.getId());
    }
    
    @Test
    void markteinheit() {

        Material m = new Material(1, MaterialArt.Stoff);
        Material m2 = new Material(33, MaterialArt.Holz);
        Material m3 = new Material(32, MaterialArt.Glas);
        int testid = m3.getId();
        Material m4 = new Material(65, MaterialArt.Leder);
        assertEquals(m2, Material.findeMarkteinheit("Holz"));
        assertEquals(33, ((Material)Material.findeMarkteinheit("Holz")).getQuality());
        assertEquals(33, ((Material)m.findeMarkteinheit("Holz")).getQuality());

        assertEquals(m4, m.findeMarkteinheit(testid + 1));
        assertEquals(65, ((Material)Markteinheit.findeMarkteinheit(testid + 1)).getQuality());
        
        assertEquals(m2, Material.findeMarkteinheit("Holz", Material.class));
        assertEquals(33, ((Material)Material.findeMarkteinheit("Holz", Material.class)).getQuality());
        

        assertNull(Material.findeMarkteinheit("Holz", Maschine.class));
        assertNull(Material.findeMarkteinheit(null, Material.class));
    }

}
