package fachkonzept.markt;

import java.util.ArrayList;
import java.util.List;

import fachkonzept.Angebot;
import fachkonzept.Maschine;
import fachkonzept.Umsatz;
import fachkonzept.Unternehmen;
import fachkonzept.util.MaschinenArt;

public class Maschinenmarkt extends Markt {

	public Maschinenmarkt() {
		
	}

	@Override
	public void kaufen(Angebot a, int menge, Unternehmen k) {
		
		super.kaufen(a, menge, k);
		
		k.maschineHinzu((Maschine)a.getMarkteinheit(), menge);
	}
	


	public static List<Umsatz> umsatzProMaschinenArt(MaschinenArt a, int runde) {
		List<Umsatz> gefiltert = new ArrayList();
		for(Umsatz u : Beschaffungsmarkt.getUmsatzHistorie()) {
			if(u.getAngebot().getMarkteinheit() instanceof Maschine && ((Maschine)u.getAngebot().getMarkteinheit()).getMaschinenArt() == a && u.getRunde() == runde) {
				gefiltert.add(u);
			}
		}
		return gefiltert;

	}
	public static List<Umsatz> umsatzProMaschinenArt(MaschinenArt a) {
		List<Umsatz> gefiltert = new ArrayList();
		for(Umsatz u : Beschaffungsmarkt.getUmsatzHistorie()) {
			if(u.getAngebot().getMarkteinheit() instanceof Maschine && ((Maschine)u.getAngebot().getMarkteinheit()).getMaschinenArt() == a) {
				gefiltert.add(u);
			}
		}
		return gefiltert;

	}

	
	
	

}
