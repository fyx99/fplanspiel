package fachkonzept.markt;

import fachkonzept.Angebot;
import fachkonzept.Arbeitskraft;
import fachkonzept.Mitarbeiter;
import fachkonzept.Unternehmen;

public class Arbeitsmarkt extends Markt {
    
    public Arbeitsmarkt() {
        
        
        
    }
    
    @Override
    public void kaufen(Angebot a, int menge, Unternehmen k) {

        super.kaufen(a, menge);
        k.arbeitskraftHinzu(new Arbeitskraft((Mitarbeiter)a.getMarkteinheit()));
        //muss den mitarbeiter noch dem unternehen zuordnen
    }

}
