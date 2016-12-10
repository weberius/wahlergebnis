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

public class LandtagswahlNRWFacadeTest {

	@Before
	public void setUp() throws Exception {
	}

	public static void main(String[] args) throws IOException, SQLException, NamingException, ParseException {

		ConnectionEnvironment.setUpConnectionForJndi();

		String datum = "2012-05-13";
		String bundesland = Land.nrw.key;
		String gemeinde = Gemeinde.koeln.key;
		int nr = 10101;

		Facade facade = new LandtagswahlNRWFacade(datum, bundesland, gemeinde, nr);
		InputStream inputStream = LandtagswahlNRWFacadeTest.class
				.getResourceAsStream("/landtagswahl.nrw.koeln.2012.json");
		String expected = IOUtils.toString(inputStream);
		String actual = facade.getJson();
		Assert.assertEquals(expected, actual);
	}

}
