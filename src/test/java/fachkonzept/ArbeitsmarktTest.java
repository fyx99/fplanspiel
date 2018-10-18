package fachkonzept;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import fachkonzept.markt.Arbeitsmarkt;
import fachkonzept.markt.Beschaffungsmarkt;
import fachkonzept.util.MaterialArt;
import fachkonzept.util.MitarbeiterFachgebiet;

public class ArbeitsmarktTest {
	
	@Test
    void init() {
		Arbeitsmarkt am = new Arbeitsmarkt();
		
		Mitarbeiter ma1 = new Mitarbeiter("Name unnötig", 300, 120000, MitarbeiterFachgebiet.MASCHINE);
		am.anbieten(new Angebot(ma1, 20, 50));
		
		assertEquals(1, am.getAngebote().size());
		assertEquals(20, am.getAngebote().get(0).getMenge());
		assertEquals(50, am.getAngebote().get(0).getPreis());
		
		assertEquals("Name unnötig", am.getAngebote().get(0).getMarkteinheit().getName());
		assertEquals(MitarbeiterFachgebiet.MASCHINE, ((Mitarbeiter)am.getAngebote().get(0).getMarkteinheit()).getMfg());
	
		Mitarbeiter ma2 = new Mitarbeiter("Name unnötig", 200, 120000, MitarbeiterFachgebiet.VERTRIEB);
		am.anbieten(new Angebot(ma2, 10, 60));
		
		assertEquals(2, am.getAngebote().size());
		assertEquals(10, am.getAngebote().get(1).getMenge());
		assertEquals(60, am.getAngebote().get(1).getPreis());
	}
	
	@Test
	void kaufen() {
		Arbeitsmarkt am = new Arbeitsmarkt();
        assertEquals(0, am.getAngebote().size());
        
        Mitarbeiter ma1 = new Mitarbeiter("Name unnötig", 300, 120000, MitarbeiterFachgebiet.MASCHINE);
        Mitarbeiter ma2 = new Mitarbeiter("Name unnötig", 200, 120000, MitarbeiterFachgebiet.VERTRIEB);
        
        Angebot a1 =new Angebot(ma1, 22, 323);
        Angebot a2 =new Angebot(ma2, 25, 269);
        am.anbieten(a1);
        am.anbieten(a2);


        assertNotNull(Angebot.findeAngebot(a1.getId()));
        Unternehmen testUN = new Unternehmen("tests", new Spiel());
        
        assertEquals(22, am.getAngebote().get(0).getMenge());
        am.kaufen(a1, 10, testUN);
        assertEquals(12, am.getAngebote().get(0).getMenge());
        assertEquals(120000, ((Arbeitskraft)testUN.getMitarbeiter().get(0)).getM().getArbeitszeit());
        assertEquals(2, am.getAngebote().size());
        assertEquals(25, am.getAngebote().get(1).getMenge());
        assertEquals(269, am.getAngebote().get(1).getPreis());
//        assertEquals(-3300, kk.getGuv().rundenErgebnis());
	}
}