package de.illilli.opendata.service.wahlergebnis.jdbc;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.naming.NamingException;

import org.apache.commons.dbutils.handlers.BeanHandler;

import de.illilli.jdbc.Select;

public class SelectStimmbezirk extends Select<StimmbezirkDTO> {

	private final static String queryString = "/selectStimmbezirk.sql";

	public SelectStimmbezirk(String bundesland, String gemeinde, String datum, int nr)
			throws SQLException, NamingException, IOException, ParseException {
		setQueryString(queryString);
		long time = new SimpleDateFormat("yyyy-MM-dd").parse(datum).getTime();
		Object[] params = new Object[] { new Timestamp(time), bundesland, gemeinde, nr };
		runSelect(new BeanHandler<StimmbezirkDTO>(StimmbezirkDTO.class), params);
	}
}
