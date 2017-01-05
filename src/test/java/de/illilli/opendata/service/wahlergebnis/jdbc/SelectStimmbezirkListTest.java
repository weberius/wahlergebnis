package de.illilli.opendata.service.wahlergebnis.jdbc;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import javax.naming.NamingException;

import org.junit.After;
import org.junit.Before;

import de.illilli.jdbc.ConnectionEnvironment;
import de.illilli.jdbc.Select;
import de.illilli.opendata.service.wahlergebnis.StimmArt;
import de.illilli.opendata.service.wahlergebnis.Wahl;

public class SelectStimmbezirkListTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	public static void main(String[] args) throws SQLException, NamingException, IOException, ParseException {
		ConnectionEnvironment.setUpConnectionForJndi();
		String datum = "2012-05-13";
		String bundesland = "05";
		String gemeinde = "05315000";
		String wahl = Wahl.landtagswahl.name;
		String art = StimmArt.erststimmen.name;
		String nr = "10101,10102,10103";
		Select<StimmbezirkDTO> select = new SelectStimmbezirkList(wahl, art, bundesland, gemeinde, datum, nr);
		List<StimmbezirkDTO> dtoList = select.getDbObjectList();
		for (StimmbezirkDTO dto : dtoList) {
			System.out.println(dto.toString());
		}
	}

}
