package fachkonzept;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fachkonzept.marketing.Fernsehwerbung;
import fachkonzept.marketing.MessenKampagne;
import fachkonzept.marketing.PRKampagne;
import fachkonzept.markt.Absatzmarkt;
import fachkonzept.markt.Maschinenmarkt;
import fachkonzept.util.KreditArt;
import fachkonzept.util.MaschinenArt;
import fachkonzept.util.MitarbeiterFachgebiet;
import fachkonzept.util.ProduktArt;
import fachkonzept.util.ProduktTyp;
import fachkonzept.util.SimulationsKonstanten;
import fachkonzept.util.StandortArt;

class SimulationTest {
    Spiel s;
    Unternehmen u1;
    Unternehmen u2;
    Unternehmen u3;
    Unternehmen u4;
    @BeforeEach
    void setUp() {
        
       s= new Spiel();
       s.unternehmenHinzufuegen(u1 = new Unternehmen("tst", s, new Standort(SimulationsKonstanten.getStandortFaktoren(StandortArt.NEUTRAL))));
       s.unternehmenHinzufuegen(u2 = new Unternehmen("ts2t", s, new Standort(SimulationsKonstanten.getStandortFaktoren(StandortArt.NEUTRAL))));
       s.unternehmenHinzufuegen(u3 = new Unternehmen("t3st", s, new Standort(SimulationsKonstanten.getStandortFaktoren(StandortArt.NEUTRAL))));
       s.unternehmenHinzufuegen(u4 = new Unternehmen("t3sst", s, new Standort(SimulationsKonstanten.getStandortFaktoren(StandortArt.NEUTRAL))));
       s.rundenStart();
       
    }
    
    void setUpAbsatzmarkt() {
        // 3 Spieler -> glastisch 36*3, holzstuhl 75*3, lederstuhl 37*3, edelstahlschrank 50*3
        Absatzmarkt testMarkt1 = u1.getVmarkt();
        testMarkt1.anbieten(new Angebot(new Produkt(ProduktArt.Glastisch, ProduktTyp.Tisch), 30, 12), u1);
        testMarkt1.anbieten(new Angebot(new Produkt(ProduktArt.Glastisch, ProduktTyp.Tisch), 30, 15), u1);
        testMarkt1.anbieten(new Angebot(new Produkt(ProduktArt.Holzstuhl, ProduktTyp.Stuhl), 450, 5), u1);
        Absatzmarkt testMarkt2 = u2.getVmarkt();
        testMarkt2.anbieten(new Angebot(new Produkt(ProduktArt.Edelstahlschrank, ProduktTyp.Schrank), 50, 500), u2);
        testMarkt2.anbieten(new Angebot(new Produkt(ProduktArt.Glastisch, ProduktTyp.Tisch), 30, 15), u2);   
        testMarkt2.anbieten(new Angebot(new Produkt(ProduktArt.Holzstuhl, ProduktTyp.Stuhl), 300, 5), u2);
        Absatzmarkt testMarkt3 = u3.getVmarkt();
        testMarkt3.anbieten(new Angebot(new Produkt(ProduktArt.Glastisch, ProduktTyp.Tisch), 28, 12), u3);
        testMarkt3.anbieten(new Angebot(new Produkt(ProduktArt.Lederstuhl, ProduktTyp.Stuhl), 40, 50.99), u3);  
        testMarkt3.anbieten(new Angebot(new Produkt(ProduktArt.Edelstahlschrank, ProduktTyp.Schrank), 50, 600), u3); 
    }
    
    @Test
    void kreditTilgung() {
        Verbindlichkeit v1 = new Verbindlichkeit(new Kredit(50000,0.05,7, KreditArt.Mehr_Cash), 40000.0, 2);
        u1.verbindlichkeitHinzu(v1);
        u1.setKapital(1000000);
        assertEquals(0, u1.getGuv().rundenErgebnis());
        Simulation.simuliere(s);
        assertEquals(1, u1.getGuv().getAusgaben().size());
        assertEquals(32000, u1.getVerbindlichkeiten().get(0).getVerbleibendeSumme());
        assertEquals(Integer.valueOf(3), u1.getVerbindlichkeiten().get(0).getAktuelleLaufzeit());
        assertEquals(10000, u1.getGuv().getAusgaben().get(0).getSumme());
        assertEquals("Kreditkosten", u1.getGuv().getAusgaben().get(0).getBeschreibung());
        assertEquals(990000, u1.getKapital());
        assertEquals(-10000, u1.getGuv().rundenErgebnis());

        Simulation.simuliere(s);
        assertEquals(24000, u1.getVerbindlichkeiten().get(0).getVerbleibendeSumme());
        assertEquals(Integer.valueOf(4), u1.getVerbindlichkeiten().get(0).getAktuelleLaufzeit());
        assertEquals(9600, u1.getGuv().getAusgaben().get(1).getSumme());
        assertEquals(980400, u1.getKapital());
        assertEquals(-19600, u1.getGuv().rundenErgebnis());

        Simulation.simuliere(s);
        assertEquals(16000, u1.getVerbindlichkeiten().get(0).getVerbleibendeSumme());
        assertEquals(Integer.valueOf(5), u1.getVerbindlichkeiten().get(0).getAktuelleLaufzeit());
        assertEquals(9200, u1.getGuv().getAusgaben().get(2).getSumme());
        assertEquals(971200, u1.getKapital());
        assertEquals(-28800, u1.getGuv().rundenErgebnis());

        Simulation.simuliere(s);
        assertEquals(8000, u1.getVerbindlichkeiten().get(0).getVerbleibendeSumme());
        assertEquals(Integer.valueOf(6), u1.getVerbindlichkeiten().get(0).getAktuelleLaufzeit());
        assertEquals(8800, u1.getGuv().getAusgaben().get(3).getSumme());
        assertEquals(-37600, u1.getGuv().rundenErgebnis());

        Simulation.simuliere(s);
        assertEquals(8400, u1.getGuv().getAusgaben().get(4).getSumme());
        assertEquals(-46000, u1.getGuv().rundenErgebnis());
        //jetzt ist der eigentlich getilgt
        assertEquals(0, u1.getVerbindlichkeiten().size());

        Simulation.simuliere(s);

        assertEquals(-46000, u1.getGuv().rundenErgebnis());
        
    }
    
    @Test
    void lohnzahlungen() {
        u1.setKapital(500000);
        u1.arbeitskraftHinzu(new Arbeitskraft(1000, new Mitarbeiter("Name1", 7000, 40000, MitarbeiterFachgebiet.MASCHINE)));

        Simulation.simuliere(s);
        assertEquals(493000, u1.getKapital());
        assertEquals(7000, u1.getGuv().getAusgaben().get(0).getSumme());
        assertEquals("Lohnkosten", u1.getGuv().getAusgaben().get(0).getBeschreibung());
        

        u1.arbeitskraftHinzu(new Arbeitskraft(1000, new Mitarbeiter("Name21",78820, 40000, MitarbeiterFachgebiet.MASCHINE)));
        u1.arbeitskraftHinzu(new Arbeitskraft(1000, new Mitarbeiter("Name13", 15478, 40000, MitarbeiterFachgebiet.MASCHINE)));
        u1.arbeitskraftHinzu(new Arbeitskraft(1000, new Mitarbeiter("Name14", 777.77, 40000, MitarbeiterFachgebiet.MASCHINE)));

        Simulation.simuliere(s);
        assertEquals(493000 - 78820 - 15478 - 777.77 - 7000, u1.getKapital());
        assertEquals(5, u1.getGuv().getAusgaben().size());
    }
    

    
    @Test
    void simuliereAbsatzmarkt() {
        Simulation.simuliereSpielstart(s);
        setUpAbsatzmarkt();

        Simulation.simuliere(s);
        //assertEquals(Integer.valueOf(36), u1.getVmarkt().getProduktVolumen().get(ProduktArt.Glastisch));
        /*assertEquals(539, SimulationsKonstanten.getProduktMarktpreis(ProduktArt.Glastisch));
        assertEquals(60, SimulationsKonstanten.getProduktMarktpreis(ProduktArt.Holzstuhl));
        assertEquals(Integer.valueOf(75), u1.getVmarkt().getProduktVolumen().get(ProduktArt.Holzstuhl));
        assertEquals(560, SimulationsKonstanten.getProduktMarktpreis(ProduktArt.Edelstahlschrank));
        assertEquals(Integer.valueOf(50), u1.getVmarkt().getProduktVolumen().get(ProduktArt.Edelstahlschrank));
        assertEquals(135, SimulationsKonstanten.getProduktMarktpreis(ProduktArt.Lederstuhl));
        assertEquals(Integer.valueOf(37), u1.getVmarkt().getProduktVolumen().get(ProduktArt.Lederstuhl));
        
        //jetzt wirds ernst :D --> Glastisch
        
/*
        assertEquals(1, u1.getVmarkt().getAngeboteByProduktArt(ProduktArt.Glastisch).size());
        assertEquals(1, u2.getVmarkt().getAngeboteByProduktArt(ProduktArt.Glastisch).size());
        assertEquals(0, u3.getVmarkt().getAngeboteByProduktArt(ProduktArt.Glastisch).size());
        //andere angebots mengen
        assertEquals(1, u1.getVmarkt().getAngeboteByProduktArt(ProduktArt.Holzstuhl).size());
        assertEquals(1, u2.getVmarkt().getAngeboteByProduktArt(ProduktArt.Holzstuhl).size());
        assertEquals(0, u2.getVmarkt().getAngeboteByProduktArt(ProduktArt.Edelstahlschrank).size());
        assertEquals(1, u3.getVmarkt().getAngeboteByProduktArt(ProduktArt.Edelstahlschrank).size());    //beispiel für kappung
        assertEquals(0, u2.getVmarkt().getAngeboteByProduktArt(ProduktArt.Lederstuhl).size());
        
        //die restbestände
        assertEquals(5, u1.getVmarkt().getAngeboteByProduktArt(ProduktArt.Glastisch).get(0).getMenge());
        assertEquals(5, u2.getVmarkt().getAngeboteByProduktArt(ProduktArt.Glastisch).get(0).getMenge());      
        assertEquals(50, u3.getVmarkt().getAngeboteByProduktArt(ProduktArt.Edelstahlschrank).get(0).getMenge());
        */
        //die umsätze
/*
        assertEquals(1295, u1.getGuv().rundenErgebnis());
        assertEquals(25935, u2.getGuv().rundenErgebnis());
        assertEquals(2375.6, u3.getGuv().rundenErgebnis(), 0.001);
        */
    }   

    @Test
    void simuliereMarketingmix() {
    	u4.setKapital(55555);
    	u4.getMarketingmix().marketingBuchen(new PRKampagne("Fußfdgdfgdf", 43000), u4);	//kapp 25
    	u4.getMarketingmix().marketingBuchen(new Fernsehwerbung("werbnungg", 900),  u4);	//3
    	u4.getMarketingmix().marketingBuchen(new MessenKampagne("werbsaddnungg", 1000),  u4);
    	u4.getMarketingmix().marketingBuchen(new MessenKampagne("Fußball Tesdfdsfsdfnsoring", 3500),  u4);	//kapp 12

    	Simulation.simuliere(s);
    	assertEquals(55555-48400, u4.getKapital());
    	assertEquals(40, u4.getMarketingmix().getMarketingStaerke());
    	Simulation.simuliere(s);
    	assertEquals(0, u4.getMarketingmix().getMarketingStaerke());
    	assertEquals(0, u2.getMarketingmix().getMarketingStaerke());
    }
   
    @Test
    void simuliereMaschinenMarkt() {
    	Simulation.simuliereSpielstart(s);
    	
		int gesamt = 0;
		for (MaschinenArt a : MaschinenArt.values()) {
			// wv pro produkt verkauft wurde
			gesamt += Maschinenmarkt.umsatzProMaschinenArt(a, s.getRunde()).stream().mapToInt(u -> u.getMenge()).sum();
		}
		assertEquals(10, gesamt);
		
		double schnittMenge = gesamt / MaschinenArt.values().length;
		assertEquals(1.0, schnittMenge);
		
    	Angebot a1 = u1.getMmarkt().getAngebote().get(0);
    	u1.getMmarkt().kaufen(a1, 10, u1);
    	u1.getMmarkt().kaufen(u1.getMmarkt().getAngebote().get(1), 5, u1);
    	u1.getMmarkt().kaufen(u1.getMmarkt().getAngebote().get(2), 15, u1);
    	u1.getMmarkt().kaufen(u1.getMmarkt().getAngebote().get(3), 60, u1);
    	//9 maschinen

    	assertEquals(7000, u1.getMmarkt().getAngebote().get(0).getPreis());
    	assertEquals(7500, u1.getMmarkt().getAngebote().get(1).getPreis());
    	assertEquals(8000, u1.getMmarkt().getAngebote().get(2).getPreis());
    	assertEquals(9500, u1.getMmarkt().getAngebote().get(3).getPreis());
    	assertEquals(11500, u1.getMmarkt().getAngebote().get(4).getPreis());

    	s.zugBeendet();
    	s.zugBeendet();
    	s.zugBeendet();
    	s.zugBeendet();
    	
		for (MaschinenArt a : MaschinenArt.values()) {
			// wv pro produkt verkauft wurde
			gesamt += Maschinenmarkt.umsatzProMaschinenArt(a, s.getRunde() -1 ).stream().mapToInt(u -> u.getMenge()).sum();
		}
		assertEquals(110, gesamt);
		schnittMenge = gesamt / MaschinenArt.values().length;

		assertEquals(12, schnittMenge);
		
    	assertEquals(7572.73, u1.getMmarkt().getAngebote().get(0).getPreis());	//bei durchschnittsmenge 0%
    	assertEquals(7090.91, u1.getMmarkt().getAngebote().get(1).getPreis());
    	assertEquals(8290.91, u1.getMmarkt().getAngebote().get(2).getPreis());
    	assertEquals(10350, u1.getMmarkt().getAngebote().get(4).getPreis());
    	assertEquals(SimulationsKonstanten.MASCHINEN_MARKT_MENGE, u1.getMmarkt().getAngebote().get(0).getMenge());
    	
    	//jetzt den spaß nochmal
    	
    	s.zugBeendet();
    	s.zugBeendet();
    	s.zugBeendet();
    	s.zugBeendet();
    	
    	assertEquals(7572.73, u1.getMmarkt().getAngebote().get(0).getPreis());	
    	assertEquals(7090.91, u1.getMmarkt().getAngebote().get(1).getPreis());
    	assertEquals(8290.91, u1.getMmarkt().getAngebote().get(2).getPreis());
    	assertEquals(10350, u1.getMmarkt().getAngebote().get(4).getPreis());
    	

    	u1.getMmarkt().kaufen(u1.getMmarkt().getAngebote().get(2), 9, u1);
    	
    	s.zugBeendet();
    	s.zugBeendet();
    	s.zugBeendet();
    	s.zugBeendet();
    	
    	assertEquals(6815.46, u1.getMmarkt().getAngebote().get(0).getPreis());	
    	assertEquals(6381.82, u1.getMmarkt().getAngebote().get(1).getPreis());
    	assertEquals(14923.64, u1.getMmarkt().getAngebote().get(2).getPreis());
    	
    }
    
    @Test
    void simuliereBeschaffungsMarkt() {
    	Simulation.simuliereSpielstart(s);

    	Angebot a1 = u1.getBmarkt().getAngebote().get(0);
    	u1.getBmarkt().kaufen(a1, 10, u1);
    	u1.getBmarkt().kaufen(u1.getBmarkt().getAngebote().get(1), 5, u1);
    	u1.getBmarkt().kaufen(u1.getBmarkt().getAngebote().get(2), 15, u1);
    	u1.getBmarkt().kaufen(u1.getBmarkt().getAngebote().get(3), 30, u1);
    	//6 materialien

    	assertEquals(3, u1.getBmarkt().getAngebote().get(0).getPreis());
    	assertEquals(3.5, u1.getBmarkt().getAngebote().get(1).getPreis());
    	assertEquals(9, u1.getBmarkt().getAngebote().get(2).getPreis());
    	assertEquals(14, u1.getBmarkt().getAngebote().get(3).getPreis());
    	assertEquals(1, u1.getBmarkt().getAngebote().get(4).getPreis());
    	
    	s.zugBeendet();
    	s.zugBeendet();
    	s.zugBeendet();
    	s.zugBeendet();

    	assertEquals(3.15, u1.getBmarkt().getAngebote().get(0).getPreis());	//bei durchschnittsmenge 0%
    	assertEquals(3.33, u1.getBmarkt().getAngebote().get(1).getPreis());
    	assertEquals(9.45, u1.getBmarkt().getAngebote().get(2).getPreis());
    	assertEquals(0.9, u1.getBmarkt().getAngebote().get(4).getPreis());
    	assertEquals(SimulationsKonstanten.MASCHINEN_MARKT_MENGE, u1.getBmarkt().getAngebote().get(0).getMenge());
    }
    
    @Test
    void simmuliereArbeitsmarkt() {
    	Simulation.simuliereSpielstart(s);
    	
    	assertEquals(6, u1.getAmarkt().getAngebote().size());
    	
    	Angebot a1 = u1.getAmarkt().getAngebote().get(0);
    	//weitere Tests...
    }
    
    @Test
    void simuliereSzenario() {
    	Spiel spiel = new Spiel();
    	spiel.setRundenAnzahl(8);
    	spiel.setSzenario(1);
    	spiel.unternehmenHinzufuegen(u1 = new Unternehmen("tst", s, new Standort(SimulationsKonstanten.getStandortFaktoren(StandortArt.NEUTRAL))));
    	spiel.unternehmenHinzufuegen(u2 = new Unternehmen("tst2", s, new Standort(SimulationsKonstanten.getStandortFaktoren(StandortArt.NEUTRAL))));
    	spiel.rundenStart();
    	spiel.zugBeendet();
    	
    	assertEquals(3, spiel.getAktuellesUnternehmen().getMaschinen().size());	//3 Maschinen
    	assertEquals("Holzstuhlmaschine", spiel.getAktuellesUnternehmen().getMaschinen().get(0).getName());
    	assertEquals("Kunststofftischmaschine", spiel.getAktuellesUnternehmen().getMaschinen().get(1).getName());
    	assertEquals("Glasschrankmaschine", spiel.getAktuellesUnternehmen().getMaschinen().get(2).getName());
    	
    	assertEquals(2, spiel.getAktuellesUnternehmen().getMitarbeiter().size());
    	assertEquals(MitarbeiterFachgebiet.MASCHINE, spiel.getAktuellesUnternehmen().getMitarbeiter().get(0).getM().getMfg());	//1x Mitarbeiter Fertigung
    	assertEquals(MitarbeiterFachgebiet.VERTRIEB, spiel.getAktuellesUnternehmen().getMitarbeiter().get(1).getM().getMfg()); //1x Mitarbeiter Vertrieb
    
    	assertEquals(Integer.valueOf(1500), spiel.getAktuellesUnternehmen().getMaterialien().get("Holz"));
    	assertEquals(Integer.valueOf(350), spiel.getAktuellesUnternehmen().getMaterialien().get("Glas"));
    	assertEquals(Integer.valueOf(1000), spiel.getAktuellesUnternehmen().getMaterialien().get("Kunststoff"));
    	assertEquals(Integer.valueOf(150), spiel.getAktuellesUnternehmen().getMaterialien().get("Stoff"));
    	assertEquals(Integer.valueOf(100), spiel.getAktuellesUnternehmen().getMaterialien().get("Edelstahl"));
    	
    	
    	assertNotNull(spiel.getAktuellesUnternehmen().getVerbindlichkeiten().get(0));
    	assertEquals(105000, spiel.getAktuellesUnternehmen().getKapital());
    	
    	
    }

    

   
    
    
    
    
    
    
    
    
    
    
    
    
    
    

}
