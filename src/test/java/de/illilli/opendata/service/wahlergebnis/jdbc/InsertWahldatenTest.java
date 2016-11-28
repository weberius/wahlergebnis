package de.illilli.opendata.service.wahlergebnis.jdbc;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.naming.NamingException;

import org.junit.After;
import org.junit.Before;

import de.illilli.jdbc.ConnectionEnvironment;
import de.illilli.jdbc.UpdateData;

public class InsertWahldatenTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	public static void main(String[] args) throws SQLException, NamingException, IOException, ParseException {
		ConnectionEnvironment.setUpConnectionForJndi();
		UpdateData insert = new InsertWahldaten(getTestDTO());
		System.out.println(insert.getUpdated());
		insert.closeConnection();
	}

	static WahldatenDTO getTestDTO() throws ParseException {
		WahldatenDTO dto = new WahldatenDTO();
		dto.setArt("landtagswahl");
		Date datum = new Date(new SimpleDateFormat("dd.MM.yyyy").parse("11.11.2016").getTime());
		dto.setDatum(datum);
		dto.setGemeinde("01231231");
		dto.setBundesland("05");
		return dto;
	}

}
