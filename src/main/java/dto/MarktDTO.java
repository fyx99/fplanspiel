package dto;

import java.util.ArrayList;
import java.util.List;

import fachkonzept.Angebot;

public class MarktDTO {

	private List<AngebotDTO> angebote;

	public List<AngebotDTO> getAngebote() {
		return angebote;
	}

	public void setAngebote(List<AngebotDTO> angebote) {
		this.angebote = angebote;
	}

}
