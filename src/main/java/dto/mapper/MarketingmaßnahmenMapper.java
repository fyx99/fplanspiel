package dto.mapper;

import java.util.List;
import java.util.stream.Collectors;

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
	
    public static List<MarketingmaßnahmeDTO> toDTO(List<Marketingmaßnahme> us){
    	return us.stream().map(MarketingmaßnahmenMapper::toDTO).collect(Collectors.toList());
    }

}
