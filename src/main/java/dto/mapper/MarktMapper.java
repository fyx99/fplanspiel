package dto.mapper;

import java.util.List;

import dto.MarktDTO;
import fachkonzept.Angebot;
import fachkonzept.markt.Markt;

public class MarktMapper {


    public static MarktDTO toDTO(Markt m) {
    	MarktDTO md = new MarktDTO();
    	md.setAngebote(AngebotMapper.toDTO(m.getAngebote()));
    	return md;
    }
}
