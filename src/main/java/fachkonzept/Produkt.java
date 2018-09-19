package fachkonzept;

import fachkonzept.markt.Markteinheit;

public class Produkt extends Markteinheit{
	
	public String name;
	
	
	
	public Produkt(String name) {		
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	
	public static Produkt findeProdukt(String name) {
		for(Markteinheit m : Markteinheit.alleMarkteinheiten) {
			if(m instanceof Produkt && ((Produkt) m).getName() == name) {
				return (Produkt)m;
			}
		}
		return null;
		
	}
	
}