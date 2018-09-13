package DTOs;

import Fachkonzept.Markt;
import Fachkonzept.Markteinheit;

public class AngebotDTO {

	private Float preis;
	private Integer id;
	private Integer menge;
	private Markteinheit markteinheit;
	
	private Markt markttyp;

    public Float getPreis() {
        return preis;
    }

    public void setPreis(Float preis) {
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

	
	
}
