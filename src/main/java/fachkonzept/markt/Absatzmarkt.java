package fachkonzept.markt;

import java.util.ArrayList;
import java.util.List;

import fachkonzept.Angebot;
import fachkonzept.Produkt;
import fachkonzept.ProduktArt;

public class Absatzmarkt extends Markt {
	
	
	//hier soll der spieler angtebote einstellen und die simulation welche wahrnehmen
	
	public Absatzmarkt() {
	}
	
	@Override
	public void kaufen(Angebot a, int menge) {
		// TODO Auto-generated method stub
		super.kaufen(a, menge);

		//hier kauft nur die simulation, wenn die kauft muss es nirgens gespeichert werden fürn anfang
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
