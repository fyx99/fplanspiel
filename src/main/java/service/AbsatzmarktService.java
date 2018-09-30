package service;

import javax.annotation.security.PermitAll;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import fachkonzept.Angebot;
import fachkonzept.Maschine;
import fachkonzept.Material;
import fachkonzept.Produkt;
import fachkonzept.Spiel;
import fachkonzept.markt.Absatzmarkt;
import fachkonzept.markt.Markteinheit;



@PermitAll
@Path("Spiel/Unternehmen/Vertriebsmarkt")
public class AbsatzmarktService {		//nein - entweder verkaufs - oder Absatzmarkt
	
	private static Spiel spiel= SpielService.spiel;
	
	@GET
	@Path("Angebote")
	@Produces(MediaType.APPLICATION_JSON)
	public Absatzmarkt getVMarkt() {
		//
		return spiel.getAktuellesUnternehmen().getVmarkt();

	}
	
	@GET
	@Path("Produkt/anbieten/{menge}/{produktid}/{preis}")
	@Produces(MediaType.APPLICATION_JSON)
	public Object angebotErstellen(@PathParam("menge") int menge, @PathParam("produktid") int id, @PathParam("preis") int preis) {
		//hiermit kann ich alle dinge verkaufen
		Markteinheit m = Markteinheit.findeMarkteinheit(id);
		if(m instanceof Material) {
			Angebot a = new Angebot((Material)m, menge, preis);
			spiel.getAktuellesUnternehmen().getBmarkt().anbieten(a);
		}
		else if(m instanceof Maschine) {
			Angebot a = new Angebot((Maschine)m, menge, preis);
			spiel.getAktuellesUnternehmen().getMmarkt().anbieten(a);
		}
		else if(m instanceof Produkt){
			Angebot a = new Angebot((Produkt)m, menge, preis);
			spiel.getAktuellesUnternehmen().getVmarkt().anbieten(a);
		}
		
		return m.getName() + " angeboten " + menge + " stück für " + preis;

	}
	
	@GET
	@Path("Umsatzhistorie")
	@Produces(MediaType.APPLICATION_JSON)
	public Object getUmsatzHistorie() {

		return spiel.getAktuellesUnternehmen().getVmarkt().getUmsatzHistorie(spiel, 1);

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