package service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fachkonzept.Maschine;
import fachkonzept.Material;
import fachkonzept.Produkt;
import fachkonzept.Produktionsmatrix;
import fachkonzept.util.MaschinenArt;
import fachkonzept.util.MaterialArt;
import fachkonzept.util.ProduktArt;
import fachkonzept.util.ProduktTyp;

class ServiceTest {
    
    @BeforeEach
    void beforeEaching() {
        SpielService.neuesSpiel();
        SpielService.neuesUnternehmen("Test Unternehmen AG 107", "A");
        SpielService.neuesUnternehmen("Test Unternehmen AG 108", "B");
        SpielService.neuesUnternehmen("Test Unternehmen AG 109", "C");
        SpielService.spielStart(3, 0);
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
        assertEquals(0.8, SpielService.getSpiel().getUnternehmen().get(0).getStandort().getMitarbeiterKosten());

        SpielService.neuesUnternehmen("Test Unternehmen AG 88", "B");
        assertEquals(0.8, SpielService.getSpiel().getUnternehmen().get(1).getStandort().getMaterialKosten());
        SpielService.neuesUnternehmen("Test Unternehmen AG 89", "C");
        assertEquals(1.2, SpielService.getSpiel().getUnternehmen().get(2).getStandort().getMarketingEinfluss());
        SpielService.neuesUnternehmen("Test Unternehmen AG 90", "D");
        assertEquals(0.8, SpielService.getSpiel().getUnternehmen().get(3).getStandort().getFertigungsKosten());
        
        assertEquals(4, SpielService.getSpiel().getUnternehmen().size());
        assertNull(SpielService.getSpiel().getAktuellesUnternehmen());
        
        SpielService.spielStart(2, 1);
        
        assertEquals(1, SpielService.getSpiel().getSzenario());

        assertEquals(1, SpielService.getSpiel().getRunde());
        assertEquals(2, SpielService.getSpiel().getRundenAnzahl());
        assertEquals("Test Unternehmen AG 87", SpielService.getSpiel().getAktuellesUnternehmen().getName());
        assertEquals(6, SpielService.getSpiel().getAktuellesUnternehmen().getBmarkt().getAngebote().size());

        assertEquals(Integer.valueOf(0), SpielService.zugBeendet());
        assertEquals("Test Unternehmen AG 88", SpielService.getSpiel().getAktuellesUnternehmen().getName());
        
        assertEquals(Integer.valueOf(0), SpielService.zugBeendet());
        assertEquals("Test Unternehmen AG 89", SpielService.getSpiel().getAktuellesUnternehmen().getName());
        
        assertEquals(Integer.valueOf(0), SpielService.zugBeendet());
        assertEquals("Test Unternehmen AG 90", SpielService.getSpiel().getAktuellesUnternehmen().getName());
        
        assertEquals(Integer.valueOf(2), SpielService.zugBeendet());
        assertEquals("Test Unternehmen AG 87", SpielService.getSpiel().getAktuellesUnternehmen().getName());
        
        assertEquals(Integer.valueOf(0), SpielService.zugBeendet());
        assertEquals("Test Unternehmen AG 88", SpielService.getSpiel().getAktuellesUnternehmen().getName());
        
        assertEquals(Integer.valueOf(0), SpielService.zugBeendet());
        assertEquals("Test Unternehmen AG 89", SpielService.getSpiel().getAktuellesUnternehmen().getName());
        
        assertEquals(Integer.valueOf(0), SpielService.zugBeendet());
        assertEquals("Test Unternehmen AG 90", SpielService.getSpiel().getAktuellesUnternehmen().getName());

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
    	Maschine m = new Maschine(MaschinenArt.Holzstuhlmaschine, 5000, new Produkt(ProduktArt.Holzstuhl, ProduktTyp.Stuhl), new Produktionsmatrix(null), 5.0, 500);
    	int anzahlAngeboteMMarkt = SpielService.getUnternehmen().getMmarkt().getAngebote().size();
        SpielService.angebotErstellen(50, m.getId(), 99);
        assertEquals(anzahlAngeboteMMarkt+1, SpielService.getUnternehmen().getMmarkt().getAngebote().size());
        
        
        
        Material material = new Material(MaterialArt.Holz);
        
    	int alteMenge = SpielService.getUnternehmen().getBmarkt().getAngebote().get(0).getMenge();
    	
    	assertEquals(6, SpielService.getBMarkt().getAngebote().size());
    	
        SpielService.angebotErstellen(50, material.getId(), 3);
        
        //HIER Wird kein neues Material-Angebot erstellt, sondern die Menge des neues Angebots einfach auf das alte addiert
        assertEquals(6, SpielService.getBMarkt().getAngebote().size());
        
        assertEquals(Integer.valueOf(alteMenge + 50), SpielService.getUnternehmen().getBmarkt().getAngebote().get(0).getMenge());
        

        Produkt p = new Produkt(ProduktArt.Holzstuhl, ProduktTyp.Stuhl);

    	int anzahlAngeboteVMarkt = SpielService.getVMarkt().getAngebote().size();
        SpielService.angebotErstellen(50, p.getId(), 99);
        assertEquals(anzahlAngeboteVMarkt+1, SpielService.getVMarkt().getAngebote().size());
        
    }
    
    @Test
    void angebotEntfernen() {
    	//zuerst was auf V-Markt anbieten:
    	assertEquals(0, SpielService.getUnternehmen().getVmarkt().getAngebote().size());
    	
    	Produkt p = new Produkt(ProduktArt.Holzstuhl, ProduktTyp.Stuhl);
    	SpielService.angebotErstellen(50, p.getId(), 50);
    	assertEquals(1, SpielService.getUnternehmen().getVmarkt().getAngebote().size());
    	
        //nun Angebot aus VMarktentfernen
    	int angebotsID = SpielService.getUnternehmen().getVmarkt().getAngebote().get(0).getId();
    	int anzahlAngebote = SpielService.getVMarkt().getAngebote().size();
    	
    	SpielService.angebotEntfernen(angebotsID);
    	assertEquals(anzahlAngebote-1, SpielService.getVMarkt().getAngebote().size());
    }
    
    @Test
    void kaufen() {
        int mitarbeiterId = SpielService.getAMarkt().getAngebote().get(0).getId();
 
        assertEquals(false, SpielService.kaufeAngebot(100, -1));
        assertEquals(true, SpielService.kaufeAngebot(5, mitarbeiterId));
        assertEquals(true, SpielService.kaufeAngebot(50000, mitarbeiterId));
        
        int materialId = SpielService.getBMarkt().getAngebote().get(0).getId();
        assertEquals(true, SpielService.kaufeAngebot(5, materialId));
        
        int kreditId = SpielService.getFMarkt().getAngebote().get(0).getId();
        assertEquals(true, SpielService.kaufeAngebot(5, kreditId));
        
        int maschinenId = SpielService.getMMarkt().getAngebote().get(0).getId();
        assertEquals(true, SpielService.kaufeAngebot(5, maschinenId));
    }
    
    @Test
    void produziere() {
    	
    	int maschinenId = SpielService.getMMarkt().getAngebote().get(0).getId();
    	assertEquals(true, SpielService.kaufeAngebot(5, maschinenId));
        int maschinenIdInventar = SpielService.getUnternehmen().getMaschinen().getMaschinen().get(0).getId();
        SpielService.produziere(100, maschinenIdInventar);
        assertEquals(100, SpielService.getUnternehmen().getProdukte().get(0).getMenge());
        
        
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
    
   @Test 
   void doppelterUnternehmensname() {
	   assertEquals("1", SpielService.neuesUnternehmen("1", "A"));
	   assertEquals("Doppelter Name", SpielService.neuesUnternehmen("1", "A"));
	   
   }
   
   @Test
   void spielDTO() {
	   assertEquals(1, SpielService.getSpielDTO().getRunde());
   }
   
   @Test
   void mitarbeiterKündigen() {
	   int anzMitarbeiter = SpielService.getSpiel().getUnternehmen().get(0).getMitarbeiter().size();
	   assertEquals(0, anzMitarbeiter);
	   int idMitarbeiterAngebot = SpielService.getSpiel().getUnternehmen().get(0).getAmarkt().getAngebote().get(0).getId();
	   SpielService.kaufeAngebot(5, idMitarbeiterAngebot);
	   int idMitarbeiter = SpielService.getUnternehmen().getMitarbeiter().getArbeiter().get(0).getM().getId();
	   
	   String nameMitarbeiter2 = SpielService.getSpiel().getUnternehmen().get(0).getMitarbeiter().get(1).getM().getName();
	   
	   assertEquals(anzMitarbeiter+5, SpielService.getSpiel().getUnternehmen().get(0).getMitarbeiter().size());
	   
	   SpielService.mitarbeiterEntfernen(idMitarbeiter);
	   assertEquals(anzMitarbeiter+4, SpielService.getSpiel().getUnternehmen().get(0).getMitarbeiter().size());
	   
	   //wurde der damalige 2. Mitarbeiter nun 1. Mitarbeiter?
	   assertEquals(nameMitarbeiter2, SpielService.getSpiel().getUnternehmen().get(0).getMitarbeiter().get(0).getM().getName());
   }
    
    

}
