package fachkonzept.marketing;

import java.util.ArrayList;
import java.util.List;

import fachkonzept.Unternehmen;

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
    
    public static void marketingBuchen(Marketingmaßnahme m, Unternehmen u) {
        //hier wird direkt bezahlt
        u.getMarketingmix().marketingHinzu(m);
        u.kosten("Marketingkosten", m.getBudget());
    }

}
