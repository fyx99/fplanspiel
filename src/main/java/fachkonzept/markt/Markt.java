package fachkonzept.markt;

import java.util.ArrayList;
import java.util.List;

import fachkonzept.Angebot;
import fachkonzept.Spiel;
import fachkonzept.Umsatz;
import fachkonzept.Unternehmen;

public abstract class Markt {

	private List<Angebot> angebote = new ArrayList<Angebot>();

	public void angebotHinzu(Angebot a) {
		this.angebote.add(a);
	}

	public void kaufen(Angebot a, int menge, Unternehmen k) {
		Angebot verbleibendesAngebot = a.kaufen(menge);
		angebote.remove(a);
		if (verbleibendesAngebot != null) {
			angebote.add(verbleibendesAngebot);
		}
		k.verringereKapital(a.getPreis() * menge);
		// unternehmen bezahlt angebot

	}

	public void kaufen(Angebot a, int menge) {
		Angebot verbleibendesAngebot = a.kaufen(menge);
		angebote.remove(a);
		if (verbleibendesAngebot != null) {
			angebote.add(verbleibendesAngebot);
		}
		// simulation muss nicht bezahlen :)
		// die einlagerung ist in der sub class

	}
	
	List<Umsatz> umsatzHistorie = new ArrayList<Umsatz>();
	
	private void umsatzFesthalten(Angebot a, int menge, int runde) {
		umsatzHistorie.add(new Umsatz(a, menge, runde - 1));	//da sim am anfang 
	}

	public void verkaufen(Angebot a, int menge, Unternehmen vk) {	//nur an simulation !!!!
		Angebot verbleibendesAngebot = a.kaufen(menge);
		angebote.remove(a);
		if (verbleibendesAngebot != null) {
			angebote.add(verbleibendesAngebot);
		}
		vk.umsatz(a.getPreis() * menge);
		// erziehlt umsatz
		// bestand muss verringert werden
		vk.markteinheitEntfernen(a.getMarkteinheit(), menge);
		
		//zuletzt wollen wir das ereignis speichern
		umsatzFesthalten(a, menge, vk.getSpiel().getRunde());

	}
	
	public List<Umsatz> getUmsatzHistorie(Spiel s, int rundenZurueck) {

		List<Umsatz> umsaetze = new ArrayList<Umsatz>();
		for(Umsatz u : this.umsatzHistorie) {
			if(u.getRunde() >= s.getRunde() - rundenZurueck) {
				umsaetze.add(u);
			}
		}
		
		return umsaetze;
	}

	public void anbieten(Angebot a) {
		angebote.add(a);
	}

	public void angebotEntfernen(Angebot a) {
		angebote.remove(a);
	}

	public List<Angebot> getAngebote() {
		return this.angebote;
	}

	public void setAngebote(List<Angebot> angebote) {
		this.angebote = angebote;
	}

}