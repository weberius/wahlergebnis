package de.illilli.opendata.service.wahlergebnis.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.apache.commons.io.IOUtils;

import de.illilli.jdbc.Select;

public class SelectErgebnisForStimmbezirk implements Select<ErgebnisDTO> {

	private final static String sqlFileName = "/sql/selectErgebnisForStimmbezirk.sql";
	private String wahl;
	private String art;
	private String bundesland;
	private String gemeinde;
	private long time;
	private int nr;

	public SelectErgebnisForStimmbezirk(String wahl, String art, String bundesland, String gemeinde, String datum,
			int nr) throws ParseException {
		this.wahl = wahl;
		this.art = art;
		this.bundesland = bundesland;
		this.gemeinde = gemeinde;
		this.time = new SimpleDateFormat("yyyy-MM-dd").parse(datum).getTime();
		this.nr = nr;
	}

	@Override
	public String getSql() throws IOException {
		InputStream inputStream = SelectErgebnisForGemeinde.class.getResourceAsStream(sqlFileName);
		return IOUtils.toString(inputStream);
	}

	@Override
	public Object[] getParameter() {
		return new Object[] { wahl, art, bundesland, gemeinde, new Timestamp(time), nr };
	}

	@Override
	public Class<ErgebnisDTO> getDtoClazz() {
		return ErgebnisDTO.class;
	}

}
