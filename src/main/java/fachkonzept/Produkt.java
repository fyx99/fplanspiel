package fachkonzept;

import fachkonzept.markt.Markteinheit;
import fachkonzept.util.ProduktArt;
import fachkonzept.util.ProduktTyp;

public class Produkt extends Markteinheit{
	
	private ProduktArt produktArt;
	private ProduktTyp produktTyp;
	private String name;
	private double startPreis = 0;
	private double preisEntwicklung = 1;	//langfristige veränderung übers spiel
	
	public Produkt(ProduktArt name, ProduktTyp pt) {		
		super();
		this.name = name.name();
		this.produktArt = name;
		this.produktTyp = pt;
	}

	public String getName() {
		return name;
	}
	
	public ProduktArt getProduktArt() {
		return produktArt;
	}

	public ProduktTyp getProduktTyp() {
		return produktTyp;
	}

	public double getStartPreis() {
		return startPreis;
	}

	public void setStartPreis(double startPreis) {
		this.startPreis = startPreis;
	}
	

	public double getPreisEntwicklung() {
        return preisEntwicklung;
    }

    public void setPreisEntwicklung(double preisEntwicklung) {
        this.preisEntwicklung = preisEntwicklung;
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