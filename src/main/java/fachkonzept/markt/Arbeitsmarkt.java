package fachkonzept.markt;

import java.util.ArrayList;
import java.util.List;

import fachkonzept.Angebot;
import fachkonzept.Arbeitskraft;
import fachkonzept.Mitarbeiter;
import fachkonzept.Umsatz;
import fachkonzept.Unternehmen;
import fachkonzept.util.MitarbeiterFachgebiet;

public class Arbeitsmarkt extends Markt {
    
    public Arbeitsmarkt() {
        
                
    }
    
    @Override
    public void kaufen(Angebot a, int menge, Unternehmen k) {

        super.kaufen(a, menge, k);
        
        for (int i = 0; i< menge; i++) {
			k.arbeitskraftHinzu(new Arbeitskraft((Mitarbeiter)a.getMarkteinheit()));	//Mitarbeiter dem UN hinzufügen
		}
        
    }
    

	public static List<Umsatz> umsatzProMitarbeiterFachgebiet(MitarbeiterFachgebiet a) {
		List<Umsatz> gefiltert = new ArrayList();
		for(Umsatz u : Beschaffungsmarkt.getUmsatzHistorie()) {
			if(u.getAngebot().getMarkteinheit() instanceof Mitarbeiter && ((Mitarbeiter)u.getAngebot().getMarkteinheit()).getMfg() == a) {
				gefiltert.add(u);
			}
		}
		return gefiltert;

	}


}
