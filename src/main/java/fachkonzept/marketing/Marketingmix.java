package fachkonzept.marketing;

import java.util.ArrayList;
import java.util.List;

public class Marketingmix {
    

    private List<Marketingmaßnahme> marketing = new ArrayList<Marketingmaßnahme>();
    
    public List<Marketingmaßnahme> getMarketing() {
        return marketing;
    }

    public void setMarketing(List<Marketingmaßnahme> marketing) {
        this.marketing = marketing;
    }
    
    public void marketingHinzu(Marketingmaßnahme m) {
        this.marketing.add(m);
    }
    
    public void marketingEntfernen(Marketingmaßnahme m) {
        this.marketing.remove(m);
    }

}
