package fachkonzept;

import fachkonzept.markt.Markteinheit;

public class Mitarbeiter extends Markteinheit {
    
    
    //frage wäre, welche attribute haben mitarbeiter noch?
    
    private String name;
    private double lohnkosten;
    
        
    public Mitarbeiter(String name, double lohn) {
        super();
        
        this.name= name;
        this.lohnkosten = lohn;
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


    public void setLohnkosten(double lohnkosten) {
        this.lohnkosten = lohnkosten;
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
