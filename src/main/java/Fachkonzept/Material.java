package Fachkonzept;

public abstract class Material extends Markteinheit{
	
	
	private int quality;
	
	
	public Material(int q) {
		super();
		this.quality = q;
	
	}


	public int getQuality() {
		return quality;
	}	
	
	
}