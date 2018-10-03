/*package dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import fachkonzept.Material;

public class MaterialienGesamtDTO {

	public MaterialienGesamtDTO(Map<String, Integer> materialmatrix) {
	    for (Map.Entry<String, Integer> ein : materialmatrix.entrySet()) {
	    	materialien.add(new MaterialDTO((Material)Material.findeMarkteinheit(ein.getKey(), Material.class), ein.getValue()));
	    }
	}
	
	private List<MaterialDTO> materialien = new ArrayList<MaterialDTO>();

	public List<MaterialDTO> getMaterialien() {
		return materialien;
	}
	
	
	

}
*/