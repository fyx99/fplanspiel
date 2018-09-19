package dto;

import java.util.List;

import fachkonzept.Unternehmen;

public class SpielDTO {

	public SpielDTO() {
		// TODO Auto-generated constructor stub
	}
	
	private int quartal;
	private int jahr;
	
	List<Unternehmen> unternehmen;

    public int getQuartal() {
        return quartal;
    }

    public void setQuartal(int quartal) {
        this.quartal = quartal;
    }

    public int getJahr() {
        return jahr;
    }

    public void setJahr(int jahr) {
        this.jahr = jahr;
    }

    public List<Unternehmen> getUnternehmen() {
        return unternehmen;
    }

    public void setUnternehmen(List<Unternehmen> unternehmen) {
        this.unternehmen = unternehmen;
    }
	
	
}
