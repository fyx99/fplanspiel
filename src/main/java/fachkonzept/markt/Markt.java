package fachkonzept.markt;

import java.util.ArrayList;
import java.util.List;

import fachkonzept.Angebot;
import fachkonzept.Spiel;
import fachkonzept.Umsatz;
import fachkonzept.Unternehmen;

public abstract class Markt {

    private Unternehmen u;
    private List<Umsatz> umsatzHistorie = new ArrayList<Umsatz>();
    private List<Angebot> angebote = new ArrayList<Angebot>();

    public void anbieten(Angebot a) {       // für simulation
        for(Angebot aa : this.angebote) {
            if(vergleiche(a, aa)) {
                aa.setMenge(a.getMenge() + aa.getMenge());
                return;
            }
        }
        this.angebote.add(a);
    }
    
    public void kaufen(Angebot a, int menge, Unternehmen kaeufer) {
        a.setMenge(a.getMenge() - menge);
        if(a.getMenge() <= 0) {
            angebote.remove(a);
        }
        Spiel.log(a.getId() + " gekaufet übrig " + a.getMenge());
        kaeufer.kosten(a.getPreis() * menge, ("Kauf von " + a.getMarkteinheit().getClass().getSimpleName()));
        // unternehmen bezahlt angebot

    }

    public Angebot verkaufen(Angebot a, int menge, Unternehmen vk) {	// nur an simulation !!!!
        Angebot verbleibendesAngebot = a.kaufen(menge);
        angebote.remove(a);
        if(verbleibendesAngebot != null) {
            angebote.add(verbleibendesAngebot);
        }
        vk.umsatz(a.getPreis() * menge, "Verkauf");

        vk.markteinheitEntfernen(a.getMarkteinheit(), menge);

        umsatzFesthalten(a, menge, vk.getSpiel().getRunde());
        return verbleibendesAngebot;
    }

    private void umsatzFesthalten(Angebot a, int menge, int runde) {
        umsatzHistorie.add(new Umsatz(a, menge, runde));    // da simulation am ende
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

    public void angebotEntfernen(Angebot a) {
        angebote.remove(a);
    }

    public Unternehmen getU() {
        return u;
    }

    public void setU(Unternehmen u) {
        this.u = u;
    }

    public List<Angebot> getAngebote() {
        return angebote;
    }

    public void setAngebote(List<Angebot> angebote) {
        this.angebote = angebote;
    }

    public void angebotHinzu(Angebot a) {
        this.angebote.add(a);
    }

    private boolean vergleiche(Angebot a, Angebot b) {
        boolean t = false;
        if(a.getMarkteinheit().equals(b.getMarkteinheit()) && a.getPreis() == b.getPreis())
            t = true;

        return t;
    }

}