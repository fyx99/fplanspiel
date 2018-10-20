
package dto.mapper;

import java.util.List;
import java.util.stream.Collectors;

import dto.AngebotDTO;
import fachkonzept.Angebot;

public class AngebotMapper {

	public static AngebotDTO toDTO(Angebot an) {
		AngebotDTO a = new AngebotDTO();
		a.setId(an.getId());
		a.setMenge(an.getMenge());
		a.setMarkteinheit(an.getMarkteinheit());
		a.setPreis(an.getPreis());
		return a;
	}
	
	public static List<AngebotDTO> toDTO(List<Angebot> an) {
		return an.stream().map(AngebotMapper::toDTO).collect(Collectors.toList());
	}

}
