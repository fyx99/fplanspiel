package DTOs;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import Fachkonzept.Material;

public class MaterialienGesamtDTO {

	public MaterialienGesamtDTO(Map<String, Integer> maschinenmatrix) {
	    for (Map.Entry<String, Integer> ein : maschinenmatrix.entrySet()) {
	    	materialien.add(new MaterialDTO((Material)Material.findeMarkteinheit(ein.getKey(), Material.class), ein.getValue()));
	    }
	}
	
	private List<MaterialDTO> materialien = new ArrayList<MaterialDTO>();

	public List<MaterialDTO> getMaschinen() {
		return materialien;
	}
	
	
	

}
