package fachkonzept;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import service.SpielService;

class ServiceTest {

    @Test
    void init() {
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

}
