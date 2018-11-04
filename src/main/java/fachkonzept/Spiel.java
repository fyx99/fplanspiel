package fachkonzept;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

public class Spiel {

    private int runde = 0;
    private int rundenAnzahl = 10;
    private int szenario;
    private Unternehmen aktuellesUnternehmen = null;
    private List<Unternehmen> unternehmen = new ArrayList<Unternehmen>();
    private Stack<Unternehmen> naechsteUnternehmen;
    private List<Unternehmen> rangliste = new ArrayList<Unternehmen>();

    public Spiel() {

    }

    public Spiel(int rundenAnzahl) {
        this.rundenAnzahl = rundenAnzahl;
    }

    public String rundenStart() {
        
        if(runde == 0) {
            //Spielstart simulieren
            Simulation.simuliereSpielstart(this);
            runde++;    //um in die runde 1 zu starten
        }
        else {
            Simulation.simuliere(this);
        }
        ranglisteErstellen();
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
            ranglisteErstellen();
            return true;
        }

        return false;
    }
    
    public List<Unternehmen> ranglisteErstellen() {
        rangliste = new ArrayList<Unternehmen>();
        rangliste = unternehmen.stream()
                .sorted((u1, u2) -> Double.compare(u2.getGuv().rundenErgebnis(), u1.getGuv().rundenErgebnis()))
                .collect(Collectors.toList());
        
        return rangliste;
    }

    public List<Unternehmen> getUnternehmen() {
        return unternehmen;
    }

    public Unternehmen getAktuellesUnternehmen() {
        return aktuellesUnternehmen;
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
    
    public List<Unternehmen> getRangliste() {
        return rangliste;
    }


    private static List<String> log = new ArrayList<String>();

    public static void log(String nachricht) {
        log.add(nachricht);
    }

    public static List<String> getLog() {
        return log;
    }

	public int getSzenario() {
		return szenario;
	}

	public void setSzenario(int szenario) {
		this.szenario = szenario;
	}

}
