package dto;

import java.util.ArrayList;
import java.util.List;

import fachkonzept.Maschine;

public class MaschinenGesamtDTO {

	public MaschinenGesamtDTO(List<Maschine> maschinen) {
	    for(Maschine m : maschinen)
	        this.maschinen.add(new MaschinenDTO(m));
	}
	
	private List<MaschinenDTO> maschinen = new ArrayList<MaschinenDTO>();

	public List<MaschinenDTO> getMaschinen() {
		return maschinen;
	}
	
	
	

}
