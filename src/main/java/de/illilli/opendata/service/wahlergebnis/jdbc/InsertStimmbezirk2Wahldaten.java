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

public class InsertStimmbezirk2Wahldaten implements UpdateData {

	private static final Logger logger = Logger.getLogger(InsertStimmbezirk2Wahldaten.class);
	private Connection conn;
	private int updated;

	public InsertStimmbezirk2Wahldaten(int stimmbezirkId, int ergebnisId)
			throws SQLException, NamingException, IOException {
		this(stimmbezirkId, ergebnisId, ConnectionFactory.getConnection());
	}

	public InsertStimmbezirk2Wahldaten(int stimmbezirkId, int ergebnisId, Connection connection)
			throws IOException, SQLException {
		this.conn = connection;
		InputStream inputStream = InsertErgebnis.class.getResourceAsStream("/insertStimmbezirk2Wahldaten.sql");
		String sql = IOUtils.toString(inputStream);

		QueryRunner run = new QueryRunner();
		Object[] params = new Object[] { stimmbezirkId, ergebnisId };
		this.updated = run.update(this.conn, sql, params);

		logger.info("insert " + stimmbezirkId + ";" + ergebnisId);
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
