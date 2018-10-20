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
	
}
