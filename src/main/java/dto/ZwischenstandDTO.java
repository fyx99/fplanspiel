package dto;

import java.util.ArrayList;
import java.util.List;

import fachkonzept.Unternehmen;

public class ZwischenstandDTO {
    
    
    public ZwischenstandDTO(Integer runde, List<Unternehmen> unternehmen) {
        super();
        this.runde = runde;
        List<UnternehmenStatDTO> dtos = new ArrayList();
        
        for(Unternehmen u : unternehmen) {
            dtos.add(new UnternehmenStatDTO(u.getUmsatz(), u.getKapital(), u.getName(), u.getGuv().rundenErgebnis(), u.getVmarkt().getMarktanteile(u), u.getMitarbeiter().size()));
        }
        this.unternehmen = dtos;
    }

    private Integer runde;
    
    List<UnternehmenStatDTO> unternehmen = new ArrayList<UnternehmenStatDTO>();

    public List<UnternehmenStatDTO> getUnternehmen() {
        return unternehmen;
    }

    public void setUnternehmen(List<UnternehmenStatDTO> unternehmen) {
        this.unternehmen = unternehmen;
    }

    public Integer getRunde() {
        return runde;
    }

    public void setRunde(Integer runde) {
        this.runde = runde;
    }
    

}
