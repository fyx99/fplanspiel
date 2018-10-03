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
    }

}
