package de.illilli.opendata.service.wahlergebnis.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.apache.commons.io.IOUtils;

import de.illilli.jdbc.Select;

public class SelectErgebnisForGemeinde implements Select<ErgebnisDTO> {

	private final static String sqlFileName = "/sql/selectErgebnisForGemeinde.sql";

	private String wahl;
	private String art;
	private String bundesland;
	private String gemeinde;
	private long time;

	public SelectErgebnisForGemeinde(String wahl, String art, String bundesland, String gemeinde, String datum)
			throws ParseException {
		this.wahl = wahl;
		this.art = art;
		this.bundesland = bundesland;
		this.gemeinde = gemeinde;
		this.time = new SimpleDateFormat("yyyy-MM-dd").parse(datum).getTime();
	}

	@Override
	public String getSql() throws IOException {
		InputStream inputStream = SelectErgebnisForGemeinde.class.getResourceAsStream(sqlFileName);
		return IOUtils.toString(inputStream);
	}

	@Override
	public Object[] getParameter() {
		Object[] params = new Object[] { wahl, art, bundesland, gemeinde, new Timestamp(time) };
		return params;
	}

	@Override
	public Class<ErgebnisDTO> getDtoClazz() {
		return ErgebnisDTO.class;
	}

}
