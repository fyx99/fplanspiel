package dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import fachkonzept.Produkt;

public class ProdukteGesamtDTO {

	public ProdukteGesamtDTO(Map<String, Integer> produktmatrix) {
	    for (Map.Entry<String, Integer> ein : produktmatrix.entrySet()) {
	    	produkte.add(new ProduktDTO((Produkt)Produkt.findeMarkteinheit(ein.getKey(), Produkt.class), ein.getValue()));
	    }
	}
	
	private List<ProduktDTO> produkte = new ArrayList<ProduktDTO>();

	public List<ProduktDTO> getProdukte() {
		return produkte;
	}
}
