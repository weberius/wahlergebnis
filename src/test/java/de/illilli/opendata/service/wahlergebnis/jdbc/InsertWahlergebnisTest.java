package de.illilli.opendata.service.wahlergebnis.jdbc;

import java.sql.Connection;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.illilli.jdbc.ConnectionEnvironment;

public class InsertWahlergebnisTest {

	private Connection conn;

	@Before
	public void setUp() throws Exception {
		ConnectionEnvironment.setUpConnectionForJndi();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testInsertWahlergebnisConnection() {

	}

}
