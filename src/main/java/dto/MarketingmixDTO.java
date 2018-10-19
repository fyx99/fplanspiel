package dto;

import java.util.ArrayList;
import java.util.List;

import dto.mapper.MarketingmaßnahmenMapper;
import fachkonzept.marketing.Marketingmaßnahme;
import fachkonzept.marketing.Marketingmix;

public class MarketingmixDTO {

	
	public MarketingmixDTO(Marketingmix mm) {
		List<MarketingmaßnahmeDTO> list = new ArrayList<>();
		for(Marketingmaßnahme m : mm.getMarketing()) {
			list.add(MarketingmaßnahmenMapper.toDTO(m));
		}
		this.marketing = list;
	}
	private List<MarketingmaßnahmeDTO> marketing;
	
	
	public List<MarketingmaßnahmeDTO> getMarketing() {
		return marketing;
	}
	public void setMarketing(List<MarketingmaßnahmeDTO> marketing) {
		this.marketing = marketing;
	}

	

}