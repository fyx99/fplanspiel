package fachkonzept;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import fachkonzept.util.ProduktArt;
import fachkonzept.util.ProduktTyp;

public class ProduktTest {

	@Test
	void produktTyp() {
		Produkt m = new Produkt(ProduktArt.Holztisch, ProduktTyp.Tisch);
		assertEquals(ProduktArt.Holztisch, m.getProduktArt());
		assertEquals(ProduktTyp.Tisch, m.getProduktTyp());
	}
	
	@Test
	void startPreis() {
		Produkt m = new Produkt(ProduktArt.Holztisch, ProduktTyp.Tisch);
		m.getStartPreis();
		assertEquals(0, m.getStartPreis());
		
		m.setStartPreis(15);
		assertEquals(15,m.getStartPreis());
	}
	
	@Test
	void PreisEntwicklung() {
		Produkt m = new Produkt(ProduktArt.Holztisch, ProduktTyp.Tisch);
		m.setPreisEntwicklung(15);
		assertEquals(15, m.getPreisEntwicklung());
	}
}
