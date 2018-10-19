package dto.mapper;

import dto.MarketingmaßnahmeDTO;
import fachkonzept.marketing.Marketingmaßnahme;
import fachkonzept.util.MarketingmaßnahmenArt;

public class MarketingmaßnahmenMapper {

	public static MarketingmaßnahmeDTO toDTO (Marketingmaßnahme m) {
		MarketingmaßnahmeDTO mm = new MarketingmaßnahmeDTO();
		mm.setName(m.getName());
		mm.setBudget(m.getBudget());
		mm.setArt(MarketingmaßnahmenArt.valueOf(m.getClass().getSimpleName()));	
		mm.setId(m.getId());
		return mm;
	}

}
