package fachkonzept.markt;

import java.util.ArrayList;
import java.util.List;

import fachkonzept.Angebot;
import fachkonzept.Material;
import fachkonzept.Umsatz;
import fachkonzept.Unternehmen;
import fachkonzept.util.MaterialArt;

public class Beschaffungsmarkt extends Markt {


	public Beschaffungsmarkt() {

	}

	@Override
	public void kaufen(Angebot a, int menge, Unternehmen k) {
		k.materialHinzu((Material) a.getMarkteinheit(), Math.min(a.getMenge(), menge));
		super.kaufen(a, menge, k);
	}

	public static List<Umsatz> umsatzProMaterialArt(MaterialArt a, int runde) {
		List<Umsatz> gefiltert = new ArrayList<Umsatz>();
		for(Umsatz u : Beschaffungsmarkt.getUmsatzHistorie()) {
			if(u.getAngebot().getMarkteinheit() instanceof Material && ((Material)u.getAngebot().getMarkteinheit()).getMaterialArt() == a && u.getRunde() == runde) {
				gefiltert.add(u);
			}
		}
		return gefiltert;

	}

	public static List<Umsatz> umsatzProMaterialArt(MaterialArt a) {
		List<Umsatz> gefiltert = new ArrayList();
		for(Umsatz u : Beschaffungsmarkt.getUmsatzHistorie()) {
			if(u.getAngebot().getMarkteinheit() instanceof Material && ((Material)u.getAngebot().getMarkteinheit()).getMaterialArt() == a) {
				gefiltert.add(u);
			}
		}
		return gefiltert;

	}
	

}
