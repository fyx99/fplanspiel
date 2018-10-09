package dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import fachkonzept.Produkt;

public class ProdukteGesamtDTO {

	public ProdukteGesamtDTO(Map<Produkt, Integer> produktmatrix) {
	    for (Map.Entry<Produkt, Integer> ein : produktmatrix.entrySet()) {
	    	produkte.add(new ProduktDTO(ein.getKey(), ein.getValue()));
	    }
	}
	
	private List<ProduktDTO> produkte = new ArrayList<ProduktDTO>();

	public List<ProduktDTO> getProdukte() {
		return produkte;
	}
}
