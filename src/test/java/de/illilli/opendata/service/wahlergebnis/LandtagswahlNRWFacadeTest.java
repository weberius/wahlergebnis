package de.illilli.opendata.service.wahlergebnis;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import de.illilli.opendata.service.Facade;

public class LandtagswahlNRWFacadeTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGetJson() throws IOException {
		Facade facade = new LandtagswahlNRWFacade(Gemeinde.koeln, 2012, 10101);
		InputStream inputStream = this.getClass().getResourceAsStream("/landtagswahl.nrw.koeln.2012.json");
		String expected = IOUtils.toString(inputStream);
		String actual = facade.getJson();
		Assert.assertEquals(expected, actual);
	}

}
