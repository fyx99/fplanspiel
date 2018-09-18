package DTOs;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import Fachkonzept.Produkt;

public class ProdukteGesamtDTO {

	public ProdukteGesamtDTO(Map<String, Integer> produktmatrix) {
	    for (Map.Entry<String, Integer> ein : produktmatrix.entrySet()) {
	    	produkte.add(new ProduktDTO(Produkt.findeProdukt(ein.getKey()), ein.getValue()));
	    }
	}
	
	private List<ProduktDTO> produkte = new ArrayList<ProduktDTO>();

	public List<ProduktDTO> getProdukte() {
		return produkte;
	}
}
