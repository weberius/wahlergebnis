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

public class SelectErgebnisTest {

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
		Select<ErgebnisDTO> select = new SelectErgebnis(datum, bundesland, gemeinde, nr);
		List<ErgebnisDTO> ergebnis = select.getDbObjectList();
		for (ErgebnisDTO dto : ergebnis) {
			System.out.println(dto.toString());
		}

	}

}
