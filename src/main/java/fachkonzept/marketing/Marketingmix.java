package fachkonzept.marketing;

import java.util.ArrayList;
import java.util.List;

import fachkonzept.Unternehmen;

public class Marketingmix {
    

    private List<Marketingmaßnahme> marketing = new ArrayList<Marketingmaßnahme>();
    private int marketingStaerke = 0;	//0-150; 150 entspricht +15% angebotsstärke
    
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
    
    
    public int getMarketingStaerke() {
        return marketingStaerke;
    }

    public void setMarketingStaerke(int marketingStaerke) {
        this.marketingStaerke = marketingStaerke;
    }

    public static void marketingBuchen(Marketingmaßnahme m, Unternehmen u) {
        //hier wird direkt bezahlt
        u.getMarketingmix().marketingHinzu(m);
        //u.kosten(m.getBudget(), "Marketingkosten");
        //abgerechnet wird in der simulation laufend
    }
    
    public List<Marketingmaßnahme> getMarketingType(String type){
        List<Marketingmaßnahme> ret = new ArrayList();
        for(Marketingmaßnahme m : marketing) {
            if(m.getClass().getName().equals(type))
                ret.add(m);
        }
        return ret;
    }

}
