package DTOs;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import Fachkonzept.Maschine;

public class MaschinenGesamtDTO {

	public MaschinenGesamtDTO(Map<String, Integer> maschinenmatrix) {
	    for (Map.Entry<String, Integer> ein : maschinenmatrix.entrySet()) {
	    	maschinen.add(new MaschinenDTO(Maschine.findeMaschine(ein.getKey()), ein.getValue()));
	    }
	}
	
	private List<MaschinenDTO> maschinen = new ArrayList<MaschinenDTO>();

	public List<MaschinenDTO> getMaschinen() {
		return maschinen;
	}
	
	
	

}
