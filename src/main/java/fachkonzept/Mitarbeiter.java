package fachkonzept;

import fachkonzept.markt.Markteinheit;
import fachkonzept.util.MitarbeiterFachgebiet;

public class Mitarbeiter extends Markteinheit {
    
    
    //frage wäre, welche attribute haben mitarbeiter noch?
    
    private String name;
    private double lohnkosten;
    private MitarbeiterFachgebiet mfg;
    private int arbeitszeit;
    
        
    public Mitarbeiter(String name, double lohn, int arbeitszeit, MitarbeiterFachgebiet mfg) {
        super();
        
        this.name= name;
        this.lohnkosten = lohn;
        this.mfg = mfg;
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
