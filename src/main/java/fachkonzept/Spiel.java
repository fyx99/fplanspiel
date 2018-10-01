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

	private void neueRunde() {
		if(!checkSpielende()) {
			rundenStart();
            this.runde++;
		}
		else {
		    //spiel zu ende
		    
		}
	}
	
	private boolean checkSpielende() {
		if(this.runde >= this.rundenAnzahl) {
			return true;
		}
		//hier könnten noch umsatz oder gewinnziele
		
		return false;
	}



	public String rundenStart() {
		
		//wir simulieren jetzt mal am rundenanfang
		Simulation.simuliere(this, unternehmen);

		this.naechsteUnternehmen = new Stack<Unternehmen>();
		for (int i = unternehmen.size() - 1; i >= 0; i--) {   //bisschen kompliziert damit der erste auch erster ist :D

			naechsteUnternehmen.push(unternehmen.get(i));
		}
		
		if (!naechsteUnternehmen.isEmpty()) {
			aktuellesUnternehmen = naechsteUnternehmen.pop();
			return null;
		}
		return "Kann die runde nicht starten!";

	}

	public Integer zugBeendet() {
		
		//nächster ist dran

		if (!naechsteUnternehmen.isEmpty()) {
			aktuellesUnternehmen = naechsteUnternehmen.pop();
			return 0;
		}
		else {
			//alle durch -> Simulation
			aktuellesUnternehmen = null;
			int alteRunde = this.getRunde();
			neueRunde();
			return this.getRunde() > alteRunde ? this.getRunde() : -1;
		}

	}

    public List<Unternehmen> getUnternehmen() {
        return unternehmen;
    }

	public Unternehmen getAktuellesUnternehmen() {
		return aktuellesUnternehmen;
	}

	public void setAktuellesUnternehmen(Unternehmen naechstesUnternehmen) {
		this.aktuellesUnternehmen = naechstesUnternehmen;
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
	
	private static List<String> log = new ArrayList<String>();
	
	public static void log(String nachricht) {
		log.add(nachricht);
	}
	public static List<String> getLog(){
		return log;
	}

    public int getRundenAnzahl() {
        return rundenAnzahl;
    }

    public void setRundenAnzahl(int rundenAnzahl) {
        this.rundenAnzahl = rundenAnzahl;
    }
	

}
