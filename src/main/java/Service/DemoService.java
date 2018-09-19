package Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.security.PermitAll;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import DTOs.UnternehmenDTO;
import Fachkonzept.Absatzmarkt;
import Fachkonzept.Angebot;
import Fachkonzept.Beschaffungsmarkt;
import Fachkonzept.Markteinheit;
import Fachkonzept.Maschine;
import Fachkonzept.Maschinenmarkt;
import Fachkonzept.Material;
import Fachkonzept.Produkt;
import Fachkonzept.Spiel;
import Fachkonzept.Unternehmen;

/**
 * Root resource (exposed at "myresource" path)
 */
@PermitAll
@Path("demo")
public class DemoService {

	/**
	 * Method handling HTTP GET requests. The returned object will be sent to the
	 * client as "text/plain" media type.
	 *
	 * @return String that will be returned as a text/plain response.
	 */

	private static Spiel s;

	@GET
	@Path("ping")
	@Produces(MediaType.TEXT_PLAIN) // Application_Json
	public String getIt() {
		return "1";
	}

	@GET
	@Path("neuesspiel")
	@Produces(MediaType.TEXT_PLAIN) // Application_Json
	public String neuesSpiel() {

		s = new Spiel();

		return "neues spiel erstellt";
	}

	@GET
	@Path("neuesunternehmen/{name}")
	@Produces(MediaType.TEXT_PLAIN) // Application_Json
	public String neuesSpiel(@PathParam("name") String name) {

		if (s != null) {

			s.unternehmenHinzufuegen(new Unternehmen(name, s));
		}

		return "neues unternehmen mit name " + name;
	}

	@GET
	@Path("spielstarten")
	@Produces(MediaType.APPLICATION_JSON)
	public Unternehmen spielStart() {
		//
		return s.rundenStart();
	}
	
	@GET
	@Path("quickstart")
	@Produces(MediaType.APPLICATION_JSON)
	public void qs() {
		s = new Spiel();
		if (s != null) {

			s.unternehmenHinzufuegen(new Unternehmen("ui", s));
			s.unternehmenHinzufuegen(new Unternehmen("uii", s));
		}

		s.rundenStart();
	}

	@GET
	@Path("zugbeendet")
	@Produces(MediaType.APPLICATION_JSON)
	public void zugBeendet() {
		//
		s.zugBeendet();

	}

	@GET
	@Path("stats")
	@Produces(MediaType.TEXT_PLAIN)
	public String gameStats() {
		//
		return s.gameStatsHelper();

	}
	@GET
	@Path("unternehmen")
	@Produces(MediaType.APPLICATION_JSON)
	public UnternehmenDTO u() {
		//
		if(s != null)
		return Unternehmen.getDTO((s.getNaechstesUnternehmen()));
		
		return null;

	}

	@GET
	@Path("bmarkt")
	@Produces(MediaType.APPLICATION_JSON)
	public Beschaffungsmarkt getBMarkt() {
		//
		return s.getNaechstesUnternehmen().getBmarkt();

	}

	@GET
	@Path("vmarkt")
	@Produces(MediaType.APPLICATION_JSON)
	public Absatzmarkt getVMarkt() {
		//
		return s.getNaechstesUnternehmen().getVmarkt();

	}

	@GET
	@Path("mmarkt")
	@Produces(MediaType.APPLICATION_JSON)
	public Maschinenmarkt getMMarkt() {
		//
		return s.getNaechstesUnternehmen().getMmarkt();

	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("setangebote")
	@Produces(MediaType.APPLICATION_JSON)
	public void setAngebote(String json) {
		List<Angebot> angebote = new Gson().fromJson(json, new TypeToken<List<Angebot>>() {/**/}.getType());
		//
		s.getNaechstesUnternehmen().getVmarkt().setAngebote(angebote);

	}

	@GET
	@Path("angebotkaufen/{menge}/{angebotsid}")
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
			s.getNaechstesUnternehmen().getMmarkt().kaufen(angebot, tatsaechlichemenge, s.getNaechstesUnternehmen());

		} else if (angebot.getMarkteinheit() instanceof Material) {

			s.getNaechstesUnternehmen().getBmarkt().kaufen(angebot, tatsaechlichemenge, s.getNaechstesUnternehmen());
		} else {
			// sollte nicht passieren
		}

		return "gekauft " + tatsaechlichemenge + " vomn " + angebot.getMarkteinheit().getClass().getName();

	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("demopost")
	@Produces(MediaType.APPLICATION_JSON)
	public Object kaufeAngebot(String json) {

		return "toll";

	}
	
	@GET
	@Path("bestand")
	@Produces(MediaType.APPLICATION_JSON)
	public Object getBestand() {
		//
		Map<String, Integer> bestand = new HashMap<String, Integer>();
		bestand.putAll(s.getNaechstesUnternehmen().getMaschinen());
		bestand.putAll(s.getNaechstesUnternehmen().getMaterialien());
		bestand.putAll(s.getNaechstesUnternehmen().getProdukte());
		return bestand;

	}
	
	@GET
	@Path("produziere/{menge}/{maschinenid}")
	@Produces(MediaType.APPLICATION_JSON)
	public Object produziere(@PathParam("menge") int menge, @PathParam("maschinenid") int id) {
		// erstmal ressourcen verbrauchen
		//dann produkte zum unternehmen hinzu
		Maschine m = (Maschine)Markteinheit.findeMarkteinheit(id);
		Produkt p = m.produziere(menge, s.getNaechstesUnternehmen());
		return p.getId() + "produziere " + menge + " von " + p.getName() + " in " + m.getName() 
				+ " deren auslastung " + m.getAuslastung() + " kapazität" + m.getKapazitaet();

	}
	
	
	@GET
	@Path("anbieten/{menge}/{produktid}/{preis}")
	@Produces(MediaType.APPLICATION_JSON)
	public Object angebotErstellen(@PathParam("menge") int menge, @PathParam("produktid") int id, @PathParam("preis") int preis) {
		//hiermit kann ich alle dinge verkaufen
		Markteinheit m = Markteinheit.findeMarkteinheit(id);
		if(m instanceof Material) {
			Angebot a = new Angebot((Material)m, menge, preis);
			s.getNaechstesUnternehmen().getBmarkt().anbieten(a);
		}
		else if(m instanceof Maschine) {
			Angebot a = new Angebot((Maschine)m, menge, preis);
			s.getNaechstesUnternehmen().getMmarkt().anbieten(a);
		}
		else if(m instanceof Produkt){
			Angebot a = new Angebot((Produkt)m, menge, preis);
			s.getNaechstesUnternehmen().getVmarkt().anbieten(a);
		}
		
		return m.getName() + " angeboten " + menge + " stück für " + preis;

	}
	@GET
	@Path("angebotentfernen/{angebotsid}")
	@Produces(MediaType.APPLICATION_JSON)
	public Object angebotEntfernen(@PathParam("angebotsid") int id) {
		// angebot entfernen
		
		Angebot a = Angebot.findeAngebot(id);
		s.getNaechstesUnternehmen().getVmarkt().angebotEntfernen(a);
		
		
		return a.getId() + " entfernen ";

	}
	
	
	//für den aktuellen spieler braucht man 
	//alle maschinen
	@GET
	@Path("maschinen")
	@Produces(MediaType.APPLICATION_JSON)
	public Object getMaschinen() {

		return s.getNaechstesUnternehmen().zeigeMaschinen();

	}
	
	//alle produkte
	@GET
	@Path("produkte")
	@Produces(MediaType.APPLICATION_JSON)
	public Object getProdukte() {

		return s.getNaechstesUnternehmen().zeigeProdukte();

	}
	
	//alle materialien
	@GET
	@Path("materialien")
	@Produces(MediaType.APPLICATION_JSON)
	public Object getMaterialien() {

		return s.getNaechstesUnternehmen().zeigeMaterialien();

	}
	
	
	@GET
	@Path("umsatzhistorievmarkt")
	@Produces(MediaType.APPLICATION_JSON)
	public Object getUmsatzHistorie() {

		return s.getNaechstesUnternehmen().getVmarkt().getUmsatzHistorie(s, 1);

	}
}
