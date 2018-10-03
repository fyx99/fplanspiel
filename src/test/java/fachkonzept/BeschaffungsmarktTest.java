package fachkonzept;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import fachkonzept.markt.Beschaffungsmarkt;
import fachkonzept.util.MaterialArt;

class BeschaffungsmarktTest {

    @Test
    void init() {
        Beschaffungsmarkt bmarkt = new Beschaffungsmarkt();
        bmarkt.anbieten(new Angebot(new Material(MaterialArt.Edelstahl), 500, 33));
        assertEquals(1, bmarkt.getAngebote().size());
        assertEquals(0, bmarkt.getUmsatzHistorie().size());
        assertEquals(500, bmarkt.getAngebote().get(0).getMenge());
        assertEquals(33, bmarkt.getAngebote().get(0).getPreis());
        assertEquals("Edelstahl", bmarkt.getAngebote().get(0).getMarkteinheit().getName());
        assertEquals(MaterialArt.Edelstahl, ((Material)bmarkt.getAngebote().get(0).getMarkteinheit()).getMaterialArt());

        bmarkt.anbieten(new Angebot(new Material( MaterialArt.Holz), 22, 323));
        bmarkt.anbieten(new Angebot(new Material( MaterialArt.Edelstahl), 40, 78));
        assertEquals(3, bmarkt.getAngebote().size());
        assertEquals(MaterialArt.Edelstahl, ((Material)bmarkt.getAngebote().get(0).getMarkteinheit()).getMaterialArt());
        

        bmarkt.anbieten(new Angebot(new Material( MaterialArt.Edelstahl), 400, 78)); //selbes angebot
        assertEquals(3, bmarkt.getAngebote().size());
        assertEquals(440, bmarkt.getAngebote().get(2).getMenge());
        assertEquals(78, bmarkt.getAngebote().get(2).getPreis());
        bmarkt.anbieten(new Angebot(new Material( MaterialArt.Edelstahl), 0, 78)); //selbes angebot
        assertEquals(440, bmarkt.getAngebote().get(2).getMenge());
    }
    
    @Test
    void kaufen() {

        Beschaffungsmarkt bmarkt = new Beschaffungsmarkt();
        assertEquals(0, bmarkt.getAngebote().size());
        Angebot a2 =new Angebot(new Material( MaterialArt.Holz), 22, 323);
        bmarkt.anbieten(a2);
        Angebot a1 = new Angebot(new Material(MaterialArt.Edelstahl), 500, 33);
        bmarkt.anbieten(a1);
        bmarkt.anbieten(new Angebot(new Material( MaterialArt.Edelstahl), 40, 78));
        assertNotNull(Angebot.findeAngebot(a1.getId()));
        Unternehmen kk = new Unternehmen("tests", new Spiel(), "A");
        
        bmarkt.kaufen(a1, 100, kk);
        assertEquals(Integer.valueOf(100), kk.getMaterialien().get(MaterialArt.Edelstahl.name()));
        assertEquals(3, bmarkt.getAngebote().size());
        assertEquals(400, bmarkt.getAngebote().get(1).getMenge());
        assertEquals(33, bmarkt.getAngebote().get(1).getPreis());
        assertEquals(-3300, kk.getGuv().rundenErgebnis());
        
        bmarkt.kaufen(a1, 400, kk);
        assertEquals(Integer.valueOf(500), kk.getMaterialien().get(MaterialArt.Edelstahl.name()));
        assertEquals(2, bmarkt.getAngebote().size());
        
        bmarkt.kaufen(a2, 100, kk);
        assertEquals(Integer.valueOf(22), kk.getMaterialien().get(MaterialArt.Holz.name()));
        assertEquals(1, bmarkt.getAngebote().size());
    }
    
    @Test
    void verkaufen() {
        
        //todo
    }

}
