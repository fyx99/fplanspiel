package fachkonzept.markt;

import fachkonzept.Angebot;
import fachkonzept.Unternehmen;

public class Arbeitsmarkt extends Markt {
    
    public Arbeitsmarkt() {
        
        
        
    }
    
    @Override
    public void kaufen(Angebot a, int menge, Unternehmen k) {

        super.kaufen(a, menge);
        
        //muss den mitarbeiter noch dem unternehen zuordnen
    }

}
