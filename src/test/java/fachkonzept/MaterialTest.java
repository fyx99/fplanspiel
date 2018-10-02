package fachkonzept;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import fachkonzept.markt.Markteinheit;

class MaterialTest {
    
    @Test
    void init() {
        
        Material m = new Material(1, "beispiel material");
        int testid = m.getId();
        assertEquals(1, m.getQuality());
        assertEquals("beispiel material", m.getName());
        m.setName("neuer Name2349824");
        assertEquals("neuer Name2349824", m.getName());
        

        Material m2 = new Material(2, "bdfsfl");
        assertEquals(1+ testid, m2.getId());
    }
    
    @Test
    void markteinheit() {

        Material m = new Material(1, "beispiel material");
        Material m2 = new Material(33, "beispiel material2");
        Material m3 = new Material(32, "beispiel material3");
        int testid = m3.getId();
        Material m4 = new Material(65, "beispiel material4");
        assertEquals(m2, Material.findeMarkteinheit("beispiel material2"));
        assertEquals(33, ((Material)Material.findeMarkteinheit("beispiel material2")).getQuality());
        assertEquals(33, ((Material)m.findeMarkteinheit("beispiel material2")).getQuality());

        assertEquals(m4, m.findeMarkteinheit(testid + 1));
        assertEquals(65, ((Material)Markteinheit.findeMarkteinheit(testid + 1)).getQuality());
        
        assertEquals(m2, Material.findeMarkteinheit("beispiel material2", Material.class));
        assertEquals(33, ((Material)Material.findeMarkteinheit("beispiel material2", Material.class)).getQuality());
        

        assertNull(Material.findeMarkteinheit("beispiel material2", Maschine.class));
        assertNull(Material.findeMarkteinheit(null, Material.class));
    }

}
