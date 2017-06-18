package de.illilli.opendata.service.wahlergebnis.jdbc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

import org.junit.After;
import org.junit.Before;

import de.illilli.jdbc.ConnectionEnvironment;
import de.illilli.jdbc.ConnectionFactory;
import de.illilli.jdbc.Select;
import de.illilli.jdbc.SelectListDao;

public class SelectWahldatenTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	public static void main(String[] args) throws SQLException, NamingException, IOException {
		ConnectionEnvironment.setUpConnectionForJndi();
		Connection conn = ConnectionFactory.getConnection();

		Select<WahldatenDTO> select = new SelectWahldaten();
		List<WahldatenDTO> dtoList = new SelectListDao<WahldatenDTO>(select, conn).execute();
		for (WahldatenDTO dto : dtoList) {
			System.out.println(dto.toString());
		}

	}

}
