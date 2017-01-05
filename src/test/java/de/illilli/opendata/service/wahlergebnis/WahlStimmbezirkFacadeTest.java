package de.illilli.opendata.service.wahlergebnis;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.text.ParseException;

import javax.naming.NamingException;

import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Before;

import de.illilli.jdbc.ConnectionEnvironment;
import de.illilli.opendata.service.Facade;

public class WahlStimmbezirkFacadeTest {

	@Before
	public void setUp() throws Exception {
	}

	public static void main(String[] args) throws IOException, SQLException, NamingException, ParseException {

		ConnectionEnvironment.setUpConnectionForJndi();

		String wahl = Wahl.landtagswahl.name;
		String bundesland = Land.nrw.key;
		String gemeinde = Gemeinde.koeln.key;
		String datum = "2012-05-13";
		String art = StimmArt.erststimmen.name;
		String nr = "10101,10102,10103";

		Facade facade = new WahlStimmbezirkFacade(wahl, bundesland, gemeinde, datum, art, nr);
		InputStream inputStream = WahlStimmbezirkFacadeTest.class
				.getResourceAsStream("/landtagswahl.nrw.koeln.2012.json");
		String expected = IOUtils.toString(inputStream);
		String actual = facade.getJson();
		System.out.println(actual);
		Assert.assertEquals(expected, actual);
	}

}
