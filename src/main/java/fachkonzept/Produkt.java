package fachkonzept;

import fachkonzept.markt.Markteinheit;
import fachkonzept.util.ProduktArt;
import fachkonzept.util.ProduktTyp;

public class Produkt extends Markteinheit{
	
	private ProduktArt produktArt;
	private ProduktTyp produktTyp;
	private double startPreis = 0;
	private double preisEntwicklung = 1;	//langfristige veränderung übers spiel
	
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

}