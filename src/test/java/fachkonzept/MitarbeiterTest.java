package fachkonzept;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import fachkonzept.util.MitarbeiterFachgebiet;

public class MitarbeiterTest {
	
	@Test
	void testen() {
		Mitarbeiter m= new Mitarbeiter("test", 12.12, 8, MitarbeiterFachgebiet.MASCHINE);
		Arbeitskraft mm= new Arbeitskraft(400, m);
		mm.setAuslastung(400);
		mm.setM(m);
		assertEquals(400, mm.getAuslastung());
		m.setName("test");
		m.setLohnkosten(12.13);
		m.setMfg(MitarbeiterFachgebiet.VERTRIEB);
		m.setArbeitszeit(8);
		assertEquals("test", m.getName());
		assertEquals(12.13, m.getLohnkosten());
		assertEquals(8, m.getArbeitszeit());
		assertEquals(MitarbeiterFachgebiet.VERTRIEB, m.getMfg());
		Mitarbeiter.findeMitarbeiter("test");
		Mitarbeiter.findeMitarbeiter("");
	}
}
