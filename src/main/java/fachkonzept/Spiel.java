package fachkonzept;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Spiel {

    private int runde = 0;
    private int rundenAnzahl = 10;
    Unternehmen aktuellesUnternehmen = null;
    List<Unternehmen> unternehmen = new ArrayList<Unternehmen>();
    private Stack<Unternehmen> naechsteUnternehmen;

    public Spiel() {

    }

    public Spiel(int rundenAnzahl) {
        this.rundenAnzahl = rundenAnzahl;
    }

    public String rundenStart() {
        
        if(runde == 0) {
            //dann anfangs simulation
            Simulation.simuliereSpielstart(this, this.getUnternehmen());
            runde++;    //um in die runde 1 zu starten
        }
        else {
            Simulation.simuliere(this, unternehmen);
        }

        this.naechsteUnternehmen = new Stack<Unternehmen>();
        for(int i = unternehmen.size() - 1; i >= 0; i--) {   // bisschen kompliziert damit der erste auch erster ist :D

            naechsteUnternehmen.push(unternehmen.get(i));
        }

        if(!naechsteUnternehmen.isEmpty()) {
            aktuellesUnternehmen = naechsteUnternehmen.pop();
            return null;
        }
        return "Kann die runde nicht starten!";

    }

    public Integer zugBeendet() {

        if(!naechsteUnternehmen.isEmpty()) {
            aktuellesUnternehmen = naechsteUnternehmen.pop();
            return 0;
        }
        aktuellesUnternehmen = null;
        
        if(!checkSpielende()) {
            rundenStart();
            runde++;
            return runde;
        }
        return -1;
    }
    
    private boolean checkSpielende() {
        if(this.runde >= this.rundenAnzahl) {
            return true;
        }
        // hier könnten noch umsatz oder gewinnziele

        return false;
    }

    public List<Unternehmen> getUnternehmen() {
        return unternehmen;
    }

    public Unternehmen getAktuellesUnternehmen() {
        return aktuellesUnternehmen;
    }

    public void setAktuellesUnternehmen(Unternehmen u) {
        this.aktuellesUnternehmen = u;
    }

    public void setUnternehmen(List<Unternehmen> unternehmen) {
        this.unternehmen = unternehmen;
    }

    public void unternehmenHinzufuegen(Unternehmen u) {

        this.unternehmen.add(u);
    }

    public int getRunde() {
        return this.runde;
    }

    public int getRundenAnzahl() {
        return rundenAnzahl;
    }

    public void setRundenAnzahl(int rundenAnzahl) {
        this.rundenAnzahl = rundenAnzahl;
    }

    private static List<String> log = new ArrayList<String>();

    public static void log(String nachricht) {
        log.add(nachricht);
    }

    public static List<String> getLog() {
        return log;
    }

}
