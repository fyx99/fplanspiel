package dto;

import java.util.List;
import java.util.Map;

import fachkonzept.Arbeitskraft;
import fachkonzept.util.MitarbeiterFachgebiet;

public class MitarbeiterGesamtDTO {
    
    public MitarbeiterGesamtDTO(List<Arbeitskraft> arbeiter, Map<MitarbeiterFachgebiet, Integer> kapazitaeten) {
        this.arbeiter = arbeiter;
        this.kapazitaeten = kapazitaeten;
    }
    private List<Arbeitskraft> arbeiter;
    private Map<MitarbeiterFachgebiet, Integer> kapazitaeten;
    public List<Arbeitskraft> getArbeiter() {
        return arbeiter;
    }
    public void setArbeiter(List<Arbeitskraft> arbeiter) {
        this.arbeiter = arbeiter;
    }
    public Map<MitarbeiterFachgebiet, Integer> getKapazitaeten() {
        return kapazitaeten;
    }
    public void setKapazitaeten(Map<MitarbeiterFachgebiet, Integer> kapazitaeten) {
        this.kapazitaeten = kapazitaeten;
    }
    
    
}
