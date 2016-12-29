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

	private final static String queryStringForStimmbezirk = "/selectErgebnisForStimmbezirk.sql";

	private final static String queryStringForGemeinde = "/selectErgebnisForGemeinde.sql";

	public SelectErgebnis(String wahl, String art, String bundesland, String gemeinde, String datum, int nr)
			throws SQLException, NamingException, IOException, ParseException {
		setQueryString(queryStringForStimmbezirk);
		long time = new SimpleDateFormat("yyyy-MM-dd").parse(datum).getTime();
		Object[] params = new Object[] { wahl, art, bundesland, gemeinde, new Timestamp(time), nr };
		runSelect(new BeanListHandler<ErgebnisDTO>(ErgebnisDTO.class), params);
	}

	public SelectErgebnis(String wahl, String art, String bundesland, String gemeinde, String datum)
			throws SQLException, NamingException, IOException, ParseException {
		setQueryString(queryStringForGemeinde);
		long time = new SimpleDateFormat("yyyy-MM-dd").parse(datum).getTime();
		Object[] params = new Object[] { wahl, art, bundesland, gemeinde, new Timestamp(time) };
		runSelect(new BeanListHandler<ErgebnisDTO>(ErgebnisDTO.class), params);
	}

}
