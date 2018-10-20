package fachkonzept;

import fachkonzept.markt.Markteinheit;
import fachkonzept.util.ProduktArt;
import fachkonzept.util.ProduktTyp;

public class Produkt extends Markteinheit{
	
	private ProduktArt produktArt;
	private ProduktTyp produktTyp;

	
	public Produkt(ProduktArt name, ProduktTyp pt) {		
		super();
		this.setName(name.name());
		this.produktArt = name;
		this.produktTyp = pt;
	}

	
	public ProduktArt getProduktArt() {
		return produktArt;
	}

	public ProduktTyp getProduktTyp() {
		return produktTyp;
	}


}