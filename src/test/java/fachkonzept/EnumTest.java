package fachkonzept;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import fachkonzept.util.ProduktArt;
import fachkonzept.util.ProduktTyp;

class EnumTest {

    @Test
    void produktArt() {
        assertEquals(9, ProduktArt.getProduktArten().size());
        assertTrue(ProduktArt.getProduktArten().contains("Holztisch"));
    }
    
    @Test
    void produktTyp() {
        assertEquals(3, ProduktTyp.getProduktTypen().size());
        assertTrue(ProduktTyp.getProduktTypen().contains("Tisch"));
    }

}
