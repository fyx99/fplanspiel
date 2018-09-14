package DTOs;

import java.util.ArrayList;
import java.util.List;

import Fachkonzept.Angebot;

public class MarktDTO {


	public MarktDTO(List<Angebot> angebote) {
		this.angebote = angebote;
	}


	private List<Angebot> angebote = new ArrayList<Angebot>();
	



	public List<Angebot> getAngebote() {
		return angebote;
	}


	public void setAngebote(List<Angebot> angebote) {
		this.angebote = angebote;
	}

	

}
