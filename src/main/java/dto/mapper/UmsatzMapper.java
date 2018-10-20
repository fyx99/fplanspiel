package dto.mapper;

import java.util.List;
import java.util.stream.Collectors;

import dto.UmsatzDTO;
import fachkonzept.Umsatz;

public class UmsatzMapper {

	public static UmsatzDTO toDTO(Umsatz umsatz) {
		UmsatzDTO um = new UmsatzDTO();
		um.setAngebot(AngebotMapper.toDTO(umsatz.getAngebot()));
		um.setMenge(umsatz.getMenge());
		um.setRunde(umsatz.getRunde());
		
		if (umsatz.getVerkaeufer()!=null) {
			um.setVerkaeufer(umsatz.getVerkaeufer().getName());
		}
		else {
			um.setVerkaeufer("Simulation");
		}

		return um;
	}

	public static List<UmsatzDTO> toDTO(List<Umsatz> um) {
		return um.stream().map(UmsatzMapper::toDTO).collect(Collectors.toList());
	}

}
