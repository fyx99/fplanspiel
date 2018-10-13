package fachkonzept;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import fachkonzept.util.KreditArt;

class VerbindlichkeitTest {

    @Test
    void init() {
        Kredit beispiel = new Kredit(10000, 0.06, 9, KreditArt.Ultra_Cash);
        
        Verbindlichkeit vTest = new Verbindlichkeit(beispiel);
        assertEquals(beispiel, vTest.getKredit());
        assertEquals(9, vTest.getKredit().getLaufzeit());
        assertEquals(10000, vTest.getKredit().getVolumen());
        assertEquals(0.06, vTest.getKredit().getZinssatz());
        assertEquals(Integer.valueOf(0), vTest.getAktuelleLaufzeit());
        assertEquals(10000, vTest.getVerbleibendeSumme());
                                                                
        Verbindlichkeit v2Test = new Verbindlichkeit(beispiel, 112.8, 3);   
        assertEquals(9, v2Test.getKredit().getLaufzeit());
        assertEquals(10000, v2Test.getKredit().getVolumen());
        assertEquals(0.06, v2Test.getKredit().getZinssatz());
        assertEquals(Integer.valueOf(3), v2Test.getAktuelleLaufzeit());
        assertEquals(112.8, v2Test.getVerbleibendeSumme());
    }
    
    @Test
    void tilgen()  {
        Kredit beispiel = new Kredit(10000, 0.06, 9, KreditArt.Kurzes_Cash);
        
        Verbindlichkeit vTest = new Verbindlichkeit(beispiel);

        assertEquals(99.9, vTest.tilgen(99.9));
        assertEquals(Integer.valueOf(1), vTest.getAktuelleLaufzeit());
        assertEquals(10000 - 99.9, vTest.getVerbleibendeSumme());
        
        assertEquals(9779, vTest.tilgen(9779));    
        assertEquals(Integer.valueOf(2), vTest.getAktuelleLaufzeit());
        assertEquals(10000 - 99.9 - 9779, vTest.getVerbleibendeSumme());
        
        
        
        Verbindlichkeit v2Test = new Verbindlichkeit(beispiel, 112.8, 3);   

        
        assertEquals(99.9, v2Test.tilgen(99.9));        
        assertEquals(Integer.valueOf(4), v2Test.getAktuelleLaufzeit());
        assertEquals(112.8 - 99.9, v2Test.getVerbleibendeSumme());
        
        assertEquals(0, v2Test.tilgen(0));        
        assertEquals(Integer.valueOf(5), v2Test.getAktuelleLaufzeit());
        assertEquals(112.8 - 99.9, v2Test.getVerbleibendeSumme());
        
        assertEquals(100, v2Test.tilgen(100));        
        assertEquals(Integer.valueOf(6), v2Test.getAktuelleLaufzeit());
        assertEquals(112.8 - 99.9 - 100, v2Test.getVerbleibendeSumme());
        
    }

}
