package fachkonzept;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Spiel {

	private int runde = 1;
	
	
	public Spiel() {
		
	}

	private void neueRunde() {
		runde++;
		
		if(!checkSpielende()) {
			rundenStart();
		}
		
	}
	
	private boolean checkSpielende() {
		if(runde > 10) {
			ende = true;
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

	public Unternehmen rundenStart() {
		
		//wir simulieren jetzt mal am rundenanfang
		Simulation.simuliere(this, unternehmen);

		this.naechsteUnternehmen = new Stack<Unternehmen>();
		for (int i = 0; i < unternehmen.size(); i++) {

			naechsteUnternehmen.push(unternehmen.get(i));
		}
		
		if (!naechsteUnternehmen.isEmpty()) {
			return aktuellesUnternehmen = naechsteUnternehmen.pop();
		}
		return null;

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
		return runde;
	}

}
