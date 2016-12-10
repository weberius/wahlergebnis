package de.illilli.opendata.service.wahlergebnis.jdbc;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.naming.NamingException;

import org.apache.commons.dbutils.handlers.BeanListHandler;

import de.illilli.jdbc.Select;

public class SelectErgebnis extends Select<ErgebnisDTO> {

	private final static String queryString = "/selectErgebnis.sql";

	public SelectErgebnis(String bundesland, String gemeinde, String datum, int nr)
			throws SQLException, NamingException, IOException, ParseException {
		setQueryString(queryString);
		long time = new SimpleDateFormat("yyyy-MM-dd").parse(datum).getTime();
		Object[] params = new Object[] { new Timestamp(time), bundesland, gemeinde, nr };
		runSelect(new BeanListHandler<ErgebnisDTO>(ErgebnisDTO.class), params);
	}

}
