package fachkonzept.markt;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import fachkonzept.Angebot;
import fachkonzept.Spiel;
import fachkonzept.Standort;
import fachkonzept.Umsatz;
import fachkonzept.Unternehmen;
import fachkonzept.util.MitarbeiterFachgebiet;
import fachkonzept.util.SimulationsKonstanten;
import fachkonzept.util.StandortArt;

public abstract class Markt {

    private static List<Umsatz> umsatzHistorie = new ArrayList<Umsatz>();
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
    
    public void anbieten(Angebot a, Unternehmen anbieter) {       // für simulation
        for(Angebot aa : this.angebote) {
            if(vergleiche(a, aa)) {
                aa.setMenge(a.getMenge() + aa.getMenge());
                return;
            }
        }
        anbieter.beschaeftigeMitarbeiter(MitarbeiterFachgebiet.VERTRIEB, (int)(SimulationsKonstanten.VERTRIEBSMINUTEN_PRO_EURO * a.getMenge() * a.getPreis()));
        this.angebote.add(a);
        a.updateAttraktivitaet(anbieter);
    }
    
    
    
    public void kaufen(Angebot a, int menge, Unternehmen kaeufer) {
        a.setMenge(a.getMenge() - menge);
        if(a.getMenge() <= 0) {
            angebote.remove(a);
        }
        Spiel.log(a.getId() + " gekaufet übrig " + a.getMenge());
        kaeufer.kosten(a.getPreis() * menge, ("Kauf von " + a.getMarkteinheit().getClass().getSimpleName()));
        umsatzFesthalten(a, menge, kaeufer.getSpiel().getRunde(), new Unternehmen("Simulation", new Spiel(), new Standort(SimulationsKonstanten.getStandortFaktoren(StandortArt.NEUTRAL))));
        // unternehmen bezahlt angebot

    }

    public Angebot verkaufen(Angebot a, int menge, Unternehmen vk) {	// nur an simulation !!!!
        Angebot verbleibendesAngebot = a.kaufen(menge);
        angebote.remove(a);
        if(verbleibendesAngebot != null) {
            angebote.add(verbleibendesAngebot);
        }
        vk.umsatz(a.getPreis() * menge, "Verkauf "+a.getMarkteinheit().getName());

        vk.markteinheitEntfernen(a.getMarkteinheit(), menge);

        umsatzFesthalten(a, menge, vk.getSpiel().getRunde(), vk);
        return verbleibendesAngebot;
    }

    private void umsatzFesthalten(Angebot a, int menge, int runde, Unternehmen vk) {
        umsatzHistorie.add(new Umsatz(a, menge, runde, vk));    // da simulation am ende
    }

    public void angebotEntfernen(Angebot a) {
        angebote.remove(a);
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
    
    public static List<Umsatz> getUmsatzHistorie() {
        return umsatzHistorie;
    }
    
    public static List<Umsatz> getUmsatzHistorie(Unternehmen u) {
        return umsatzHistorie.stream().filter(umsatz -> umsatz.getVerkaeufer().equals(u)).collect(Collectors.toList());
    }


    private boolean vergleiche(Angebot a, Angebot b) {
        if(a.getMarkteinheit().getName().equals(b.getMarkteinheit().getName()) && a.getPreis() == b.getPreis())
            return true;

        return false;
    }
    

}