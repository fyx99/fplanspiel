package fachkonzept;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Spiel {

	private int runde = 1;
	
	
	public Spiel() {
		
	}

	private void neueRunde() {
		this.runde++;
		if(!checkSpielende()) {
			rundenStart();
		}
	}
	
	private boolean checkSpielende() {
		if(this.runde > 10) {
			this.ende = true;
		}
		//hier könnten noch umsatz oder gewinnziele
		
		return false;
	}
	
	boolean ende = false;

	Unternehmen aktuellesUnternehmen = null;

	List<Unternehmen> unternehmen = new ArrayList<Unternehmen>();

	public List<Unternehmen> getUnternehmen() {
		return unternehmen;
	}

	private Stack<Unternehmen> naechsteUnternehmen;

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
		return "funzt net";

	}

	public Unternehmen zugBeendet() {
		
		//nächster ist dran

		if (!naechsteUnternehmen.isEmpty()) {
			aktuellesUnternehmen = naechsteUnternehmen.pop();
			return aktuellesUnternehmen;
		}
		else {
			//alle durch -> Simulation
			aktuellesUnternehmen = null;
			neueRunde();
			return null;
		}

	}
	public String gameStatsHelper() {
		String k ="";
		for (int i = 0; i < unternehmen.size(); i++) {
			k += "U: " + unternehmen.get(i).getName() + " K: " + unternehmen.get(i).getKapital() + " Umsatz " 
				+ unternehmen.get(i).getUmsatz() + "\n";
		}
		k += ende + "!";
		return k;
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

}
