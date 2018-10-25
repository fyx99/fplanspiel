package fachkonzept;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.eclipse.jetty.server.UserIdentity.UnauthenticatedUserIdentity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fachkonzept.markt.Arbeitsmarkt;
import fachkonzept.markt.Beschaffungsmarkt;
import fachkonzept.markt.Finanzmarkt;
import fachkonzept.util.KreditArt;
import fachkonzept.util.MaterialArt;
import fachkonzept.util.MitarbeiterFachgebiet;
import fachkonzept.util.SimulationsKonstanten;
import fachkonzept.util.StandortArt;
import net.bytebuddy.description.annotation.AnnotationValue.ForEnumerationDescription.UnknownRuntimeEnumeration;

public class StandortTest {
	
	@Test
    void faktoren() {
		Standort standortNeutral = new Standort(SimulationsKonstanten.getStandortFaktoren(StandortArt.NEUTRAL));
		Standort standortA = new Standort(SimulationsKonstanten.getStandortFaktoren(StandortArt.A));
		Standort standortB = new Standort(SimulationsKonstanten.getStandortFaktoren(StandortArt.B));
		Standort standortC = new Standort(SimulationsKonstanten.getStandortFaktoren(StandortArt.C));
		Standort standortD = new Standort(SimulationsKonstanten.getStandortFaktoren(StandortArt.D));
		
		Unternehmen unNeutral = new Unternehmen("Test", new Spiel(), standortNeutral);
		Unternehmen unA = new Unternehmen("Test", new Spiel(), standortA);
		Unternehmen unB = new Unternehmen("Test", new Spiel(), standortB);
		Unternehmen unC = new Unternehmen("Test", new Spiel(), standortC);
		Unternehmen unD = new Unternehmen("Test", new Spiel(), standortD);
		
		
		assertEquals(1.0, unNeutral.getStandort().getMitarbeiterKosten());
		assertEquals(1.0, unNeutral.getStandort().getMaterialKosten());
		assertEquals(1.0, unNeutral.getStandort().getMarketingEinfluss());
		assertEquals(1.0, unNeutral.getStandort().getFertigungsKosten());
		
		assertEquals(0.8, unA.getStandort().getMitarbeiterKosten());
		assertEquals(1.0, unA.getStandort().getMaterialKosten());
		assertEquals(1.1, unA.getStandort().getMarketingEinfluss());
		assertEquals(1.0, unA.getStandort().getFertigungsKosten());
		
		assertEquals(1.0, unB.getStandort().getMitarbeiterKosten());
		assertEquals(0.8, unB.getStandort().getMaterialKosten());
		assertEquals(1.0, unB.getStandort().getMarketingEinfluss());
		assertEquals(1.1, unB.getStandort().getFertigungsKosten());
		
		assertEquals(1.1, unC.getStandort().getMitarbeiterKosten());
		assertEquals(1.0, unC.getStandort().getMaterialKosten());
		assertEquals(0.8, unC.getStandort().getMarketingEinfluss());
		assertEquals(1.0, unC.getStandort().getFertigungsKosten());
		
		assertEquals(1.0, unD.getStandort().getMitarbeiterKosten());
		assertEquals(1.1, unD.getStandort().getMaterialKosten());
		assertEquals(1.0, unD.getStandort().getMarketingEinfluss());
		assertEquals(0.8, unD.getStandort().getFertigungsKosten());
		
	}
}