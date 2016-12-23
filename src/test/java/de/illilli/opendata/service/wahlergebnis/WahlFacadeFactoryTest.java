package de.illilli.opendata.service.wahlergebnis;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class WahlFacadeFactoryTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testStimmArtErststimmenExists() {
		boolean expected = true;
		boolean actual = WahlFacadeFactory.stimmArtExists("erststimmen");
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testStimmArtZweitstimmenExists() {
		boolean expected = true;
		boolean actual = WahlFacadeFactory.stimmArtExists("zweitstimmen");
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testStimmArtDoesNotExists() {
		boolean expected = false;
		boolean actual = WahlFacadeFactory.stimmArtExists("irgendwas");
		Assert.assertEquals(expected, actual);
	}

}
