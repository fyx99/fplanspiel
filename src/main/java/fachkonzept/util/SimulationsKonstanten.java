package fachkonzept.util;

import java.util.ArrayList;
import java.util.List;

public class SimulationsKonstanten {
    public static double getProduktMarktpreis(ProduktArt produktArt) {
        double preis = 0;
        switch(produktArt) {
        case Holzstuhl:
            preis = 60;
            break;
        case Stoffstuhl:
            preis = 90;
            break;
        case Lederstuhl:
            preis = 135;
            break;
        case Holztisch:
            preis = 250;
            break;
        case Glastisch:
            preis = 539;
            break;
        case Kunststofftisch:
            preis = 64;
            break;
        case Holzschrank:
            preis = 440;
            break;
        case Edelstahlschrank:
            preis = 560;
            break;
        case Glasschrank:
            preis = 780;
            break;
        default:
            return -1;
        }
        return preis;
    }
    public static int getProduktMarktvolumen(ProduktArt produktArt) {
        int volumen = 0;
        switch(produktArt) {
        case Holzstuhl:
            volumen = 75;
            break;
        case Stoffstuhl:
            volumen = 42;
            break;
        case Lederstuhl:
            volumen = 37;
            break;
        case Holztisch:
            volumen = 37;
            break;
        case Glastisch:
            volumen = 36;
            break;
        case Kunststofftisch:
            volumen = 135;
            break;
        case Holzschrank:
            volumen = 48;
            break;
        case Edelstahlschrank:
            volumen = 50;
            break;
        case Glasschrank:
            volumen = 40;
            break;
        default:
            return -1;
        }
        return volumen;
    }

    public static final int MATERIAL_MARKT_MENGE = 100000;
    public static final int MASCHINEN_MARKT_MENGE = 100000;
    public static final int FINANZ_MARKT_MENGE = 100000;
    
    public static final double MASCHINEN_MARKT_PREISANPASSUNG = 0.1;	//prozentzahl pro dopplung
    public static final double MATERIAL_MARKT_PREISANPASSUNG = 0.1;	//prozentzahl pro dopplung

    public static final double VERTRIEBSMINUTEN_PRO_EURO = 3.18;	//Vertriebsmitarbeiter ist 3,18 Minuten beschäftigt pro Euro bei Angebot
    public static final double VERWALTUNGSMINUTEN_PRO_EURO_MARKETINGBUDGET = 0.2;	//Verwaltungsmitarbeiter ist 2 Minuten beschäftigt pro Euro bei Marketingmaßnahme
    public static final double VERWALTUNGSMINUTEN_PRO_EURO_KREDIT = 0.25;	//Verwaltungsmitarbeiter ist 2,5 Minuten beschäftigt pro Euro bei Kredit
     
    
    public static int getMaschinenPreise(MaschinenArt ma) {
        int a = 0;
        switch (ma) {
        case Holzstuhlmaschine:
            a = 7000;
            break;
        case Lederstuhlmaschine:
            a=8000;
            break;
        case Holzschrankmaschine:
            a=11000;
            break;
        case Glastischmaschine:
            a=11500;
            break;
        case Kunststofftischmaschine:
            a=8500;
            break;
        case Stoffstuhlmaschine:
            a=7500;
            break;
        case Holztischmaschine:
            a=9500;
            break;
        case Edelstahlschrankmaschine:
            a=12000;
            break;
        case Glasschrankmaschine:
            a=12500;
            break;
        default:
            break;
        }
        return a;
    }
    
    public static List<Double> getStandortFaktoren(StandortArt standort){
    	List<Double> list = new ArrayList<Double>();
    	
    		switch(standort) {
    		case A: 
    			list.add(0.8);	//MitarbeiterKostenFaktor
    			list.add(1.0);	//MaterialKostenFaktor
    			list.add(0.9);	//MarketingsEinflussFaktor
    			list.add(1.0);	//FertigungsKostenFaktor
    			list.add(1.03);	//Angebotsattraktivität
    			break;
    		case B:
    			list.add(1.0);
    			list.add(0.8);
    			list.add(1.1);
    			list.add(1.1);
    			list.add(1.0);	
    			break;
    		case C:
    			list.add(1.1);
    			list.add(1.0);
    			list.add(1.2);
    			list.add(1.1);
    			list.add(1.03);
    			break;
    		case D:
    			list.add(0.8);
    			list.add(1.1);
    			list.add(1.0);
    			list.add(0.8);
    			list.add(1.0);
    			break;
    		case NEUTRAL:
    			list.add(1.0);	
    			list.add(1.0);	
    			list.add(1.0);	
    			list.add(1.0);
    			list.add(1.0);
    			break;
    		}
    	return list;
    }


}
