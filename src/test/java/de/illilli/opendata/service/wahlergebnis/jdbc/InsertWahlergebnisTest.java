package de.illilli.opendata.service.wahlergebnis.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;

import javax.naming.NamingException;

import org.junit.After;
import org.junit.Before;

import de.illilli.jdbc.ConnectionEnvironment;
import de.illilli.opendata.service.wahlergebnis.model.Data2Wahldaten;
import de.illilli.opendata.service.wahlergebnis.model.Wahldaten;

public class InsertWahlergebnisTest {

	private Connection conn;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	public static void main(String[] args) throws ParseException, SQLException, NamingException, IOException {
		ConnectionEnvironment.setUpConnectionForJndi();
		InputStream data = Data2Wahldaten.class.getResourceAsStream("/data.json");
		Data2Wahldaten d2w = new Data2Wahldaten(data);
		Wahldaten wahldaten = d2w.getWahldaten();
		InsertWahlergebnis insert = new InsertWahlergebnis(wahldaten);
	}

}
