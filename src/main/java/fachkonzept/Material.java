package fachkonzept;

import fachkonzept.markt.Markteinheit;

public class Material extends Markteinheit{
	
	
	private int quality;
	
	private String name;
	
	
	public Material(int q, String name) {
		super();
		this.quality = q;
		this.name = name;
	
	}


	public int getQuality() {
		return quality;
	}


	public String getName() {
		return name;
	}
	
	
}