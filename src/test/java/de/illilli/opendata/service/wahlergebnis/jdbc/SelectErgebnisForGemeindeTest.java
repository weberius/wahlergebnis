package de.illilli.opendata.service.wahlergebnis.jdbc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import javax.naming.NamingException;

import org.junit.After;
import org.junit.Before;

import de.illilli.jdbc.ConnectionEnvironment;
import de.illilli.jdbc.ConnectionFactory;
import de.illilli.jdbc.Select;
import de.illilli.jdbc.SelectListDao;
import de.illilli.opendata.service.wahlergebnis.StimmArt;
import de.illilli.opendata.service.wahlergebnis.Wahl;

public class SelectErgebnisForGemeindeTest {

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
		Select<ErgebnisDTO> select = new SelectErgebnisForGemeinde(wahl, art, bundesland, gemeinde, datum);
		List<ErgebnisDTO> ergebnis = new SelectListDao<ErgebnisDTO>(select, conn).execute();
		for (ErgebnisDTO dto : ergebnis) {
			System.out.println(dto.toString());
		}
	}

}
