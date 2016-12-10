package de.illilli.opendata.service.wahlergebnis.jdbc;

import java.io.IOException;
import java.sql.SQLException;

import javax.naming.NamingException;

import org.apache.commons.dbutils.handlers.BeanListHandler;

import de.illilli.jdbc.Select;

public class SelectWahldaten extends Select<WahldatenDTO> {

	private final static String queryString = "/selectWahldaten.sql";

	public SelectWahldaten() throws SQLException, NamingException, IOException {
		setQueryString(queryString);
		Object[] params = new Object[] {};
		runSelect(new BeanListHandler<WahldatenDTO>(WahldatenDTO.class), params);

	}
}
