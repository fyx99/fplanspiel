package dto;

import fachkonzept.Material;

public class MaterialDTO {

	
	public MaterialDTO(Material p, int menge) {
		super();
		this.p = p;
		this.menge = menge;
	}
	private Material p;
	private int menge;
	public Material getP() {
		return p;
	}
	public void setP(Material p) {
		this.p = p;
	}
	public int getMenge() {
		return menge;
	}
	public void setMenge(int menge) {
		this.menge = menge;
	}
	
	
}
