package dto;

import fachkonzept.markt.Markt;
import fachkonzept.markt.Markteinheit;

public class AngebotDTO {

	private double preis;
	private Integer id;
	private Integer menge;
	private Markteinheit markteinheit;
	
	private Markt markttyp;

    public double getPreis() {
        return preis;
    }

    public void setPreis(double preis) {
        this.preis = preis;
    }

    public Integer getMenge() {
        return menge;
    }

    public void setMenge(Integer menge) {
        this.menge = menge;
    }

    public Markteinheit getMarkteinheit() {
        return markteinheit;
    }

    public void setMarkteinheit(Markteinheit markteinheit) {
        this.markteinheit = markteinheit;
    }

    public Markt getMarkttyp() {
        return markttyp;
    }

    public void setMarkttyp(Markt markttyp) {
        this.markttyp = markttyp;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    
	
	
}
