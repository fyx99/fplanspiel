package fachkonzept;

import fachkonzept.markt.Markteinheit;

public class Material extends Markteinheit{
	
	
	private int quality;
	
	public Material(int q, String name) {
		super();
		this.quality = q;
		this.setName(name);
	}

	public int getQuality() {
		return quality;
	}

	
	
}