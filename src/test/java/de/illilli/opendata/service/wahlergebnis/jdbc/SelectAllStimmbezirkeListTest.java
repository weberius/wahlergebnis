package de.illilli.opendata.service.wahlergebnis.jdbc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import javax.naming.NamingException;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;

import de.illilli.jdbc.ConnectionEnvironment;
import de.illilli.jdbc.ConnectionFactory;
import de.illilli.jdbc.Select;
import de.illilli.jdbc.SelectListDao;
import de.illilli.opendata.service.wahlergebnis.StimmArt;
import de.illilli.opendata.service.wahlergebnis.Wahl;

public class SelectAllStimmbezirkeListTest {

	private static final Logger logger = Logger.getLogger(SelectAllStimmbezirkeListTest.class);

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	public static void main(String[] args) throws IOException, SQLException, NamingException, ParseException {
		ConnectionEnvironment.setUpConnectionForJndi();
		Connection conn = ConnectionFactory.getConnection();

		String datum = "2012-05-13";
		String bundesland = "05";
		String gemeinde = "05315000";
		String wahl = Wahl.landtagswahl.name;
		String art = StimmArt.erststimmen.name;
		Select<StimmbezirkDTO> select = new SelectAllStimmbezirkeList(wahl, art, bundesland, gemeinde, datum);
		List<StimmbezirkDTO> dtoList = new SelectListDao<StimmbezirkDTO>(select, conn).execute();
		for (StimmbezirkDTO dto : dtoList) {
			logger.debug(dto.toString());
		}

	}

}
