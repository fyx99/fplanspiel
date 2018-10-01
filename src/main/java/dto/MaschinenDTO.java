package dto;

import fachkonzept.Maschine;
import fachkonzept.Produkt;
import fachkonzept.Produktionsmatrix;

public class MaschinenDTO {

	private String name;
	private int kapazitaet;
	private int auslastung;
	private int id;

	private Produkt p;
	

	private Produktionsmatrix matrix;
	
	public MaschinenDTO(Maschine m) {
		//sollte in einen extra mapper
		name = m.getName();
		kapazitaet = m.getKapazitaet();
		auslastung = m.getAuslastung();
		p = m.getP();
		matrix = m.getMatrix();
		this.id = m.getId();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getKapazitaet() {
		return kapazitaet;
	}

	public void setKapazitaet(int kapazitaet) {
		this.kapazitaet = kapazitaet;
	}

	public int getAuslastung() {
		return auslastung;
	}

	public void setAuslastung(int auslastung) {
		this.auslastung = auslastung;
	}

	public Produkt getP() {
		return p;
	}

	public void setP(Produkt p) {
		this.p = p;
	}

	public Produktionsmatrix getMatrix() {
		return matrix;
	}

	public void setMatrix(Produktionsmatrix matrix) {
		this.matrix = matrix;
	}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
	
	
	
}
