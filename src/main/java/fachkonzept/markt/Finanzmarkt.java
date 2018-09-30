package fachkonzept.markt;

import fachkonzept.Angebot;
import fachkonzept.Unternehmen;

public class Finanzmarkt extends Markt {

    public Finanzmarkt() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public void kaufen(Angebot a, int menge, Unternehmen k) {
        // TODO Auto-generated method stub
        super.kaufen(a, menge, k);
        
        //hier muss man handeln was bei kreditaufnahme passiert
       
    }

}
