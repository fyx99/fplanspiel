<<<<<<< HEAD:src/test/java/marketing/MarketingMixTest.java
package marketing;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fachkonzept.Spiel;
import fachkonzept.Unternehmen;
import fachkonzept.marketing.Fernsehwerbung;

class MarketingMixTest {

	Spiel s;
	Unternehmen u1;
	@BeforeEach
	void setUp() {
		s = new Spiel();
		u1 = new Unternehmen("name", s);
	}
	
	@Test
	void init() {
		Fernsehwerbung test1 = new Fernsehwerbung("Werbespot", 1420.544);
		u1.getMarketingmix().marketingBuchen(test1, u1);
		assertEquals(1420.544, u1.getMarketingmix().getMarketing().get(0).getBudget());
		assertEquals(55, u1.getMarketingmix().getMarketing().get(0).getEffektivitaet());
		assertEquals(16000, u1.getMarketingmix().getMarketing().get(0).getVolumen());
		
	}
}
=======
package marketing;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fachkonzept.Spiel;
import fachkonzept.Unternehmen;
import fachkonzept.marketing.Fernsehwerbung;

class MarketingMixTest {

	Spiel s;
	Unternehmen u1;
	@BeforeEach
	void setUp() {
		s = new Spiel();
		u1 = new Unternehmen("name", s);
	}
	
	@Test
	void init() {
		Fernsehwerbung test1 = new Fernsehwerbung("Werbespot", 1420.544);
		u1.getMarketingmix().marketingBuchen(test1, u1);
		assertEquals(1420.544, u1.getMarketingmix().getMarketing().get(0).getBudget());
		assertEquals(55, u1.getMarketingmix().getMarketing().get(0).getEffektivitaet());
		assertEquals(16000, u1.getMarketingmix().getMarketing().get(0).getVolumen());
		
	}
}
>>>>>>> da8ad11c1a25afc2f38818fef0a655ed8eb1bb06:src/test/java/marketing/MarketingMixTest.java
