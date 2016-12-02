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

public class InsertErgebnis2Stimmbezirk implements UpdateData {

	private static final Logger logger = Logger.getLogger(InsertErgebnis2Stimmbezirk.class);
	private Connection conn;
	private int updated;

	public InsertErgebnis2Stimmbezirk(int ergebnis, int stimmbezirkId)
			throws SQLException, NamingException, IOException {
		this(ergebnis, stimmbezirkId, ConnectionFactory.getConnection());
	}

	public InsertErgebnis2Stimmbezirk(int ergebnis, int stimmbezirkId, Connection connection)
			throws IOException, SQLException {

		this.conn = connection;
		InputStream inputStream = InsertErgebnis.class.getResourceAsStream("/insertErgebnis2Stimmbezirk.sql");
		String sql = IOUtils.toString(inputStream);

		QueryRunner run = new QueryRunner();
		Object[] params = new Object[] { ergebnis, stimmbezirkId };
		this.updated = run.update(this.conn, sql, params);

		logger.info("insert " + ergebnis + ";" + stimmbezirkId);

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
