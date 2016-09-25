package de.illilli.opendata.service.wahlergebnis;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import de.illilli.opendata.service.Config;
import de.illilli.opendata.service.Facade;

public class PutWahlFactoryTest {

	@Before
	public void setUp() throws Exception {
	}

	/**
	 * Dieset Test prüft, ob bei Eingabe eines 'falschen' Wertes die
	 * DefaultFacade verwendet wird.
	 */
	@Test
	public void testForDefaultFacade() {
		Facade facade = PutWahlFacadeFactory.getFacade("landtagswahl", "nrw", "berlin", 2012);
		String expected = Config.getProperty("wahlergebnis.put.default");
		String actual = facade.getJson();
		Assert.assertEquals(expected, actual);
	}

}
