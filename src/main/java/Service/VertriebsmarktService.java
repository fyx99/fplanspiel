package Service;

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
import Fachkonzept.Verkaufsmarkt;



@PermitAll
@Path("Spiel/Unternehmen/Vertriebsmarkt")
public class VertriebsmarktService {
	
	private static Spiel spiel= SpielService.spiel;
	
	
	@POST
	@Path("Initialisierung")
	public void initVMarkt() {
		
		//Initilialisierung des Vertriebmarktes

	}
	
	
	@GET
	@Path("Angebote")
	@Produces(MediaType.APPLICATION_JSON)
	public Verkaufsmarkt getVMarkt() {
		//
		return spiel.getNaechstesUnternehmen().getVmarkt();

	}
	
	@GET
	@Path("Produkte/Verkauf/{menge}/{produktid}/{preis}")
	@Produces(MediaType.APPLICATION_JSON)
	public Object angebotErstellen(@PathParam("menge") int menge, @PathParam("produktid") int id, @PathParam("preis") int preis) {
		//hiermit kann ich alle dinge verkaufen
		Markteinheit m = Markteinheit.findeMarkteinheit(id);
		if(m instanceof Material) {
			Angebot a = new Angebot((Material)m, menge, preis);
			spiel.getNaechstesUnternehmen().getBmarkt().anbieten(a);
		}
		else if(m instanceof Maschine) {
			Angebot a = new Angebot((Maschine)m, menge, preis);
			spiel.getNaechstesUnternehmen().getMmarkt().anbieten(a);
		}
		else if(m instanceof Produkt){
			Angebot a = new Angebot((Produkt)m, menge, preis);
			spiel.getNaechstesUnternehmen().getVmarkt().anbieten(a);
		}
		
		return m.getName() + " angeboten " + menge + " stück für " + preis;

	}
	
	@GET
	@Path("Umsatzhistorie")
	@Produces(MediaType.APPLICATION_JSON)
	public Object getUmsatzHistorie() {

		return spiel.getNaechstesUnternehmen().getVmarkt().getUmsatzHistorie(spiel, 1);

	}
	
	@GET
	@Path("Angebote/Entfernung/{angebotsid}")
	@Produces(MediaType.APPLICATION_JSON)
	public Object angebotEntfernen(@PathParam("angebotsid") int id) {
		// angebot entfernen
		
		Angebot a = Angebot.findeAngebot(id);
		spiel.getNaechstesUnternehmen().getVmarkt().angebotEntfernen(a);
		
		
		return a.getId() + " entfernen ";

	}

	
	
}