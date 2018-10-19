package dto;

import java.util.List;

import fachkonzept.Spiel;
import fachkonzept.Unternehmen;

public class SpielDTO {

	public SpielDTO(Spiel s) {
		this.runde = s.getRunde();
		this.quartal = s.getRunde() % 4;
		this.jahr = s.getRunde() / 4;
		
	}
	private int runde;
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

	public int getRunde() {
		return runde;
	}

	public void setRunde(int runde) {
		this.runde = runde;
	}
	
	
}
