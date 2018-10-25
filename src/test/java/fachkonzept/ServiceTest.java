package fachkonzept;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import service.SpielService;

class ServiceTest {
    
    @BeforeEach
    void beforeEaching() {
        SpielService.neuesSpiel();
        SpielService.neuesUnternehmen("Test Unternehmen AG 107", "A");
        SpielService.neuesUnternehmen("Test Unternehmen AG 108", "B");
        SpielService.neuesUnternehmen("Test Unternehmen AG 109", "C");
        SpielService.spielStart(3);
    }

    @Test
    void spielInit() {
        //start eines spiels
        assertEquals("test ping", SpielService.ping("test ping"));
        SpielService.neuesSpiel();
        assertNotNull(SpielService.getSpiel());
        assertEquals(0, SpielService.getSpiel().getRunde());
        assertEquals(0, SpielService.getSpiel().getUnternehmen().size());
        
        SpielService.neuesUnternehmen("Test Unternehmen AG 87", "A");
        assertEquals(1, SpielService.getSpiel().getUnternehmen().size());
        assertEquals("Test Unternehmen AG 87", SpielService.getSpiel().getUnternehmen().get(0).getName());
        //assertEquals(0.8, SpielService.getSpiel().getAktuellesUnternehmen().getStandort().getMitarbeiterKosten());

        SpielService.neuesUnternehmen("Test Unternehmen AG 88", "B");
        SpielService.neuesUnternehmen("Test Unternehmen AG 89", "C");
        assertEquals(3, SpielService.getSpiel().getUnternehmen().size());
        assertNull(SpielService.getSpiel().getAktuellesUnternehmen());
        
        SpielService.spielStart(2);

        assertEquals(1, SpielService.getSpiel().getRunde());
        assertEquals(2, SpielService.getSpiel().getRundenAnzahl());
        assertEquals("Test Unternehmen AG 87", SpielService.getSpiel().getAktuellesUnternehmen().getName());
        assertEquals(6, SpielService.getSpiel().getAktuellesUnternehmen().getBmarkt().getAngebote().size());

        assertEquals(Integer.valueOf(0), SpielService.zugBeendet());
        assertEquals("Test Unternehmen AG 88", SpielService.getSpiel().getAktuellesUnternehmen().getName());
        
        assertEquals(Integer.valueOf(0), SpielService.zugBeendet());
        assertEquals("Test Unternehmen AG 89", SpielService.getSpiel().getAktuellesUnternehmen().getName());
        
        assertEquals(Integer.valueOf(2), SpielService.zugBeendet());
        assertEquals("Test Unternehmen AG 87", SpielService.getSpiel().getAktuellesUnternehmen().getName());
        
        assertEquals(Integer.valueOf(0), SpielService.zugBeendet());
        assertEquals("Test Unternehmen AG 88", SpielService.getSpiel().getAktuellesUnternehmen().getName());
        
        assertEquals(Integer.valueOf(0), SpielService.zugBeendet());
        assertEquals("Test Unternehmen AG 89", SpielService.getSpiel().getAktuellesUnternehmen().getName());
        

        assertEquals(Integer.valueOf(-1), SpielService.zugBeendet());
    }
    
    @Test
    void datenSchnittstellen() {

        assertNotNull(SpielService.getAMarkt());
        assertNotNull(SpielService.getBMarkt());
        assertNotNull(SpielService.getFMarkt());
        assertNotNull(SpielService.getMMarkt());
        assertNotNull(SpielService.getVMarkt());
        assertNotNull(SpielService.getMaterialien());
        assertNotNull(SpielService.getMaschinen());
        assertNotNull(SpielService.getProdukte());
        assertNotNull(SpielService.getKredite());
        assertNotNull(SpielService.getMitarbeiter());
        assertNotNull(SpielService.getMitarbeiter());
        assertNotNull(SpielService.getUnternehmen());
        assertNotNull(SpielService.getUmsatzHistorie());
        assertNotNull(SpielService.getMarketingmix());
        //kosequenter weise könnte man alle nach richtigkeit untersuchen
    }
    
    @Test
    void anbieten() {
        //todo
    }
    
    @Test
    void kaufen() {
        //todo
    }
    
    @Test
    void produziere() {
        //todo
    }
    
    @Test
    void marketingangebote() {
    	assertEquals("Fernsehwerbung", SpielService.getMarketingAngebote().get(0));
    }
    
    @Test
    void buchenmarketingmaßnahme() {
    	assertNotNull(SpielService.erstelleMarketingmaßnahme("Sponsoring", 9999, "Sponsoring1"));
    	assertNotNull(SpielService.erstelleMarketingmaßnahme("Fernsehwerbung", 9999, ""));
    	assertNotNull(SpielService.erstelleMarketingmaßnahme("MessenKampagne", 9999, ""));
    	assertNotNull(SpielService.erstelleMarketingmaßnahme("Plakatwerbung", 9999, ""));
    	assertNotNull(SpielService.erstelleMarketingmaßnahme("PRKampagne", 9999, ""));
    	assertNotNull(SpielService.erstelleMarketingmaßnahme("Radiowerbung", 9999, ""));
    	//assertNull(SpielService.erstelleMarketingmaßnahme("", 1111, ""));
    	
    }
    
    
    @Test
    void resultate() {
        //hier mal die rundenergebnisse für 1 spieler, für alle nach der simulation und das endergebnis

        assertNotNull(SpielService.getRundenResultat());
        assertNotNull(SpielService.getZwischenstand());
        assertNotNull(SpielService.getSpielende());
        
        //die sollten auf jeden fall auf richtigkeit überprüft werden, solange man nicht nur eine methode aufruft, 
        //dann sollte man einfach die methode testen und hier nur kurz ob das mit den parametern klappt
    }
    
    @Test
    void spielende() {

    	assertEquals("Test Unternehmen AG 107", SpielService.getSpielende().get(0).getName());
    	assertEquals("Test Unternehmen AG 108", SpielService.getSpielende().get(1).getName());
    	assertEquals("Test Unternehmen AG 109", SpielService.getSpielende().get(2).getName());
    }
    
    

}
