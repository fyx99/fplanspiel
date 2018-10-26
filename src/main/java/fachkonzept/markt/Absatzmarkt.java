package fachkonzept.markt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fachkonzept.Angebot;
import fachkonzept.Produkt;
import fachkonzept.Umsatz;
import fachkonzept.Unternehmen;
import fachkonzept.util.ProduktArt;
import fachkonzept.util.ProduktTyp;

public class Absatzmarkt extends Markt {
	
	private Map<ProduktArt, Integer> produktVolumen = new HashMap<ProduktArt, Integer>();
    public List<Angebot> getAngeboteByProduktArt(ProduktArt pa){

        List<Angebot> produkt_angebote = new ArrayList<Angebot>();
        
        for (Angebot a : this.getAngebote()) {
            if(((Produkt)a.getMarkteinheit()).getProduktArt().equals(pa)) {
                produkt_angebote.add(a);
            }
        }
        return produkt_angebote;
    }
    
    public static List<Umsatz> getUmsaetzeByProduktArt(ProduktArt pa, int runde){		//alle Umsätze ds Absatzmarkes holen

        List<Umsatz> ums = new ArrayList<Umsatz>();
        
        for (Umsatz um : getUmsatzHistorie()) {
            if(um.getAngebot().getMarkteinheit() instanceof Produkt && ((Produkt)um.getAngebot().getMarkteinheit()).getProduktArt().equals(pa) && um.getRunde() == runde) {
            	ums.add(um);
            }
        }
        return ums;
    }
    
    
    public static List<Umsatz> getUmsaetzeByProduktTyp(ProduktTyp pt){		//alle Umsätze des Absatzmarkes holen

        List<Umsatz> ums = new ArrayList<Umsatz>();
        
        for (Umsatz um : getUmsatzHistorie()) {
            if(um.getAngebot().getMarkteinheit() instanceof Produkt && ((Produkt)um.getAngebot().getMarkteinheit()).getProduktTyp().equals(pt)) {
            	ums.add(um);
            }
        }
        return ums;
    }
    
    public static int getMarktanteilByProduktTypAndUnternehmen(Unternehmen un, ProduktTyp pt) {
		double gesamtUmsatz =0;
		
		double unUmsatz = 0;
		
		for(Umsatz u : getUmsaetzeByProduktTyp(pt)) {
			gesamtUmsatz += u.getMenge() * u.getAngebot().getPreis();	//Gesamtumsätze des Absatzmarktes
			if(u.getVerkaeufer().equals(un)) {
				unUmsatz += u.getMenge() * u.getAngebot().getPreis();	//Gesamumsätze des einzelnen UN
			}
		}
		return (int)(unUmsatz / gesamtUmsatz *100);		//in % zurückgeben

	}
    
    public static Map<ProduktTyp, Integer> getMarktanteile (Unternehmen un){
    	Map<ProduktTyp, Integer> marktAnteile = new HashMap<ProduktTyp, Integer>();
    	for(ProduktTyp pt: ProduktTyp.values()) {
    		marktAnteile.put(pt, getMarktanteilByProduktTypAndUnternehmen(un, pt));
    	}
    	return marktAnteile;
    }

	public Map<ProduktArt, Integer> getProduktVolumen() {
		return produktVolumen;
	}

	public void setProduktVolumen(Map<ProduktArt, Integer> produktVolumen) {
		this.produktVolumen = produktVolumen;
	}
    
    
    
    
	

}
