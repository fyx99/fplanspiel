package fachkonzept.markt;

import java.util.ArrayList;
import java.util.List;

import fachkonzept.Angebot;
import fachkonzept.Spiel;
import fachkonzept.Umsatz;
import fachkonzept.Unternehmen;

public abstract class Markt {
	
	private Unternehmen u;

	private List<Angebot> angebote = new ArrayList<Angebot>();

	public void angebotHinzu(Angebot a) {
		this.angebote.add(a);
	}

	public void kaufen(Angebot a, int menge, Unternehmen k) {
		a.setMenge(a.getMenge() - menge);
		if(a.getMenge() <= 0) {
		    angebote.remove(a);
		}
		Spiel.log(a.getId() + " gekaufet übrig " + a.getMenge());
		k.verringereKapital(a.getPreis() * menge);
		// unternehmen bezahlt angebot

	}

	public void kaufen(Angebot a, int menge) {
	    a.setMenge(a.getMenge() - menge);
		angebote.remove(a);
	      if(a.getMenge() <= 0) {
	            angebote.remove(a);
	        }
		// simulation muss nicht bezahlen :)
		// die einlagerung ist in der sub class

	}
	
	List<Umsatz> umsatzHistorie = new ArrayList<Umsatz>();
	
	private void umsatzFesthalten(Angebot a, int menge, int runde) {
		umsatzHistorie.add(new Umsatz(a, menge, runde - 1));	//da sim am anfang 
	}

	public Angebot verkaufen(Angebot a, int menge, Unternehmen vk) {	//nur an simulation !!!!
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
		return verbleibendesAngebot;
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

	public Angebot anbieten(Angebot a) {
		a.setMarkttyp(this);
		//jetzt weiß das angebot wo es gelandet ist -> dann kann mans auch kaufen
		//muss schauen ob es vergleichbares angebot gibt
		for (Angebot aa : this.angebote) {
			if(vergleiche(a,aa)) {
				aa.setMenge(a.getMenge() + aa.getMenge());
				return aa;}
		}
		angebote.add(a);
		return a;
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

	public Unternehmen getU() {
		return u;
	}

	public void setU(Unternehmen u) {
		this.u = u;
	}
	
	private boolean vergleiche(Angebot a, Angebot b) {
		boolean t = false;
		if(a.getMarkteinheit().equals(b.getMarkteinheit()) && a.getPreis() == b.getPreis())
			t = true;
		
		return t;
	}
	

}