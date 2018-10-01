package service;

import javax.annotation.security.PermitAll;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import dto.UnternehmenDTO;
import fachkonzept.Maschine;
import fachkonzept.Produkt;
import fachkonzept.Spiel;
import fachkonzept.Unternehmen;
import fachkonzept.markt.Markteinheit;



@PermitAll
@Path("Spiel/Unternehmen")
public class UnternehmenService {
	
	private static Spiel spiel = SpielService.spiel;
	
	//Neues Unternehmen zum Spiel hinzufügen
	@GET
	@Path("Neu/{name}")
	@Produces(MediaType.TEXT_PLAIN) // Application_Json
	public String neuesSpiel(@PathParam("name") String name) {

		if (spiel != null) {

			spiel.unternehmenHinzufuegen(new Unternehmen(name, spiel, "A"));
		}

		return "neues unternehmen mit name " + name;
	}
	
	//alle Unternehmen anzeigen
	@GET
	@Path("Alle")
	@Produces(MediaType.APPLICATION_JSON)
	public UnternehmenDTO u() {
		//
		if(spiel != null)
		return Unternehmen.getDTO((spiel.getAktuellesUnternehmen()));
		
		return null;

	}
	
	@GET
	@Path("Produktion/{menge}/{maschinenid}")
	@Produces(MediaType.APPLICATION_JSON)
	public Object produziere(@PathParam("menge") int menge, @PathParam("maschinenid") int id) {
		// erstmal ressourcen verbrauchen
		//dann produkte zum unternehmen hinzu
		Maschine m = (Maschine)Markteinheit.findeMarkteinheit(id);
		Produkt p = m.produziere(menge, spiel.getAktuellesUnternehmen());
		return p.getId() + "produziere " + menge + " von " + p.getName() + " in " + m.getName() 
				+ " deren auslastung " + m.getAuslastung() + " kapazität" + m.getKapazitaet();

	}
	
	//Alle Produkte des UN anzeigen
		@GET
		@Path("Inventar/Produkte")
		@Produces(MediaType.APPLICATION_JSON)
		public Object getProdukte() {

			return spiel.getAktuellesUnternehmen().zeigeProdukte();

		}
		
		//Alle Materialien des UN anzeigen
		@GET
		@Path("Inventar/Materialien")
		@Produces(MediaType.APPLICATION_JSON)
		public Object getMaterialien() {

			return spiel.getAktuellesUnternehmen().zeigeMaterialien();

		}
		
		//Alle Maschinen
		@GET
		@Path("Inventar/Maschinen")
		@Produces(MediaType.APPLICATION_JSON)
		public Object getMaschinen() {

			return spiel.getAktuellesUnternehmen().zeigeMaschinen();

		}

	
	
}