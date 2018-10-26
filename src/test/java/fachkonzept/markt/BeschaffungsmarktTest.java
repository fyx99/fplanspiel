package fachkonzept.markt;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import fachkonzept.Angebot;
import fachkonzept.Kredit;
import fachkonzept.Material;
import fachkonzept.Spiel;
import fachkonzept.Standort;
import fachkonzept.Unternehmen;
import fachkonzept.util.KreditArt;
import fachkonzept.util.MaterialArt;
import fachkonzept.util.SimulationsKonstanten;
import fachkonzept.util.StandortArt;

class BeschaffungsmarktTest {

    @Test
    void init() {
        Beschaffungsmarkt bmarkt = new Beschaffungsmarkt();
        bmarkt.anbieten(new Angebot(new Material(MaterialArt.Edelstahl), 500, 33));
        assertEquals(1, bmarkt.getAngebote().size());
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
        Unternehmen kk = new Unternehmen("tests", new Spiel(), new Standort(SimulationsKonstanten.getStandortFaktoren(StandortArt.NEUTRAL)));
        
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
    
    @Test
    void umsatzProMaterialArt() {
    	int size = Beschaffungsmarkt.getUmsatzHistorie().size();
    	int size2 = Beschaffungsmarkt.umsatzProMaterialArt(MaterialArt.Holz).size();
    	int size3 = Beschaffungsmarkt.umsatzProMaterialArt(MaterialArt.Edelstahl).size();
        Beschaffungsmarkt bmarkt = new Beschaffungsmarkt();
        Angebot a1 = new Angebot(new Material(MaterialArt.Edelstahl), 500, 33);
        Angebot a2 = new Angebot(new Material(MaterialArt.Holz), 50, 10);
        bmarkt.anbieten(a1);
        bmarkt.anbieten(a2);
        
        Finanzmarkt fmarkt = new Finanzmarkt();
        Angebot a3 = new Angebot(new Kredit(1555,0.05, 5,KreditArt.Mehr_Cash), 22, 11);
        fmarkt.anbieten(a3);

        bmarkt.kaufen(a1, 10, new Unternehmen("a1", new Spiel(), new Standort(SimulationsKonstanten.getStandortFaktoren(StandortArt.NEUTRAL))));
        bmarkt.kaufen(a1, 15, new Unternehmen("a1", new Spiel(), new Standort(SimulationsKonstanten.getStandortFaktoren(StandortArt.NEUTRAL))));
        bmarkt.kaufen(a2, 10, new Unternehmen("a1", new Spiel(), new Standort(SimulationsKonstanten.getStandortFaktoren(StandortArt.NEUTRAL))));
        fmarkt.kaufen(a3, 10, new Unternehmen("a1", new Spiel(), new Standort(SimulationsKonstanten.getStandortFaktoren(StandortArt.NEUTRAL))));
        
    	assertEquals(4 + size, Beschaffungsmarkt.getUmsatzHistorie().size());
    	assertEquals(1 + size2, Beschaffungsmarkt.umsatzProMaterialArt(MaterialArt.Holz).size());
    	assertEquals(2 + size3, Beschaffungsmarkt.umsatzProMaterialArt(MaterialArt.Edelstahl).size());
    	

    	assertEquals(10, Beschaffungsmarkt.umsatzProMaterialArt(MaterialArt.Edelstahl).get(0+size3).getMenge());
    	
    	
    	
    	
    	
    	
    	
    	
    	
    }

}
