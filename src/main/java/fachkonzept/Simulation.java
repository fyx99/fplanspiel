package fachkonzept;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import fachkonzept.markt.Beschaffungsmarkt;
import fachkonzept.markt.Maschinenmarkt;


public class Simulation {

	public Simulation() {
		// TODO Auto-generated constructor stub
	}
	
	public static void simuliere(Spiel s, List<Unternehmen> us) {
		

		
		Iterator<Unternehmen> i = us.iterator();
		while(i.hasNext()) {
			Unternehmen n = i.next();
			n.verringereKapital(6);
			
			if(s.getRunde() == 1) {
				n.setKapital(1000);
				n.setBmarkt(beschaffungsmarktDemoDaten());
				n.setMmarkt(maschinenmarktDemoDaten());
			}
			else {//alle anderen Runden
				
			}
			List<Angebot> as = n.getVmarkt().getAngebote();
			for (int j = 0; j < as.size(); j++) {

				n.getVmarkt().verkaufen(as.get(j), as.get(j).getMenge(), n);	//alles kaufen
				
			}
			
			
		}
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
	private static Maschinenmarkt maschinenmarktDemoDaten() {
		Material holz = new Material(1, "Holz");
		Material stoff = new Material(1, "Stoff");
		Material leder = new Material(1, "Leder");
		Material glas = new Material(1, "Glas");
		Material kunststoff = new Material(1, "Kunststoff");
		Material edelstahl = new Material(1, "Edelstahl");
		
		Produkt holzstuhl = new Produkt("Holzstuhl");
		Map<String, Integer> map_hst = new HashMap<String, Integer>();	//für jedes Produkt Map mit benötigten Ressourcen
		map_hst.put(holz.getName(), 10);//10 Holz 
		
		Produkt stoffstuhl = new Produkt("Stoffstuhl");
		Map<String, Integer> map_sst = new HashMap<String, Integer>();	
		map_sst.put(holz.getName(), 8);
		map_sst.put(stoff.getName(), 4);
		
		Produkt lederstuhl = new Produkt("Lederstuhl");
		Map<String, Integer> map_lst = new HashMap<String, Integer>();	
		map_lst.put(holz.getName(), 8);
		map_lst.put(leder.getName(), 4);
		
		Produkt holztisch = new Produkt("Holztisch");
		Map<String, Integer> map_ht = new HashMap<String, Integer>();	
		map_ht.put(holz.getName(), 32);
		
		Produkt glastisch = new Produkt("Glastisch");
		Map<String, Integer> map_gt = new HashMap<String, Integer>();	
		map_gt.put(edelstahl.getName(), 10);
		map_gt.put(glas.getName(), 18);
		
		Produkt kunststofftisch = new Produkt("Kunststofftisch");
		Map<String, Integer> map_kt = new HashMap<String, Integer>();	
		map_kt.put(kunststoff.getName(), 32);
		
		Produkt holzschrank = new Produkt("Holzschrank");
		Map<String, Integer> map_hsc = new HashMap<String, Integer>();	
		map_hsc.put(holz.getName(), 80);
		
		Produkt edelstahlschrank = new Produkt("Edelstahlschrank");
		Map<String, Integer> map_esc = new HashMap<String, Integer>();	
		map_esc.put(holz.getName(), 60);
		map_esc.put(edelstahl.getName(), 20);
		
		Produkt glasschrank = new Produkt("Glasschrank");
		Map<String, Integer> map_gsc = new HashMap<String, Integer>();	
		map_gsc.put(holz.getName(), 60);
		map_gsc.put(glas.getName(), 20);
		
		//Map für jedes Produkt mit den benötigten Ressourcen
		//Produktionsmatrix pm = new Produktionsmatrix(map);
		
		//Stühle
		Maschine m1 = new Maschine("Holzstuhl-Maschine", 100, holzstuhl, new Produktionsmatrix(map_hst), 15);
		Maschine m2 = new Maschine("Stoffstuhl-Maschine", 57, stoffstuhl, new Produktionsmatrix(map_sst), 20);
		Maschine m3 = new Maschine("Lederstuhl-Maschine", 50, lederstuhl, new Produktionsmatrix(map_lst), 25);
		
		//Tische
		Maschine m4 = new Maschine("Holztisch-Maschine", 50, holztisch, new Produktionsmatrix(map_ht), 100);
		Maschine m5 = new Maschine("Glastisch-Maschine", 35, glastisch, new Produktionsmatrix(map_gt), 125);
		Maschine m6 = new Maschine("Kunststofftisch-Maschine", 180, kunststofftisch, new Produktionsmatrix(map_kt), 20);
		
		//Schränke
		Maschine m7 = new Maschine("Holzschrank-Maschine", 65, holzschrank, new Produktionsmatrix(map_hsc), 150);
		Maschine m8 = new Maschine("Edelstahlschrank-Maschine", 50, edelstahlschrank, new Produktionsmatrix(map_esc), 185);
		Maschine m9 = new Maschine("Glasschrank-Maschine", 38, glasschrank, new Produktionsmatrix(map_gsc), 215);
		
		//Maschinen auf Maschinenmarkt anbieten
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

}
