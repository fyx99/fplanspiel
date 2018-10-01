package fachkonzept;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import fachkonzept.markt.Arbeitsmarkt;
import fachkonzept.markt.Beschaffungsmarkt;
import fachkonzept.markt.Finanzmarkt;
import fachkonzept.markt.Maschinenmarkt;

public class Simulation {

    public Simulation() {
        // TODO Auto-generated constructor stub
    }

    private static List<String> simlog = new ArrayList();

    public static void simuliereSpielstart(Spiel s, List<Unternehmen> us) {

        Iterator<Unternehmen> i = us.iterator();
        while(i.hasNext()) {
            Unternehmen n = i.next();
            n.setKapital(1000);
            n.setBmarkt(beschaffungsmarktDemoDaten());
            n.setMmarkt(maschinenmarktDemoDaten());
            n.setFmarkt(finanzmarktDemoDaten());
            n.setAmarkt(arbeitsmarktDemoDaten());

        }

    }

    public static void simuliere(Spiel s, List<Unternehmen> us) {

        Iterator<Unternehmen> i = us.iterator();
        while(i.hasNext()) {
            Unternehmen n = i.next();
            // n.verringereKapital(6);

            simuliereKredittilgung(n);
            simuliereLohnzahlung(n);

        }
        // gemeinsame konkurrenz dinge
        simuliereAbsatzmarkt(us);

    }

    public static void simuliereKredittilgung(Unternehmen u) {
        for(Verbindlichkeit v : u.getVerbindlichkeiten()) {
            // jeder kredit muss getilgt werden

            u.kosten(v.tilgen(v.getVerbleibendeSumme() / (v.getKredit().getLaufzeit() - v.getAktuelleLaufzeit()))
                    + v.getKredit().getZinssatz() * v.getVerbleibendeSumme(), "Kreditkosten");
            // irgendwie nicht die richtige formel :D

        }
    }
    
    public static void simuliereLohnzahlung(Unternehmen u) {
        for(Arbeitskraft ak : u.getMitarbeiter()) {
            u.kosten(ak.getM().getLohnkosten(), "Lohnkosten");
        }
    }

    public static void simuliereAbsatzmarkt(List<Unternehmen> us) {
        for(ProduktArt produktArt : ProduktArt.values()) {
            // pro produkt gehen wir den spaß jetzt mal durch
            List<Angebot> produkt_angebote = new ArrayList<Angebot>();
            for(Unternehmen u : us) {
                produkt_angebote.addAll(u.getVmarkt().getAngeboteByProduktArt(produktArt));

            }
            // jetzt haben wir alle angebote der speziellen produkt art
            log("vorhandene produktart size: " + produkt_angebote.size());
            simuliereEinzelnesProdukt(produkt_angebote, getProduktMarktvolumen(produktArt), getProduktMarktpreis(produktArt));
        }
    }

    public static void simuliereEinzelnesProdukt(List<Angebot> angebote, int nachfrage, double grundpreis) {
        int verbleibende_nachfrage = nachfrage;
        int gedeckt = 0;
        int preis = 0;
        while(gedeckt < verbleibende_nachfrage) {

            List<Angebot> pot_angebote = new ArrayList<Angebot>();
            for(Angebot a : angebote) {		// erstmal angebote die preislich in frage kommen
                if(a.getPreis() == preis) {
                    pot_angebote.add(a);

                }
            }
            log("pot angebote: " + pot_angebote.size() + " bei preis " + preis);
            // => verteilen
            double kappungsindex = 1;
            double kappungsfaktor = 1;
            int volle = pot_angebote.size();
            while(gedeckt < verbleibende_nachfrage && !pot_angebote.isEmpty() && volle > 0) {

                // schneiden
                int durchschnittliche_menge = (verbleibende_nachfrage - gedeckt) / pot_angebote.size();

                for(Angebot aa : pot_angebote) {
                    if(aa.getMenge() < durchschnittliche_menge)
                        durchschnittliche_menge = aa.getMenge();
                }

                // verteilen
                Iterator<Angebot> iter = pot_angebote.iterator();

                while(iter.hasNext()) {
                    Angebot aa = iter.next();
                    // Spiel.log("Unternehmen " + aa.getMarkttyp().getU().getName() + " verkauft " + aa.getMarkteinheit().getName() + " menge: "
                    // + durchschnittliche_menge + " bei preis " + preis);
                    gedeckt += durchschnittliche_menge;

                    if(aa.getMarkttyp().verkaufen(aa, durchschnittliche_menge, aa.getMarkttyp().getU()) == null) {
                        iter.remove();
                        volle--;
                        log("Simkauf: " + aa.getId() + " " + durchschnittliche_menge + " von " + aa.getMarkttyp().getU().getName());
                    }
                    Spiel.log(volle + "");
                }

            }
            preis++;	// jetzt mal schauen ob es ein teureres gibt
            if(preis > grundpreis) {
                // hier wird jetzt die nachfrage gekappt, da kunden zwar x kaufen würden aber nicht zu denen preisen :D
                verbleibende_nachfrage = 0;
                // bsp direkte kappung
                // hier wäre ein beispiel wie man es quadratisch abnehmen lassen kann - kappungsfaktor zum angleichen
                /*
                 * verbleibende_nachfrage = verbleibende_nachfrage - (int)(kappungsfaktor * kappungsindex * kappungsindex); kappungsindex++;
                 */ }
        }
    }

    public static double getProduktMarktpreis(ProduktArt produktArt) {
        // hier als vorläufiges beispiel mit dem string -> soll natürlich wenn dann als enum
        double preis = 0;
        switch(produktArt) {
        case Holzstuhl:
            preis = 40;
            break;
        case Stoffstuhl:
            preis = 40;
            break;
        case Lederstuhl:
            preis = 40;
            break;
        case Holztisch:
            preis = 40;
            break;
        case Glastisch:
            preis = 40;
            break;
        case Kunststofftisch:
            preis = 40;
            break;
        case Holzschrank:
            break;
        case Edelstahlschrank:
            preis = 40;
            break;
        case Glasschrank:
            preis = 40;
            break;
        default:
            return -1;
        }
        return preis;
    }

    public static int getProduktMarktvolumen(ProduktArt produktArt) {
        // hier als vorläufiges beispiel mit dem string -> soll natürlich wenn dann als enum
        int volumen = 0;
        switch(produktArt) {
        case Holzstuhl:
            volumen = 50;
            break;
        case Stoffstuhl:
            volumen = 8808;
            break;
        case Lederstuhl:
            volumen = 40;
            break;
        case Holztisch:
            volumen = 40;
            break;
        case Glastisch:
            volumen = 40;
            break;
        case Kunststofftisch:
            volumen = 40;
            break;
        case Holzschrank:
            break;
        case Edelstahlschrank:
            volumen = 40;
            break;
        case Glasschrank:
            volumen = 40;
            break;
        default:
            return -1;
        }
        return volumen;
    }

    public static double getPeriodenverschiebung(int runde) {		// um die nachfragemenge zu verändern
        double randomStart = Math.random() * 2;
        return 1 + Math.sin(0.125 * Math.PI * runde + randomStart * Math.PI) * 0.5;
    }

    private static Beschaffungsmarkt beschaffungsmarktDemoDaten() {
        Material holz = new Material(1, "Holz");
        Material stoff = new Material(1, "Stoff");
        Material leder = new Material(1, "Leder");
        Material glas = new Material(1, "Glas");
        Material kunststoff = new Material(1, "Kunststoff");
        Material edelstahl = new Material(1, "Edelstahl");

        Beschaffungsmarkt b = new Beschaffungsmarkt();
        b.anbieten(new Angebot(holz, 100, 3));
        b.anbieten(new Angebot(stoff, 100, 3.50));
        b.anbieten(new Angebot(leder, 100, 9));
        b.anbieten(new Angebot(glas, 100, 14));
        b.anbieten(new Angebot(kunststoff, 100, 1));
        b.anbieten(new Angebot(edelstahl, 100, 6));

        return b;
    }

    private static Finanzmarkt finanzmarktDemoDaten() {
        Kredit k1 = new Kredit(100000, 0.05, 4, "Ultra-Cash");
        Kredit k2 = new Kredit(5000, 0.02, 2, "Kurzes-Cash");
        Kredit k3 = new Kredit(200000, 0.03, 8, "Mehr Cash");

        Finanzmarkt fm = new Finanzmarkt();
        fm.anbieten(new Angebot(k1, 1, 0));
        fm.anbieten(new Angebot(k2, 10, 0));
        fm.anbieten(new Angebot(k3, 4, 0));

        return fm;
    }
    
    private static Arbeitsmarkt arbeitsmarktDemoDaten() {
        Mitarbeiter ma1 = new Mitarbeiter("Name unnötig", 300, 120000, MitarbeiterFachgebiet.MASCHINE);
        Mitarbeiter ma2 = new Mitarbeiter("Name unnötig", 400, 60000, MitarbeiterFachgebiet.MASCHINE);  //bsp weniger arbeitszeit
        Mitarbeiter ma3 = new Mitarbeiter("Name unnötig", 200, 120000, MitarbeiterFachgebiet.VERTRIEB);
        Mitarbeiter ma4 = new Mitarbeiter("Name unnötig", 300, 120000, MitarbeiterFachgebiet.VERTRIEB);
        Mitarbeiter ma5 = new Mitarbeiter("Name unnötig", 250, 120000, MitarbeiterFachgebiet.VERWALTUNG);

        Arbeitsmarkt am = new Arbeitsmarkt();
        am.anbieten(new Angebot(ma1, 30, 20));      //ist der preis hier nötig? oder einfach 0
        am.anbieten(new Angebot(ma2, 30, 20));
        am.anbieten(new Angebot(ma3, 30, 20));
        am.anbieten(new Angebot(ma4, 30, 20));
        am.anbieten(new Angebot(ma5, 30, 20));
        return am;
    }

    private static Maschinenmarkt maschinenmarktDemoDaten() {
        Material holz = new Material(1, "Holz");
        Material stoff = new Material(1, "Stoff");
        Material leder = new Material(1, "Leder");
        Material glas = new Material(1, "Glas");
        Material kunststoff = new Material(1, "Kunststoff");
        Material edelstahl = new Material(1, "Edelstahl");

        Produkt holzstuhl = new Produkt(ProduktArt.Holzstuhl, ProduktTyp.Stuhl);
        Map<String, Integer> map_hst = new HashMap<String, Integer>();	// für jedes Produkt Map mit benötigten Ressourcen
        map_hst.put(holz.getName(), 10);// 10 Holz

        Produkt stoffstuhl = new Produkt(ProduktArt.Stoffstuhl, ProduktTyp.Stuhl);
        Map<String, Integer> map_sst = new HashMap<String, Integer>();
        map_sst.put(holz.getName(), 8);
        map_sst.put(stoff.getName(), 4);

        Produkt lederstuhl = new Produkt(ProduktArt.Lederstuhl, ProduktTyp.Stuhl);
        Map<String, Integer> map_lst = new HashMap<String, Integer>();
        map_lst.put(holz.getName(), 8);
        map_lst.put(leder.getName(), 4);

        Produkt holztisch = new Produkt(ProduktArt.Holztisch, ProduktTyp.Tisch);
        Map<String, Integer> map_ht = new HashMap<String, Integer>();
        map_ht.put(holz.getName(), 32);

        Produkt glastisch = new Produkt(ProduktArt.Glastisch, ProduktTyp.Tisch);
        Map<String, Integer> map_gt = new HashMap<String, Integer>();
        map_gt.put(edelstahl.getName(), 10);
        map_gt.put(glas.getName(), 18);

        Produkt kunststofftisch = new Produkt(ProduktArt.Kunststofftisch, ProduktTyp.Tisch);
        Map<String, Integer> map_kt = new HashMap<String, Integer>();
        map_kt.put(kunststoff.getName(), 32);

        Produkt holzschrank = new Produkt(ProduktArt.Holzschrank, ProduktTyp.Schrank);
        Map<String, Integer> map_hsc = new HashMap<String, Integer>();
        map_hsc.put(holz.getName(), 80);

        Produkt edelstahlschrank = new Produkt(ProduktArt.Edelstahlschrank, ProduktTyp.Schrank);
        Map<String, Integer> map_esc = new HashMap<String, Integer>();
        map_esc.put(holz.getName(), 60);
        map_esc.put(edelstahl.getName(), 20);

        Produkt glasschrank = new Produkt(ProduktArt.Glasschrank, ProduktTyp.Schrank);
        Map<String, Integer> map_gsc = new HashMap<String, Integer>();
        map_gsc.put(holz.getName(), 60);
        map_gsc.put(glas.getName(), 20);

        // Map für jedes Produkt mit den benötigten Ressourcen
        // Produktionsmatrix pm = new Produktionsmatrix(map);

        // Stühle
        Maschine m1 = new Maschine("Holzstuhl-Maschine", 100, holzstuhl, new Produktionsmatrix(map_hst), 15, 1);
        Maschine m2 = new Maschine("Stoffstuhl-Maschine", 57, stoffstuhl, new Produktionsmatrix(map_sst), 20, 2);
        Maschine m3 = new Maschine("Lederstuhl-Maschine", 50, lederstuhl, new Produktionsmatrix(map_lst), 25, 3);

        // Tische
        Maschine m4 = new Maschine("Holztisch-Maschine", 50, holztisch, new Produktionsmatrix(map_ht), 100, 4);
        Maschine m5 = new Maschine("Glastisch-Maschine", 35, glastisch, new Produktionsmatrix(map_gt), 125, 5);
        Maschine m6 = new Maschine("Kunststofftisch-Maschine", 180, kunststofftisch, new Produktionsmatrix(map_kt), 20, 6);

        // Schränke
        Maschine m7 = new Maschine("Holzschrank-Maschine", 65, holzschrank, new Produktionsmatrix(map_hsc), 150, 7);
        Maschine m8 = new Maschine("Edelstahlschrank-Maschine", 50, edelstahlschrank, new Produktionsmatrix(map_esc), 185, 8);
        Maschine m9 = new Maschine("Glasschrank-Maschine", 38, glasschrank, new Produktionsmatrix(map_gsc), 215, 9);

        // Maschinen auf Maschinenmarkt anbieten
        Maschinenmarkt b = new Maschinenmarkt();
        b.anbieten(new Angebot(m1, 3, 7000));
        b.anbieten(new Angebot(m2, 1, 7500));
        b.anbieten(new Angebot(m3, 1, 8000));
        b.anbieten(new Angebot(m4, 1, 9500));
        b.anbieten(new Angebot(m5, 1, 11500));
        b.anbieten(new Angebot(m6, 1, 8500));
        b.anbieten(new Angebot(m7, 1, 11000));
        b.anbieten(new Angebot(m8, 1, 12000));
        b.anbieten(new Angebot(m9, 1, 12500));

        return b;
    }

    public static List<String> getLog() {
        return simlog;
    }

    public static void log(String a) {
        simlog.add(a);
    }

}
