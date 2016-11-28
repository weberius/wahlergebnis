package de.illilli.opendata.service.wahlergebnis.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.NamingException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import de.illilli.jdbc.ConnectionFactory;
import de.illilli.jdbc.UpdateData;

public class InsertWahldaten implements UpdateData {

	private static final Logger logger = Logger.getLogger(InsertWahldaten.class);
	private Connection conn;
	private Integer id;

	public InsertWahldaten(WahldatenDTO dto) throws SQLException, NamingException, IOException {
		this(dto, ConnectionFactory.getConnection());
	}

	public InsertWahldaten(WahldatenDTO dto, Connection connection) throws IOException, SQLException {

		this.conn = connection;
		InputStream inputStream = InsertWahldaten.class.getResourceAsStream("/insertWahldaten.sql");
		String sql = IOUtils.toString(inputStream);

		QueryRunner run = new QueryRunner();
		Object[] params = new Object[] { dto.getArt(), dto.getDatum(), dto.getBundesland(), dto.getGemeinde() };

		ResultSetHandler<Integer> rsh = new ScalarHandler<Integer>();
		id = run.insert(this.conn, sql, rsh, params);

		logger.info("insert " + dto.toString());
	}

	@Override
	public void closeConnection() throws SQLException {
		conn.close();
	}

	@Override
	public int getUpdated() {
		return id;
	}

}
