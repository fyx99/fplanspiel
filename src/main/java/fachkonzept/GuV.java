package fachkonzept;

import java.util.ArrayList;
import java.util.List;

public class GuV {

    private List<Zahlung> einnahmen = new ArrayList();
    private List<Zahlung> ausgaben = new ArrayList();
    public List<Zahlung> getEinnahmen() {
        return einnahmen;
    }
    public void setEinnahmen(List<Zahlung> einnahmen) {
        this.einnahmen = einnahmen;
    }
    public List<Zahlung> getAusgaben() {
        return ausgaben;
    }
    public void setAusgaben(List<Zahlung> ausgaben) {
        this.ausgaben = ausgaben;
    }
    
    public void neueAusgabe(Zahlung z) {
        this.ausgaben.add(z);
    }
    public void neueEinnahme(Zahlung z) {
        this.einnahmen.add(z);
    }

    
    

}
