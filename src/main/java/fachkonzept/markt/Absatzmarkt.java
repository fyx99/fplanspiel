package fachkonzept.markt;

import java.util.ArrayList;
import java.util.List;

import fachkonzept.Angebot;
import fachkonzept.Produkt;
import fachkonzept.util.ProduktArt;

public class Absatzmarkt extends Markt {
	
	
	//hier soll der spieler angtebote einstellen und die simulation welche wahrnehmen
	
	public Absatzmarkt() {
	}
	
    public List<Angebot> getAngeboteByProduktArt(ProduktArt pa){

        List<Angebot> produkt_angebote = new ArrayList<Angebot>();
        
        for (Angebot a : this.getAngebote()) {
            if(((Produkt)a.getMarkteinheit()).getProduktArt().equals(pa)) {
                produkt_angebote.add(a);
            }
        }
        return produkt_angebote;
    }
	

}
