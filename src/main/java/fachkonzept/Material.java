package fachkonzept;

import fachkonzept.markt.Markteinheit;
import fachkonzept.util.MaterialArt;

public class Material extends Markteinheit{
	
	
	private int quality;
	
	public Material(int q, MaterialArt name) {
		super();
		this.quality = q;
		this.setName(name);
	}

	public int getQuality() {
		return quality;
	}

	
	
}