package fachkonzept;

import fachkonzept.markt.Markteinheit;
import fachkonzept.util.MaterialArt;

public class Material extends Markteinheit{
	
	
	private int quality;
	private MaterialArt materialArt;
	
	public Material(int q, MaterialArt ma) {
		super();
		this.quality = q;
		this.setName(ma.name());
		this.materialArt = ma;
	}

	public int getQuality() {
		return quality;
	}

    public MaterialArt getMaterialArt() {
        return materialArt;
    }

    public void setMaterialArt(MaterialArt materialArt) {
        this.materialArt = materialArt;
    }
	
}