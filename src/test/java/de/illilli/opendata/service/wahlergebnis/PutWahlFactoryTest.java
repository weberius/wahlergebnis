package de.illilli.opendata.service.wahlergebnis;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
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
	 * 
	 * @throws IOException
	 */
	@Test
	public void testForDefaultFacade() throws IOException {
		InputStream inputStream = JerseyClientPost.class.getResourceAsStream("/default.data.json");
		String data = IOUtils.toString(inputStream);
		Facade facade = PutWahlFacadeFactory.getFacade(data);
		String expected = Config.getProperty("wahlergebnis.put.default");
		String actual = facade.getJson();
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testForLandtagswahlNRWFacade() throws IOException {
		String data = IOUtils.toString(JerseyClientPost.class.getResourceAsStream("/data.json"));
		Facade facade = PutWahlFacadeFactory.getFacade(data);
		String expected = IOUtils
				.toString(JerseyClientPost.class.getResourceAsStream("/landtagswahl.nrw.koeln.2012.put.json"));
		String actual = facade.getJson();
		Assert.assertEquals(expected, actual);
	}

}
