package de.illilli.opendata.service.wahlergebnis.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;

import javax.naming.NamingException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import de.illilli.jdbc.ConnectionFactory;
import de.illilli.jdbc.UpdateData;
import de.illilli.opendata.service.wahlergebnis.model.Wahldaten;

public class DeleteWahlergebnis implements UpdateData {

	private static final Logger logger = Logger.getLogger(InsertWahldaten.class);
	private Connection conn;
	private int updated;

	public DeleteWahlergebnis(Wahldaten wahldaten) throws SQLException, NamingException, IOException, ParseException {
		this(wahldaten, ConnectionFactory.getConnection());
	}

	public DeleteWahlergebnis(Wahldaten wahldaten, Connection connection)
			throws IOException, SQLException, ParseException {
		this.conn = connection;
		WahldatenDTO dto = new Wahldaten2DTO(wahldaten).getDTO();
		InputStream inputStream = this.getClass().getResourceAsStream("/deleteWahlergebnis.sql");
		String sql = IOUtils.toString(inputStream);
		Object[] params = new Object[] { dto.getArt(), dto.getDatum(), dto.getBundesland(), dto.getGemeinde() };
		QueryRunner run = new QueryRunner();
		updated = run.update(conn, sql, params);
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
