package fachkonzept;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import fachkonzept.markt.Arbeitsmarkt;
import fachkonzept.markt.Beschaffungsmarkt;
import fachkonzept.markt.Finanzmarkt;
import fachkonzept.markt.Maschinenmarkt;
import fachkonzept.util.MaschinenArt;
import fachkonzept.util.MaterialArt;
import fachkonzept.util.MitarbeiterFachgebiet;
import fachkonzept.util.ProduktArt;
import fachkonzept.util.ProduktTyp;
import fachkonzept.util.SimulationsKonstanten;

public class Simulation {

    private static List<String> simlog = new ArrayList();

    public static void simuliereSpielstart(Spiel s, List<Unternehmen> us) {

        Iterator<Unternehmen> i = us.iterator();
        while(i.hasNext()) {
            Unternehmen n = i.next();
            n.setKapital(1000);
            n.setBmarkt(beschaffungsmarktDemoDaten(n));
            n.setMmarkt(maschinenmarktDemoDaten());
            n.setFmarkt(finanzmarktDemoDaten());
            n.setAmarkt(arbeitsmarktDemoDaten(n));

        }

    }

    public static void simuliere(Spiel s) {

        Iterator<Unternehmen> i = s.getUnternehmen().iterator();
        while(i.hasNext()) {
            Unternehmen n = i.next();
            // n.verringereKapital(6);

            simuliereKredittilgung(n);
            simuliereLohnzahlung(n);

        }
        // gemeinsame konkurrenz dinge
        simuliereAbsatzmarkt(s.getUnternehmen());

    }

    private static void simuliereKredittilgung(Unternehmen u) {
        
        Iterator<Verbindlichkeit> iter = u.getVerbindlichkeiten().iterator();

        while(iter.hasNext()) {
            Verbindlichkeit v = iter.next();
            // jeder kredit muss getilgt werden
            
            u.kosten(v.getKredit().getZinssatz() * v.getVerbleibendeSumme()
                    + v.tilgen(v.getVerbleibendeSumme() / (v.getKredit().getLaufzeit() - v.getAktuelleLaufzeit())), "Kreditkosten");
            // irgendwie nicht die richtige formel :D
            if(v.getAktuelleLaufzeit() == v.getKredit().getLaufzeit() || v.getVerbleibendeSumme() <= 0) {
                iter.remove();
            }
        }
    }
    
    private static void simuliereLohnzahlung(Unternehmen u) {
        for(Arbeitskraft ak : u.getMitarbeiter()) {
            u.kosten(ak.getM().getLohnkosten(), "Lohnkosten");
        }
    }

    private static void simuliereAbsatzmarkt(List<Unternehmen> us) {
        for(ProduktArt produktArt : ProduktArt.values()) {
            // pro produkt gehen wir den spaß jetzt mal durch
            Map<Angebot, Unternehmen> produkt_angebote = new HashMap<Angebot, Unternehmen>();
            for(Unternehmen u : us) {
                
                List<Angebot> unternehmenAngebote = u.getVmarkt().getAngeboteByProduktArt(produktArt);

                produkt_angebote.putAll(unternehmenAngebote.stream().collect(Collectors.toMap(a -> a, a -> u)));
                //hier werden hoffentlich die angebote gesammelt :D

            }
            // jetzt haben wir alle angebote der speziellen produkt art
            
            if(produkt_angebote.isEmpty())
                continue;
            
            simuliereEinzelnesProdukt(produkt_angebote, SimulationsKonstanten.getProduktMarktvolumen(produktArt), 
                    SimulationsKonstanten.getProduktMarktpreis(produktArt));
        }
    }

    private static void simuliereEinzelnesProdukt(Map<Angebot, Unternehmen> angebote, int nachfrage, double grundpreis) {
        //als erstes die liste nach angebotsstärke sortieren
        //hier bisher nur nach preis
        List<Angebot> sortierteAngebote = angebote.keySet().stream()
        .sorted((Angebot u1, Angebot u2) 
                -> Double.compare(u1.getPreis(), u2.getPreis()))
        .collect(Collectors.toList());
        int verbleibendeNachfrage = nachfrage;
        
        for(int i = 0; i < sortierteAngebote.size(); i++) {
            //bevor was gemacht wird sollte natürlich geschaut werden, ob der marktpreis überschritten ist und ob die nachfrage angepasst werden muss
            if(sortierteAngebote.get(i).getPreis() > grundpreis) {
                verbleibendeNachfrage -= nachfrageAnpassen(nachfrage, grundpreis, sortierteAngebote.get(i).getPreis());
            }
            if(verbleibendeNachfrage <= 0){
                break;
            }
            List<Angebot> auswahl = angbotsAuswahl(sortierteAngebote.subList(i, sortierteAngebote.size()));
            i += auswahl.size() - 1;
            
            //-> ab hier einfach abkaufen
            while(verbleibendeNachfrage > 0 && !auswahl.isEmpty()) {
                verbleibendeNachfrage = nachfrageVerteilen(auswahl, angebote, verbleibendeNachfrage);
            }
            
        }
    }
    
    private static int nachfrageAnpassen(int gesamtNachfrage, double grundpreis, double aktPreis) {
        //funktion die den preis verringert
        double kappungsfaktor = 3; //1% drüber -> 3 runter
        double preisDifferenz = 1 - aktPreis / grundpreis;      //in prozent über grundpreis
        return (int)(gesamtNachfrage * (preisDifferenz * kappungsfaktor));
        
    }

    public static double getPeriodenverschiebung(int runde) {		// um die nachfragemenge zu verändern
        double randomStart = Math.random() * 2;
        return 1 + Math.sin(0.125 * Math.PI * runde + randomStart * Math.PI) * 0.5;
    }
    
    private static List<Angebot> angbotsAuswahl(List<Angebot> angebote) {
        //wählt unter den angeboten das/ evt. die günstigste/n aus
        List<Angebot> ret = new ArrayList<Angebot>();
        int referenzPreis = (int)angebote.get(0).getPreis();
        for(Angebot a : angebote) {
            if(((int)a.getPreis()) <= referenzPreis) {
                ret.add(a);
            }
            else {
                break;
            }
        }

        return ret;
    }
    
    private static int nachfrageVerteilen(List<Angebot> angebote, Map<Angebot, Unternehmen> unternehmen, int nachfrage) {
        //hier wird auf eine liste mit n angeboten die gleichwertig sind die nachfrage verteilt
        
        int verbleibendeNachfrage = nachfrage;
        int durchschnittlicheMenge = verbleibendeNachfrage / angebote.size();
        int minMengeAngebote = angebote.stream().min(Comparator.comparing(Angebot::getMenge)).get().getMenge();
        durchschnittlicheMenge = minMengeAngebote < durchschnittlicheMenge ? minMengeAngebote : durchschnittlicheMenge;
        Iterator<Angebot> it = angebote.iterator();
        while(it.hasNext()) {
            Angebot a = it.next();
            verbleibendeNachfrage -= durchschnittlicheMenge;
            Unternehmen verkaeufer = unternehmen.get(a);
            if(verkaeufer.getVmarkt().verkaufen(a, durchschnittlicheMenge, verkaeufer) == null) {
                it.remove();
            }
        }
        
        //return wert verbleibende nachfrage
        return verbleibendeNachfrage;
    }

    private static Beschaffungsmarkt beschaffungsmarktDemoDaten(Unternehmen n) {
    	double standortfaktor_material = n.getStandort().getFaktor_materialkosten();
        Material holz = new Material(MaterialArt.Holz);
        Material stoff = new Material(MaterialArt.Stoff);
        Material leder = new Material(MaterialArt.Leder);
        Material glas = new Material(MaterialArt.Glas);
        Material kunststoff = new Material(MaterialArt.Kunststoff);
        Material edelstahl = new Material(MaterialArt.Edelstahl);

        Beschaffungsmarkt b = new Beschaffungsmarkt();
        b.anbieten(new Angebot(holz, 100, 3 * standortfaktor_material));
        b.anbieten(new Angebot(stoff, 100, 3.50 * standortfaktor_material));
        b.anbieten(new Angebot(leder, 100, 9 * standortfaktor_material));
        b.anbieten(new Angebot(glas, 100, 14 * standortfaktor_material));
        b.anbieten(new Angebot(kunststoff, 100, 1 * standortfaktor_material));
        b.anbieten(new Angebot(edelstahl, 100, 6 * standortfaktor_material));

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
    
    private static Arbeitsmarkt arbeitsmarktDemoDaten(Unternehmen n) {
    	double standortfaktor_mitarbeiterkosten = n.getStandort().getFaktor_mitarbeiterkosten();
        Mitarbeiter ma1 = new Mitarbeiter("Name unnötig", 300, 120000, MitarbeiterFachgebiet.MASCHINE);
        Mitarbeiter ma2 = new Mitarbeiter("Name unnötig", 400, 60000, MitarbeiterFachgebiet.MASCHINE);  //bsp weniger arbeitszeit
        Mitarbeiter ma3 = new Mitarbeiter("Name unnötig", 200, 120000, MitarbeiterFachgebiet.VERTRIEB);
        Mitarbeiter ma4 = new Mitarbeiter("Name unnötig", 300, 120000, MitarbeiterFachgebiet.VERTRIEB);
        Mitarbeiter ma5 = new Mitarbeiter("Name unnötig", 250, 120000, MitarbeiterFachgebiet.VERWALTUNG);

        Arbeitsmarkt am = new Arbeitsmarkt();
        am.anbieten(new Angebot(ma1, 30, 20 * standortfaktor_mitarbeiterkosten));      //ist der preis hier nötig? oder einfach 0
        am.anbieten(new Angebot(ma2, 30, 20 * standortfaktor_mitarbeiterkosten));
        am.anbieten(new Angebot(ma3, 30, 20 * standortfaktor_mitarbeiterkosten));
        am.anbieten(new Angebot(ma4, 30, 20 * standortfaktor_mitarbeiterkosten));
        am.anbieten(new Angebot(ma5, 30, 20 * standortfaktor_mitarbeiterkosten));
        return am;
    }

    private static Maschinenmarkt maschinenmarktDemoDaten() {
        Material holz = new Material(MaterialArt.Holz);
        Material stoff = new Material(MaterialArt.Stoff);
        Material leder = new Material(MaterialArt.Leder);
        Material glas = new Material(MaterialArt.Glas);
        Material kunststoff = new Material(MaterialArt.Kunststoff);
        Material edelstahl = new Material(MaterialArt.Edelstahl);

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
        Maschine m1 = new Maschine(MaschinenArt.Holzstuhlmaschine, 100, holzstuhl, new Produktionsmatrix(map_hst), 15, 1);
        Maschine m2 = new Maschine(MaschinenArt.Stoffstuhlmaschine, 57, stoffstuhl, new Produktionsmatrix(map_sst), 20, 2);
        Maschine m3 = new Maschine(MaschinenArt.Lederstuhlmaschine, 50, lederstuhl, new Produktionsmatrix(map_lst), 25, 3);

        // Tische
        Maschine m4 = new Maschine(MaschinenArt.Holztischmaschine, 50, holztisch, new Produktionsmatrix(map_ht), 100, 4);
        Maschine m5 = new Maschine(MaschinenArt.Glastischmaschine, 35, glastisch, new Produktionsmatrix(map_gt), 125, 5);
        Maschine m6 = new Maschine(MaschinenArt.Kunststofftischmaschine, 180, kunststofftisch, new Produktionsmatrix(map_kt), 20, 6);

        // Schränke
        Maschine m7 = new Maschine(MaschinenArt.Holzschrankmaschine, 65, holzschrank, new Produktionsmatrix(map_hsc), 150, 7);
        Maschine m8 = new Maschine(MaschinenArt.Edelstahlschrankmaschine, 50, edelstahlschrank, new Produktionsmatrix(map_esc), 185, 8);
        Maschine m9 = new Maschine(MaschinenArt.Glasschrankmaschine, 38, glasschrank, new Produktionsmatrix(map_gsc), 215, 9);

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
