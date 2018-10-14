package fachkonzept.markt;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import fachkonzept.Angebot;
import fachkonzept.Kredit;
import fachkonzept.Maschine;
import fachkonzept.Spiel;
import fachkonzept.Umsatz;
import fachkonzept.Unternehmen;
import fachkonzept.util.KreditArt;
import fachkonzept.util.MaschinenArt;

public class Maschinenmarkt extends Markt {

	public Maschinenmarkt() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void kaufen(Angebot a, int menge, Unternehmen k) {
		// TODO Auto-generated method stub
		super.kaufen(a, menge, k);
		
		k.maschineHinzu((Maschine)a.getMarkteinheit(), menge);
	}
	
	public List<String> getMaterialDetails(String materialName, Spiel s) {
		
		List<String> maschinen = new ArrayList<String>();
		String name="";
		Iterator<Angebot> angebote = s.getAktuellesUnternehmen().getMmarkt().getAngebote().iterator();
		
        while(angebote.hasNext()) {
            Angebot angebot = angebote.next();
            name = angebot.getMarkteinheit().getName();
            
            if(name.toLowerCase().contains(materialName.toLowerCase())) {
            	maschinen.add(name);
            }
        }
        return maschinen;
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
