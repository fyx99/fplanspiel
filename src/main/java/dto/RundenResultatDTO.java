package dto;

import fachkonzept.GuV;
import fachkonzept.Unternehmen;

public class RundenResultatDTO {
    
    private String name;    //name des unternehmens
    private double kapital; //wv geld
    private GuV guv;
    
    
    //etwas zum absatzmarkt
    
    //evt sowas wie 7 neue angebote auf dem markt für material ...
    
    //das wurde mit der menge verkauft für den und den preis
    
    //kostenaufstellung
    
    
    public RundenResultatDTO(Unternehmen u) {
        
        this.name = u.getName();
        this.kapital = u.getKapital();
        this.guv = u.getGuv();
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public double getKapital() {
        return kapital;
    }
    public void setKapital(double kapital) {
        this.kapital = kapital;
    }
    public GuV getGuv() {
        return guv;
    }
    public void setGuv(GuV guv) {
        this.guv = guv;
    }



}
