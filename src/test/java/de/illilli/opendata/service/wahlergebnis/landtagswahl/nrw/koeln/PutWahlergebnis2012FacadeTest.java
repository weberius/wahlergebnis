package de.illilli.opendata.service.wahlergebnis.landtagswahl.nrw.koeln;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import de.illilli.opendata.service.Facade;

public class PutWahlergebnis2012FacadeTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGetJson() throws IOException {
		Facade facade = new PutWahlergebnis2012Facade();
		InputStream inputStream = this.getClass().getResourceAsStream("/landtagswahl.nrw.koeln.2012.put.json");
		String expected = IOUtils.toString(inputStream);
		String actual = facade.getJson();
		Assert.assertEquals(expected, actual);
	}

}
