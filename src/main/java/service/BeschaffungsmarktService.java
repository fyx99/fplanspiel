package service;

import javax.annotation.security.PermitAll;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import fachkonzept.Angebot;
import fachkonzept.Maschine;
import fachkonzept.Material;
import fachkonzept.Spiel;
import fachkonzept.markt.Beschaffungsmarkt;



@PermitAll
@Path("Spiel/Unternehmen/Beschaffungsmarkt")
public class BeschaffungsmarktService {
	
	private static Spiel spiel= SpielService.spiel;
	
	
	@POST
	@Path("Initialisierung")
	public void initBMarkt() {
		
		//Initilialisierung des Beschaffungmarktes

	}
	
	
	@GET
	@Path("Angebote")
	@Produces(MediaType.APPLICATION_JSON)
	public Beschaffungsmarkt getBMarkt() {
		//
		return spiel.getAktuellesUnternehmen().getBmarkt();

	}
	
	@GET
	@Path("Angebot/kaufen/{menge}/{angebotsid}")
	@Produces(MediaType.APPLICATION_JSON)
	public Object kaufeAngebot(@PathParam("menge") int menge, @PathParam("angebotsid") int id) {
		// erstmal bezahlen
		
		Angebot angebot = Angebot.findeAngebot(id);
		if(angebot == null)
			return "kein anbgebot mit der id";
		//new Gson().fromJson(json, Angebot.class);  convert json to java object
		int tatsaechlichemenge = menge;
		if (angebot.getMenge() < menge)
		    tatsaechlichemenge = angebot.getMenge(); // maximal was angeboten wird

		if (angebot.getMarkteinheit() instanceof Maschine) {
			spiel.getAktuellesUnternehmen().getMmarkt().kaufen(angebot, tatsaechlichemenge, spiel.getAktuellesUnternehmen());

		} else if (angebot.getMarkteinheit() instanceof Material) {

			spiel.getAktuellesUnternehmen().getBmarkt().kaufen(angebot, tatsaechlichemenge, spiel.getAktuellesUnternehmen());
		} else {
			// sollte nicht passieren
		}

		return "gekauft " + tatsaechlichemenge + " vomn " + angebot.getMarkteinheit().getClass().getName();

	}
	
	@GET
	@Path("Angebot/entfernen/{angebotsid}")
	@Produces(MediaType.APPLICATION_JSON)
	public Object angebotEntfernen(@PathParam("angebotsid") int id) {
		// angebot entfernen
		
		Angebot a = Angebot.findeAngebot(id);
		spiel.getAktuellesUnternehmen().getVmarkt().angebotEntfernen(a);
		
		
		return a.getId() + " entfernen ";

	}

	
}