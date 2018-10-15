package fachkonzept.markt;

import java.util.ArrayList;
import java.util.List;

import fachkonzept.Angebot;
import fachkonzept.Kredit;
import fachkonzept.Umsatz;
import fachkonzept.Unternehmen;
import fachkonzept.Verbindlichkeit;
import fachkonzept.util.KreditArt;

public class Finanzmarkt extends Markt {

    public Finanzmarkt() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public void kaufen(Angebot a, int menge, Unternehmen k) {
        
        super.kaufen(a, menge, k);
        
        k.verbindlichkeitHinzu(new Verbindlichkeit((Kredit)a.getMarkteinheit()));
       
    }

	public static List<Umsatz> umsatzProKreditArt(KreditArt a) {
		List<Umsatz> gefiltert = new ArrayList();
		for(Umsatz u : Beschaffungsmarkt.getUmsatzHistorie()) {
			if(u.getAngebot().getMarkteinheit() instanceof Kredit && ((Kredit)u.getAngebot().getMarkteinheit()).getKreditArt() == a) {
				gefiltert.add(u);
			}
		}
		return gefiltert;

	}


}
