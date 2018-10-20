package dto.mapper;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import dto.ProduktDTO;
import fachkonzept.Produkt;

public class ProduktMapper {

	public static ProduktDTO toDTO(Produkt p, int menge) {
		ProduktDTO pp = new ProduktDTO(p, menge);
		return pp;
	}
	
	
	public static List<ProduktDTO> toDTO(Map<Produkt, Integer> us){
		
//		List<ProduktDTO> list = new ArrayList<>();
//		for(Entry<Produkt, Integer> e : us.entrySet()) {
//			list.add(toDTO(e.getKey(), e.getValue()));
//		} 
		
		return us.entrySet().stream().map(e -> toDTO(e.getKey(),e.getValue())).collect(Collectors.toList());
    }

}