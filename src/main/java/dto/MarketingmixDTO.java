package dto;

import java.util.List;

import fachkonzept.marketing.Marketingmaßnahme;
import fachkonzept.marketing.Marketingmix;

public class MarketingmixDTO {

	
	public MarketingmixDTO(Marketingmix mm) {
		this.marketing = mm.getMarketing();
	}
	private List<Marketingmaßnahme> marketing;

	public List<Marketingmaßnahme> getMarketing() {
		return marketing;
	}

	public void setMarketing(List<Marketingmaßnahme> marketing) {
		this.marketing = marketing;
	}

}