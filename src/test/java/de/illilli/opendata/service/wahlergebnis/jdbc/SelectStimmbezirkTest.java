package de.illilli.opendata.service.wahlergebnis.jdbc;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import javax.naming.NamingException;

import org.junit.After;
import org.junit.Before;

import de.illilli.jdbc.ConnectionEnvironment;
import de.illilli.jdbc.Select;

public class SelectStimmbezirkTest {

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
		int nr = 10101;
		Select<StimmbezirkDTO> select = new SelectStimmbezirk(datum, bundesland, gemeinde, nr);
		StimmbezirkDTO dto = select.getDbObject();
		System.out.println(dto.toString());
	}

}
