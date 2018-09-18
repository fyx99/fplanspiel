package Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.security.PermitAll;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import DTOs.UnternehmenDTO;
import Fachkonzept.Angebot;
import Fachkonzept.Beschaffungsmarkt;
import Fachkonzept.Markteinheit;
import Fachkonzept.Maschine;
import Fachkonzept.Maschinenmarkt;
import Fachkonzept.Material;
import Fachkonzept.Produkt;
import Fachkonzept.Spiel;
import Fachkonzept.Unternehmen;
import Fachkonzept.Absatzmarkt;



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

			spiel.unternehmenHinzufuegen(new Unternehmen(name, spiel));
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
		return Unternehmen.getDTO((spiel.getNaechstesUnternehmen()));
		
		return null;

	}
	
	@GET
	@Path("Inventar")
	@Produces(MediaType.APPLICATION_JSON)
	public Object getBestand() {
		//
		Map<String, Integer> bestand = new HashMap<String, Integer>();
		bestand.putAll(spiel.getNaechstesUnternehmen().getMaschinen());
		bestand.putAll(spiel.getNaechstesUnternehmen().getMaterialien());
		bestand.putAll(spiel.getNaechstesUnternehmen().getProdukte());
		return bestand;

	}
	
	@GET
	@Path("Produktion/{menge}/{maschinenid}")
	@Produces(MediaType.APPLICATION_JSON)
	public Object produziere(@PathParam("menge") int menge, @PathParam("maschinenid") int id) {
		// erstmal ressourcen verbrauchen
		//dann produkte zum unternehmen hinzu
		Maschine m = (Maschine)Markteinheit.findeMarkteinheit(id);
		Produkt p = m.produziere(menge, spiel.getNaechstesUnternehmen());
		return p.getId() + "produziere " + menge + " von " + p.getName() + " in " + m.getName() 
				+ " deren auslastung " + m.getAuslastung() + " kapazität" + m.getKapazitaet();

	}
	
	//Alle Produkte des UN anzeigen
		@GET
		@Path("Inventar/Produkte")
		@Produces(MediaType.APPLICATION_JSON)
		public Object getProdukte() {

			return spiel.getNaechstesUnternehmen().zeigeProdukte();

		}
		
		//Alle Materialien des UN anzeigen
		@GET
		@Path("Inventar/Materialien")
		@Produces(MediaType.APPLICATION_JSON)
		public Object getMaterialien() {

			return spiel.getNaechstesUnternehmen().zeigeMaterialien();

		}
		
		//Alle Maschinen
		@GET
		@Path("Inventar/Maschinen")
		@Produces(MediaType.APPLICATION_JSON)
		public Object getMaschinen() {

			return spiel.getNaechstesUnternehmen().zeigeMaschinen();

		}

	
	
}