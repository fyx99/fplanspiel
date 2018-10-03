package dto;

import java.util.ArrayList;
import java.util.List;

import fachkonzept.Angebot;

public class MarktDTO {


    public MarktDTO(List<Angebot> angebote) {
        for(Angebot aaa : angebote) {
            this.angebote.add(toDTO(aaa));
        }
    }

	private List<AngebotDTO> angebote = new ArrayList<AngebotDTO>();

	public List<AngebotDTO> getAngebote() {
		return angebote;
	}


	public void setAngebote(List<AngebotDTO> angebote) {
		this.angebote = angebote;
	}

	
	private AngebotDTO toDTO(Angebot an) {
		AngebotDTO a = new AngebotDTO();
		a.setId(an.getId());
		a.setMenge(an.getMenge());
		a.setMarkteinheit(an.getMarkteinheit());
		a.setPreis(an.getPreis());
		
		return a;
	}
	

}
