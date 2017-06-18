package de.illilli.opendata.service.wahlergebnis.jdbc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import javax.naming.NamingException;

import org.apache.commons.io.IOUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import de.illilli.jdbc.ConnectionEnvironment;
import de.illilli.jdbc.ConnectionFactory;
import de.illilli.jdbc.Select;
import de.illilli.jdbc.SelectListDao;
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
		Connection conn = ConnectionFactory.getConnection();

		String datum = "2012-05-13";
		String bundesland = "05";
		String gemeinde = "05315000";
		String wahl = Wahl.landtagswahl.name;
		String art = StimmArt.erststimmen.name;
		String nr = "10101,10102,10103";

		Select<StimmbezirkDTO> select = new SelectStimmbezirkList(wahl, art, bundesland, gemeinde, datum, nr);
		List<StimmbezirkDTO> dtoList = new SelectListDao<StimmbezirkDTO>(select, conn).execute();

		for (StimmbezirkDTO dto : dtoList) {
			System.out.println(dto.toString());
		}
	}

	@Test
	public void testStimmbezirkTokenizerSize() throws SQLException, NamingException, IOException, ParseException {

		String datum = "2012-05-13";
		String bundesland = "05";
		String gemeinde = "05315000";
		String wahl = Wahl.landtagswahl.name;
		String art = StimmArt.erststimmen.name;
		String nr = "10101,10102,10103";

		SelectStimmbezirkList select = new SelectStimmbezirkList(wahl, art, bundesland, gemeinde, datum, nr);
		Object[] stimmbezirkArray = select.getStimmbezirkArray();

		int expected = 3;
		int actual = stimmbezirkArray.length;
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testStimmbezirkTokenizerFirstToken() throws SQLException, NamingException, IOException, ParseException {

		String datum = "2012-05-13";
		String bundesland = "05";
		String gemeinde = "05315000";
		String wahl = Wahl.landtagswahl.name;
		String art = StimmArt.erststimmen.name;
		String nr = "10101,10102,10103";

		SelectStimmbezirkList select = new SelectStimmbezirkList(wahl, art, bundesland, gemeinde, datum, nr);
		Object[] stimmbezirkArray = select.getStimmbezirkArray();

		int expected = 10101;
		int actual = (Integer) stimmbezirkArray[0];
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testStimmbezirkTokenizerOneTokenNoNumber()
			throws SQLException, NamingException, IOException, ParseException {

		String datum = "2012-05-13";
		String bundesland = "05";
		String gemeinde = "05315000";
		String wahl = Wahl.landtagswahl.name;
		String art = StimmArt.erststimmen.name;
		String nr = "10101,abcde,10103";

		SelectStimmbezirkList select = new SelectStimmbezirkList(wahl, art, bundesland, gemeinde, datum, nr);
		Object[] stimmbezirkArray = select.getStimmbezirkArray();

		int expected = 10101;
		int actual = (Integer) stimmbezirkArray[0];
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testStimmbezirkGetSql() throws SQLException, NamingException, IOException, ParseException {

		String datum = "2012-05-13";
		String bundesland = "05";
		String gemeinde = "05315000";
		String wahl = Wahl.landtagswahl.name;
		String art = StimmArt.erststimmen.name;
		String nr = "10101,10102,10103";

		SelectStimmbezirkList select = new SelectStimmbezirkList(wahl, art, bundesland, gemeinde, datum, nr);

		String expected = IOUtils.toString(this.getClass().getResourceAsStream("/selectStimmbezirk.test.sql"));
		String actual = select.getSql();
		System.out.println(select.getSql());
		Assert.assertEquals(expected, actual);
	}

}
