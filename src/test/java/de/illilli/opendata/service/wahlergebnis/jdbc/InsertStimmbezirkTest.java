package de.illilli.opendata.service.wahlergebnis.jdbc;

import java.io.IOException;
import java.sql.SQLException;

import javax.naming.NamingException;

import org.junit.After;
import org.junit.Before;

import de.illilli.jdbc.ConnectionEnvironment;
import de.illilli.jdbc.UpdateData;

public class InsertStimmbezirkTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	public static void main(String[] args) throws SQLException, NamingException, IOException {
		ConnectionEnvironment.setUpConnectionForJndi();
		UpdateData insert = new InsertStimmbezirk(getTestDTO());
		System.out.println(insert.getUpdated());
		insert.closeConnection();
	}

	private static StimmbezirkDTO getTestDTO() {
		StimmbezirkDTO dto = new StimmbezirkDTO();
		dto.setAbgegeben(1000);
		dto.setGueltig(850);
		dto.setNr(10101);
		dto.setUngueltig(150);
		dto.setWahlberechtigt(1001);
		return dto;
	}

}
