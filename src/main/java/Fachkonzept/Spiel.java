package Fachkonzept;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Spiel {

	private int runde = 1;
	
	
	public Spiel() {
		
	}

	private void neueRunde() {
		runde++;
		
		if(runde > 3) {
			ende = true;
		}
		rundenStart();
	}
	
	boolean ende = false;

	Unternehmen naechstesUnternehmen = null;

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
			return naechstesUnternehmen = naechsteUnternehmen.pop();
		}
		return null;

	}

	public Unternehmen zugBeendet() {
		
		//nächster ist dran

		if (!naechsteUnternehmen.isEmpty()) {
			naechstesUnternehmen = naechsteUnternehmen.pop();
			return naechstesUnternehmen;
		}
		else {
			//alle durch -> Simulation
			naechstesUnternehmen = null;
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

	public Unternehmen getNaechstesUnternehmen() {
		return naechstesUnternehmen;
	}

	public void setNaechstesUnternehmen(Unternehmen naechstesUnternehmen) {
		this.naechstesUnternehmen = naechstesUnternehmen;
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
