package fachkonzept.markt;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import fachkonzept.Angebot;
import fachkonzept.Maschine;
import fachkonzept.Material;
import fachkonzept.Spiel;
import fachkonzept.Unternehmen;

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
	
	
	
	

}
