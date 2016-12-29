package de.illilli.opendata.service.wahlergebnis.jdbc;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.naming.NamingException;

import org.apache.commons.dbutils.handlers.BeanHandler;

import de.illilli.jdbc.Select;

public class SelectGemeinde extends Select<GemeindeDTO> {

	private final static String queryString = "/selectGemeinde.sql";

	public SelectGemeinde(String wahl, String art, String bundesland, String gemeinde, String datum)
			throws ParseException, SQLException, NamingException, IOException {

		setQueryString(queryString);
		long time = new SimpleDateFormat("yyyy-MM-dd").parse(datum).getTime();
		Object[] params = new Object[] { wahl, art, bundesland, gemeinde, new Timestamp(time) };
		runSelect(new BeanHandler<GemeindeDTO>(GemeindeDTO.class), params);

	}

}
