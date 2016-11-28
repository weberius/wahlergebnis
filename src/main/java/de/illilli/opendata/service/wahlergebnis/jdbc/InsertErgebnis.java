package de.illilli.opendata.service.wahlergebnis.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.NamingException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import de.illilli.jdbc.ConnectionFactory;
import de.illilli.jdbc.UpdateData;

public class InsertErgebnis implements UpdateData {

	private static final Logger logger = Logger.getLogger(InsertErgebnis.class);
	private Connection conn;
	private int updated;

	public InsertErgebnis(ErgebnisDTO dto) throws SQLException, NamingException, IOException {
		this(dto, ConnectionFactory.getConnection());
	}

	public InsertErgebnis(ErgebnisDTO dto, Connection connection) throws IOException, SQLException {
		this.conn = connection;
		InputStream inputStream = InsertErgebnis.class.getResourceAsStream("/insertErgebnis.sql");
		String sql = IOUtils.toString(inputStream);

		QueryRunner run = new QueryRunner();
		Object[] params = new Object[] { dto.getPartei(), dto.getStimmen() };
		this.updated = run.update(this.conn, sql, params);

		logger.info("insert " + dto.toString());
	}

	@Override
	public int getUpdated() {
		return updated;
	}

	@Override
	public void closeConnection() throws SQLException {
		this.conn.close();
	}

}
