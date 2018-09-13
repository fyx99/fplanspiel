package Fachkonzept;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Spiel {

	int runde = 1;
	
	
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

	public void rundenStart() {
		
		//wir simulieren jetzt mal am rundenanfang
		Simulation.simuliere(this, unternehmen);

		this.naechsteUnternehmen = new Stack<Unternehmen>();
		for (int i = 0; i < unternehmen.size(); i++) {

			naechsteUnternehmen.push(unternehmen.get(i));
		}
		
		if (!naechsteUnternehmen.isEmpty()) {
			naechstesUnternehmen = naechsteUnternehmen.pop();
		}

	}

	public String zugBeendet() {
		
		//nächster ist dran

		if (!naechsteUnternehmen.isEmpty()) {
			naechstesUnternehmen = naechsteUnternehmen.pop();
			return "nächster";
		}
		else {
			//alle durch -> Simulation
			naechstesUnternehmen = null;
			neueRunde();
			return "simluiere";
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
