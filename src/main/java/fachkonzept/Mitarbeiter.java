package fachkonzept;

import fachkonzept.markt.Markteinheit;
import fachkonzept.util.MitarbeiterFachgebiet;
import fachkonzept.util.Werkzeuge;

public class Mitarbeiter extends Markteinheit {
    
    private String name;
    private double lohnkosten;
    private MitarbeiterFachgebiet mfg;
    private int arbeitszeit;	//in Minuten
    
        
    public Mitarbeiter(String name, double lohn, int arbeitszeit, MitarbeiterFachgebiet mfg) {
        super();
        
        this.name= name;
        this.lohnkosten = Werkzeuge.runde2Stellen(lohn);
        this.mfg = mfg;
        this.arbeitszeit = arbeitszeit;
    }
    
    public Mitarbeiter(Mitarbeiter m) {
        super();
        
        this.name= m.name;
        this.lohnkosten = m.lohnkosten;
        this.mfg = m.mfg;
        this.arbeitszeit = m.arbeitszeit;
    }
    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    
    
    public double getLohnkosten() {
        return lohnkosten;
    }


    public MitarbeiterFachgebiet getMfg() {
        return mfg;
    }


    public void setMfg(MitarbeiterFachgebiet mfg) {
        this.mfg = mfg;
    }


    public void setLohnkosten(double lohnkosten) {
        this.lohnkosten = lohnkosten;
    }

    
    public int getArbeitszeit() {
        return arbeitszeit;
    }


    public void setArbeitszeit(int arbeitszeit) {
        this.arbeitszeit = arbeitszeit;
    }


    public static Mitarbeiter findeMitarbeiter(String name) {
        for(Markteinheit m : Markteinheit.alleMarkteinheiten) {
            if(m instanceof Mitarbeiter && ((Mitarbeiter) m).getName() == name) {
                return (Mitarbeiter)m;
            }
        }
        return null;
        
    }

    

}
