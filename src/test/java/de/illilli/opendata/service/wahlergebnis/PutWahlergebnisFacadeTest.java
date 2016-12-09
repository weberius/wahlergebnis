package de.illilli.opendata.service.wahlergebnis;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import javax.naming.NamingException;

import org.apache.commons.io.IOUtils;
import org.junit.After;
import org.junit.Before;

import de.illilli.jdbc.ConnectionEnvironment;
import de.illilli.opendata.service.Facade;

public class PutWahlergebnisFacadeTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	public static void main(String[] args) throws IOException, ParseException, SQLException, NamingException {
		ConnectionEnvironment.setUpConnectionForJndi();
		String data = IOUtils
				.toString(PutWahlergebnisFacadeTest.class.getResourceAsStream("/landtagswahl2012erststimmen.json"));
		Facade facade = new PutWahlergebnisFacade(data);
		System.out.println(facade.getJson());
	}

}
