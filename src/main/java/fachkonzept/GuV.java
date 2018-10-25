package fachkonzept;

import java.util.ArrayList;
import java.util.List;

public class GuV {

    private List<Zahlung> einnahmen = new ArrayList();
    private List<Zahlung> ausgaben = new ArrayList();

    public List<Zahlung> getEinnahmen() {
        return einnahmen;
    }


    public List<Zahlung> getAusgaben() {
        return ausgaben;
    }

    public void neueAusgabe(Zahlung z) {
        this.ausgaben.add(z);
    }

    public void neueEinnahme(Zahlung z) {
        this.einnahmen.add(z);
    }

    public double rundenErgebnis(int runde) {
        double summe = 0;
        for(Zahlung z : einnahmen) {
            if(runde == z.getRunde())
                summe += z.getSumme();
        }
        for(Zahlung z : ausgaben) {
            if(runde == z.getRunde())
                summe -= z.getSumme();
        }
        return summe;
    }

    public double rundenErgebnis() {
        double summe = 0;
        for(Zahlung z : einnahmen) {
            summe += z.getSumme();
        }
        for(Zahlung z : ausgaben) {
            summe -= z.getSumme();
        }
        return summe;
    }

}
