package fachkonzept;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import fachkonzept.util.KreditArt;

class KreditTest {

    @Test
    void init() {
        Kredit k = new Kredit(10000, 0.005, 7, KreditArt.Kurzes_Cash);
        assertEquals(7, k.getLaufzeit());
        
    }

}
