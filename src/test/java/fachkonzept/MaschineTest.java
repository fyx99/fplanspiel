package fachkonzept;

import static org.junit.jupiter.api.Assertions.fail;

import java.util.HashMap;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import fachkonzept.util.ProduktArt;
import fachkonzept.util.ProduktTyp;

class MaschineTest {
    static Produktionsmatrix pm ;
    static Produkt p;
    @BeforeAll
    static void setup() {
        HashMap map = new HashMap<String, Integer>();
        map.put("Holz", 68);
        map.put("Glas", 44);
        pm = new Produktionsmatrix(map);
        p= new Produkt(ProduktArt.Edelstahlschrank, ProduktTyp.Schrank);
    }
    
    @Test
    void test() {
        fail("Not yet implemented");
    }
    
    @Test
    void init() {
        
        
        Maschine mTest = new Maschine("Test Maschinea", 487, , null, 0, 0);
    }

}
