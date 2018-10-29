package fachkonzept;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fachkonzept.util.MitarbeiterFachgebiet;
import fachkonzept.util.SimulationsKonstanten;
import fachkonzept.util.StandortArt;

class UnternehmenTest {
	Unternehmen testUnternehmen;

	@BeforeEach
	void setUp() {
		Spiel s = new Spiel();
		s.unternehmenHinzufuegen(testUnternehmen = new Unternehmen("TestU", s, new Standort(SimulationsKonstanten.getStandortFaktoren(StandortArt.NEUTRAL))));
		s.rundenStart(); // -> anfangs simulation triggern

		testUnternehmen.arbeitskraftHinzu(
				new Arbeitskraft(new Mitarbeiter("test na2me", 3333, 2002, MitarbeiterFachgebiet.MASCHINE)));
		testUnternehmen.arbeitskraftHinzu(
				new Arbeitskraft(new Mitarbeiter("test name", 7000, 20000, MitarbeiterFachgebiet.MASCHINE)));
		testUnternehmen.arbeitskraftHinzu(
				new Arbeitskraft(0, new Mitarbeiter("test nam3e", 4000, 7000, MitarbeiterFachgebiet.VERWALTUNG)));
		testUnternehmen.arbeitskraftHinzu(
				new Arbeitskraft(4000, new Mitarbeiter("test nam4e", 7030, 30000, MitarbeiterFachgebiet.VERTRIEB)));
	}

	@Test
	void unternehmenErstellen() {
		Unternehmen u = new Unternehmen("Test  Name", new Spiel(), new Standort(SimulationsKonstanten.getStandortFaktoren(StandortArt.NEUTRAL)));
		Unternehmen u2 = new Unternehmen("Testtest", new Spiel(), new Standort(SimulationsKonstanten.getStandortFaktoren(StandortArt.NEUTRAL)));
		u.setKapital(10000.99);

		assertEquals("Test  Name", u.getName());
		assertEquals(10000.99, u.getKapital());
	}

	@Test
	void verringKapital() {
		testUnternehmen.setKapital(1000.88);
		assertEquals(1000.88, testUnternehmen.getKapital());

		testUnternehmen.kosten(250.48, "..s-.Test Beschreibung");
		assertEquals(750.40, testUnternehmen.getKapital());
		assertNotNull(testUnternehmen.getGuv());
		assertNotNull(testUnternehmen.getGuv().getAusgaben());

		assertEquals(1, testUnternehmen.getGuv().getAusgaben().size());
		assertEquals(250.48, testUnternehmen.getGuv().getAusgaben().get(0).getSumme());
		assertEquals("..s-.Test Beschreibung", testUnternehmen.getGuv().getAusgaben().get(0).getBeschreibung());

	}

	@Test
	void erhoeheKapital() {
		testUnternehmen.setKapital(2000.88);
		assertEquals(2000.88, testUnternehmen.getKapital());

		testUnternehmen.umsatz(250.04, "..s-.Test Beschreibung22");
		assertEquals(2250.92, testUnternehmen.getKapital());
		assertNotNull(testUnternehmen.getGuv());
		assertNotNull(testUnternehmen.getGuv().getEinnahmen());

		assertEquals(1, testUnternehmen.getGuv().getEinnahmen().size());
		assertEquals(250.04, testUnternehmen.getGuv().getEinnahmen().get(0).getSumme());
		assertEquals("..s-.Test Beschreibung22", testUnternehmen.getGuv().getEinnahmen().get(0).getBeschreibung());

		testUnternehmen.umsatz(1.00000, "1€");

		assertEquals(2, testUnternehmen.getGuv().getEinnahmen().size());
		assertEquals(1, testUnternehmen.getGuv().getEinnahmen().get(1).getSumme());
		assertEquals("1€", testUnternehmen.getGuv().getEinnahmen().get(1).getBeschreibung());
	}

	@Test
	void mitarbeiterKapazitaeten() {

		assertEquals(4, testUnternehmen.getMitarbeiter().size());
		assertEquals(7000, testUnternehmen.getMitarbeiterKapazitaeten(MitarbeiterFachgebiet.VERWALTUNG));
		assertEquals(22002, testUnternehmen.getMitarbeiterKapazitaeten(MitarbeiterFachgebiet.MASCHINE));
		assertEquals(26000, testUnternehmen.getMitarbeiterKapazitaeten(MitarbeiterFachgebiet.VERTRIEB));

		assertEquals(3, testUnternehmen.getMitarbeiterKapazitaeten().size());
		assertEquals(7000, (int) testUnternehmen.getMitarbeiterKapazitaeten().get(MitarbeiterFachgebiet.VERWALTUNG));
		assertEquals(22002, (int) testUnternehmen.getMitarbeiterKapazitaeten().get(MitarbeiterFachgebiet.MASCHINE));
		assertEquals(Integer.valueOf(26000),
				testUnternehmen.getMitarbeiterKapazitaeten().get(MitarbeiterFachgebiet.VERTRIEB));

	}

	@Test
	void beschaeftigeMitarbeiter() {

		testUnternehmen.beschaeftigeMitarbeiter(MitarbeiterFachgebiet.MASCHINE, 500);
		assertEquals(21502, testUnternehmen.getMitarbeiterKapazitaeten(MitarbeiterFachgebiet.MASCHINE));
		assertEquals(4, testUnternehmen.getMitarbeiter().size());
		// sollen gleich bleiben
		assertEquals(7000, testUnternehmen.getMitarbeiterKapazitaeten(MitarbeiterFachgebiet.VERWALTUNG));
		assertEquals(Integer.valueOf(26000),
				testUnternehmen.getMitarbeiterKapazitaeten().get(MitarbeiterFachgebiet.VERTRIEB));

		testUnternehmen.beschaeftigeMitarbeiter(MitarbeiterFachgebiet.MASCHINE, 1502);
		assertEquals(20000, testUnternehmen.getMitarbeiterKapazitaeten(MitarbeiterFachgebiet.MASCHINE));

		testUnternehmen.beschaeftigeMitarbeiter(MitarbeiterFachgebiet.MASCHINE, 0);
		assertEquals(20000, testUnternehmen.getMitarbeiterKapazitaeten(MitarbeiterFachgebiet.MASCHINE));

		testUnternehmen.beschaeftigeMitarbeiter(MitarbeiterFachgebiet.MASCHINE, 1);
		assertEquals(19999, testUnternehmen.getMitarbeiterKapazitaeten(MitarbeiterFachgebiet.MASCHINE));

		assertTrue(!testUnternehmen.beschaeftigeMitarbeiter(MitarbeiterFachgebiet.MASCHINE, 20000));
		assertEquals(0, testUnternehmen.getMitarbeiterKapazitaeten(MitarbeiterFachgebiet.MASCHINE));
	}

	@Test
	void umsatz() {
		setUp();
		testUnternehmen.setKapital(1000);
		testUnternehmen.umsatz(77.885, "Test umsatz");
		assertEquals(77.885, testUnternehmen.getUmsatz());
		assertEquals(1077.885, testUnternehmen.getKapital());
		assertEquals(1, testUnternehmen.getGuv().getEinnahmen().size());
		assertEquals("Test umsatz", testUnternehmen.getGuv().getEinnahmen().get(0).getBeschreibung());
		assertEquals(77.885, testUnternehmen.getGuv().getEinnahmen().get(0).getSumme());
		assertEquals(1, testUnternehmen.getGuv().getEinnahmen().get(0).getRunde());

		testUnternehmen.umsatz(0.5, "Test umsatz2");
		testUnternehmen.umsatz(1154284.885, "Test umsatz3");
		assertEquals(77.885 + 0.5 + 1154284.885, testUnternehmen.getUmsatz());
		assertEquals(1000 + 77.885 + 0.5 + 1154284.885, testUnternehmen.getKapital());
		assertEquals(3, testUnternehmen.getGuv().getEinnahmen().size());
		assertEquals("Test umsatz2", testUnternehmen.getGuv().getEinnahmen().get(1).getBeschreibung());
		assertEquals(0.5, testUnternehmen.getGuv().getEinnahmen().get(1).getSumme());
		assertEquals("Test umsatz3", testUnternehmen.getGuv().getEinnahmen().get(2).getBeschreibung());
		assertEquals(1154284.885, testUnternehmen.getGuv().getEinnahmen().get(2).getSumme());

		assertEquals(77.885 + 0.5 + 1154284.885, testUnternehmen.getGuv().rundenErgebnis());
	}

	@Test
	void kosten() {

		testUnternehmen.setKapital(1000);
		testUnternehmen.kosten(77, "Test kosten");
		assertEquals(923, testUnternehmen.getKapital());
		assertEquals(1, testUnternehmen.getGuv().getAusgaben().size());
		assertEquals("Test kosten", testUnternehmen.getGuv().getAusgaben().get(0).getBeschreibung());
		assertEquals(77, testUnternehmen.getGuv().getAusgaben().get(0).getSumme());
		assertEquals(1, testUnternehmen.getGuv().getAusgaben().get(0).getRunde());

		testUnternehmen.kosten(0.5, "Test kosten2");
		testUnternehmen.kosten(37, "Test kosten3");
		assertEquals(1000 - 77 - 37 - 0.5, testUnternehmen.getKapital());
		assertEquals(3, testUnternehmen.getGuv().getAusgaben().size());
		assertEquals("Test kosten2", testUnternehmen.getGuv().getAusgaben().get(1).getBeschreibung());
		assertEquals(0.5, testUnternehmen.getGuv().getAusgaben().get(1).getSumme());
		assertEquals("Test kosten3", testUnternehmen.getGuv().getAusgaben().get(2).getBeschreibung());
		assertEquals(37, testUnternehmen.getGuv().getAusgaben().get(2).getSumme());

		assertEquals(-114.5, testUnternehmen.getGuv().rundenErgebnis());

		assertEquals(-114.5, testUnternehmen.getGuv().rundenErgebnis(1));
		assertEquals(0, testUnternehmen.getGuv().rundenErgebnis(2));

	}
	

}
