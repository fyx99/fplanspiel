package fachkonzept;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class KreditTest {

    @Test
    void init() {
        Kredit k = new Kredit(10000, 0.005, 7, "beispiel");
        assertEquals(7, k.getLaufzeit());
        
    }

}
