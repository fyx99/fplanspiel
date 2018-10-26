package dto.mapper;

import java.util.List;
import java.util.stream.Collectors;

import dto.MarketingmixDTO;
import dto.MaschinenGesamtDTO;
import dto.MitarbeiterGesamtDTO;
import dto.UnternehmenDTO;
import fachkonzept.Unternehmen;

public class UnternehmenMapper {

    public static UnternehmenDTO toDTO(Unternehmen u) {
		UnternehmenDTO uu = new UnternehmenDTO();
		uu.setName(u.getName());
		uu.setStandort("leer");
		uu.setBmarkt(MarktMapper.toDTO(u.getBmarkt()));
		uu.setVmarkt(MarktMapper.toDTO(u.getVmarkt()));
		uu.setMmarkt(MarktMapper.toDTO(u.getMmarkt()));
		uu.setMarketingmix(new MarketingmixDTO(u.getMarketingmix()));
		uu.setUmsatz(u.getUmsatz());
		uu.setKapital(u.getKapital());
		uu.setMaterialien(u.getMaterialien());
		uu.setMaschinen(new MaschinenGesamtDTO(u.getMaschinen()));
		uu.setProdukte(ProduktMapper.toDTO(u.getProdukte()));
		uu.setMitarbeiter(new MitarbeiterGesamtDTO(u.getMitarbeiter(), u.getMitarbeiterKapazitaeten()));
		uu.setVerbindlichkeiten(u.getVerbindlichkeiten());
		return uu;
	}
    
    public static List<UnternehmenDTO> toDTO(List<Unternehmen> us){
    	return us.stream().map(UnternehmenMapper::toDTO).collect(Collectors.toList());
    }

}
