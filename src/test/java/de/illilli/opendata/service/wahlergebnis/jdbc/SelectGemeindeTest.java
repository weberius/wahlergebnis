package de.illilli.opendata.service.wahlergebnis.jdbc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;

import javax.naming.NamingException;

import org.junit.After;
import org.junit.Before;

import de.illilli.jdbc.ConnectionEnvironment;
import de.illilli.jdbc.ConnectionFactory;
import de.illilli.jdbc.Select;
import de.illilli.jdbc.SelectDao;
import de.illilli.opendata.service.wahlergebnis.StimmArt;
import de.illilli.opendata.service.wahlergebnis.Wahl;

public class SelectGemeindeTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	public static void main(String[] args) throws SQLException, NamingException, IOException, ParseException {
		ConnectionEnvironment.setUpConnectionForJndi();
		Connection conn = ConnectionFactory.getConnection();

		String datum = "2012-05-13";
		String bundesland = "05";
		String gemeinde = "05315000";
		String wahl = Wahl.landtagswahl.name;
		String art = StimmArt.erststimmen.name;
		Select<GemeindeDTO> select = new SelectGemeinde(wahl, art, bundesland, gemeinde, datum);
		GemeindeDTO dto = new SelectDao<GemeindeDTO>(select, conn).execute();
		System.out.println(dto.toString());
	}

}
