package fachkonzept;

import fachkonzept.markt.Markteinheit;
import fachkonzept.util.MaterialArt;

public class Material extends Markteinheit{
	
	
	private MaterialArt materialArt;
	
	public Material(MaterialArt ma) {
		super();
		this.setName(ma.name());
		this.materialArt = ma;
	}
    public MaterialArt getMaterialArt() {
        return materialArt;
    }

    public void setMaterialArt(MaterialArt materialArt) {
        this.materialArt = materialArt;
    }
	
}