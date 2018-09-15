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

import Fachkonzept.Spiel;
import Fachkonzept.Unternehmen;

@PermitAll
@Path("Spiel")
public class SpielService {


	public static Spiel spiel;
	
	@GET
	@Path("Ping")
	@Produces(MediaType.TEXT_PLAIN) // Application_Json
	public String getIt() {
		return "1";
	}
	
	
	@GET
	@Path("Neu")
	@Produces(MediaType.TEXT_PLAIN) // Application_Json
	public String neuesSpiel() {

		spiel = new Spiel();

		return "Ein neues Spiel wurde erstellt";
	}
	
	@GET
	@Path("Start")
	@Produces(MediaType.APPLICATION_JSON)
	public Unternehmen spielStart() {
		//
		return spiel.rundenStart();
	}
	
	@GET
	@Path("Quickstart")
	@Produces(MediaType.APPLICATION_JSON)
	public String qs() {
		spiel = new Spiel();
		if (spiel != null) {

			spiel.unternehmenHinzufuegen(new Unternehmen("ui", spiel));
			spiel.unternehmenHinzufuegen(new Unternehmen("uii", spiel));
		}

		spiel.rundenStart();
		return "Quickstart durchgeführt";
	}
	
	//Zug beendet
	@GET
	@Path("Zug/Ende")
	@Produces(MediaType.APPLICATION_JSON)
	public void zugBeendet() {
		//
		spiel.zugBeendet();

	}
	
	//Gesamtstatistik aller Spieler
	@GET
	@Path("Statistiken")
	@Produces(MediaType.TEXT_PLAIN)
	public String gameStats() {
		//
		return spiel.gameStatsHelper();

	}
	
}