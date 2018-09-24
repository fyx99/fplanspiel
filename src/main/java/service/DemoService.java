package service;

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

import dto.UnternehmenDTO;
import fachkonzept.Angebot;
import fachkonzept.Maschine;
import fachkonzept.Material;
import fachkonzept.Produkt;
import fachkonzept.Spiel;
import fachkonzept.Unternehmen;
import fachkonzept.marketing.Sponsoring;
import fachkonzept.markt.Markteinheit;

/**
 * Root resource (exposed at "myresource" path)
 */
@PermitAll
@Path("demo")
public class DemoService {

    /**
     * Method handling HTTP GET requests. The returned object will be sent to the client as "text/plain" media type.
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

        if(s != null) {

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
        if(s != null) {

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
            return Unternehmen.getDTO((s.getAktuellesUnternehmen()));

        return null;

    }

    @GET
    @Path("bmarkt")
    @Produces(MediaType.APPLICATION_JSON)
    public Object getBMarkt() {
        //
        return s.getAktuellesUnternehmen().getBmarkt();

    }

    @GET
    @Path("vmarkt")
    @Produces(MediaType.APPLICATION_JSON)
    public Object getVMarkt() {
        //
        return s.getAktuellesUnternehmen().getVmarkt();

    }

    @GET
    @Path("mmarkt")
    @Produces(MediaType.APPLICATION_JSON)
    public Object getMMarkt() {
        //
        return s.getAktuellesUnternehmen().getMmarkt();

    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("setangebote")
    @Produces(MediaType.APPLICATION_JSON)
    public void setAngebote(String json) {
        List<Angebot> angebote = new Gson().fromJson(json, new TypeToken<List<Angebot>>() {
            /**/}.getType());
        //
        s.getAktuellesUnternehmen().getVmarkt().setAngebote(angebote);

    }

    @GET
    @Path("angebotkaufen/{menge}/{angebotsid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Object kaufeAngebot(@PathParam("menge") int menge, @PathParam("angebotsid") int id) {
        // erstmal bezahlen

        Angebot angebot = Angebot.findeAngebot(id);
        if(angebot == null)
            return "kein anbgebot mit der id";
        // new Gson().fromJson(json, Angebot.class); convert json to java object
        int tatsaechlichemenge = menge;
        if(angebot.getMenge() < menge)
            tatsaechlichemenge = angebot.getMenge(); // maximal was angeboten wird

        if(angebot.getMarkteinheit() instanceof Maschine) {
            s.getAktuellesUnternehmen().getMmarkt().kaufen(angebot, tatsaechlichemenge, s.getAktuellesUnternehmen());

        } else if(angebot.getMarkteinheit() instanceof Material) {

            s.getAktuellesUnternehmen().getBmarkt().kaufen(angebot, tatsaechlichemenge, s.getAktuellesUnternehmen());
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
        bestand.putAll(s.getAktuellesUnternehmen().getMaschinen());
        bestand.putAll(s.getAktuellesUnternehmen().getMaterialien());
        bestand.putAll(s.getAktuellesUnternehmen().getProdukte());
        return bestand;

    }

    @GET
    @Path("produziere/{menge}/{maschinenid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Object produziere(@PathParam("menge") int menge, @PathParam("maschinenid") int id) {
        // erstmal ressourcen verbrauchen
        // dann produkte zum unternehmen hinzu
        Maschine m = (Maschine) Markteinheit.findeMarkteinheit(id);
        Produkt p = m.produziere(menge, s.getAktuellesUnternehmen());
        return p.getId() + "produziere " + menge + " von " + p.getName() + " in " + m.getName() + " deren auslastung " + m.getAuslastung() + " kapazität"
                + m.getKapazitaet();

    }

    @GET
    @Path("anbieten/{menge}/{produktid}/{preis}")
    @Produces(MediaType.APPLICATION_JSON)
    public Object angebotErstellen(@PathParam("menge") int menge, @PathParam("produktid") int id, @PathParam("preis") int preis) {
        // hiermit kann ich alle dinge verkaufen
        Markteinheit m = Markteinheit.findeMarkteinheit(id);
        if(m instanceof Material) {
            Angebot a = new Angebot((Material) m, menge, preis);
            s.getAktuellesUnternehmen().getBmarkt().anbieten(a);
        } else if(m instanceof Maschine) {
            Angebot a = new Angebot((Maschine) m, menge, preis);
            s.getAktuellesUnternehmen().getMmarkt().anbieten(a);
        } else if(m instanceof Produkt) {
            Angebot a = new Angebot((Produkt) m, menge, preis);
            s.getAktuellesUnternehmen().getVmarkt().anbieten(a);
        }

        //return m.getName() + " angeboten " + menge + " stück für " + preis;
        return null;
    }

    @GET
    @Path("angebotentfernen/{angebotsid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Object angebotEntfernen(@PathParam("angebotsid") int id) {
        // angebot entfernen

        Angebot a = Angebot.findeAngebot(id);
        s.getAktuellesUnternehmen().getVmarkt().angebotEntfernen(a);

        return a.getId() + " entfernen ";

    }

    // für den aktuellen spieler braucht man
    // alle maschinen
    @GET
    @Path("maschinen")
    @Produces(MediaType.APPLICATION_JSON)
    public Object getMaschinen() {

        return s.getAktuellesUnternehmen().zeigeMaschinen();

    }

    // alle produkte
    @GET
    @Path("produkte")
    @Produces(MediaType.APPLICATION_JSON)
    public Object getProdukte() {

        return s.getAktuellesUnternehmen().zeigeProdukte();

    }

    // alle materialien
    @GET
    @Path("materialien")
    @Produces(MediaType.APPLICATION_JSON)
    public Object getMaterialien() {

        return s.getAktuellesUnternehmen().zeigeMaterialien();

    }

    @GET
    @Path("umsatzhistorievmarkt")
    @Produces(MediaType.APPLICATION_JSON)
    public Object getUmsatzHistorie() {

        return s.getAktuellesUnternehmen().getVmarkt().getUmsatzHistorie(s, 1);

    }

    @GET
    @Path("marketingmix")
    @Produces(MediaType.APPLICATION_JSON)
    public Object getMarketingmix() {

        return s.getAktuellesUnternehmen().getMarketingmix();

    }
    
    @GET
    @Path("sponsoring/{budget}/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Object erstelleSponsoring(@PathParam("budget") double b, @PathParam("name") String name) {
        // angebot entfernen

        s.getAktuellesUnternehmen().getMarketingmix().marketingHinzu(new Sponsoring(name, b));
        
        return s.getAktuellesUnternehmen().getMarketingmix();

    }
    
    @GET
    @Path("log")
    @Produces(MediaType.APPLICATION_JSON)
    public Object log() {
        
        return s.getLog();

    }
}
