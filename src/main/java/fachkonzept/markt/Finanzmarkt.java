package fachkonzept.markt;

import fachkonzept.Angebot;
import fachkonzept.Kredit;
import fachkonzept.Unternehmen;
import fachkonzept.Verbindlichkeit;

public class Finanzmarkt extends Markt {

    public Finanzmarkt() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public void kaufen(Angebot a, int menge, Unternehmen k) {
        
        super.kaufen(a, menge, k);
        
        k.verbindlichkeitHinzu(new Verbindlichkeit((Kredit)a.getMarkteinheit()));
       
    }

}
