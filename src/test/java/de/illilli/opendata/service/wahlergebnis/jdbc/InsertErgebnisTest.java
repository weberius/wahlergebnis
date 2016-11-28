package de.illilli.opendata.service.wahlergebnis.jdbc;

import java.io.IOException;
import java.sql.SQLException;

import javax.naming.NamingException;

import org.junit.After;
import org.junit.Before;

import de.illilli.jdbc.ConnectionEnvironment;
import de.illilli.jdbc.UpdateData;

public class InsertErgebnisTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	public static void main(String[] args) throws IOException, SQLException, NamingException {
		ConnectionEnvironment.setUpConnectionForJndi();
		UpdateData insert = new InsertErgebnis(getTestDTO());
		System.out.println(insert.getUpdated());
		insert.closeConnection();

	}

	private static ErgebnisDTO getTestDTO() {
		ErgebnisDTO dto = new ErgebnisDTO();
		dto.setPartei("partei1");
		dto.setStimmen(123);
		return dto;
	}

}
