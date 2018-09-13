package Fachkonzept;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


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
			List<Angebot> as = n.getVmarkt().getAngebote();
			for (int j = 0; j < as.size(); j++) {

				n.getVmarkt().verkaufen(as.get(j), as.get(j).getMenge(), n);	//alles kaufen
				
			}
			
			
		}
	}
	
	private static Beschaffungsmarkt beschaffungsmarktDemoDaten() {
		AHolz ah = new AHolz(1);
		BHolz bh = new BHolz(1);
		Beschaffungsmarkt b = new Beschaffungsmarkt();
		b.anbieten(new Angebot(ah, 99, 3));
		b.anbieten(new Angebot(bh, 59, 1));
		
		return b;
	}
	private static Maschinenmarkt maschinenmarktDemoDaten() {
		AHolz aah = new AHolz(1);
		BHolz bbh = new BHolz(1);
		Produkt p1 = new Produkt("stuhl");
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put(aah.getClass().getName(), 3);
		map.put(bbh.getClass().getName(), 2);
		Produktionsmatrix pm = new Produktionsmatrix(map);
		Maschine m1 = new Maschine("beispielmaschine", 100, p1, pm);
		Maschine m2 = new Maschine("beispielmaschine2", 70, p1, pm);
		Maschinenmarkt b = new Maschinenmarkt();
		b.anbieten(new Angebot(m1, 3, 888));
		b.anbieten(new Angebot(m2, 1, 34));
		
		return b;
	}

}
