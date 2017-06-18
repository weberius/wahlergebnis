package de.illilli.opendata.service.wahlergebnis.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import de.illilli.jdbc.Select;

public class SelectAllStimmbezirkeList implements Select<StimmbezirkDTO> {

	private static final Logger logger = Logger.getLogger(SelectAllStimmbezirkeList.class);

	private static String sqlFileName = "/sql/selectStimmbezirk.sql";

	private String wahl;
	private String art;
	private String bundesland;
	private String gemeinde;
	private long time;

	public SelectAllStimmbezirkeList(String wahl, String art, String bundesland, String gemeinde, String datum)
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
		StringBuffer sql = new StringBuffer(IOUtils.toString(inputStream));
		logger.info(sql.toString());
		return sql.toString();
	}

	@Override
	public Object[] getParameter() {
		logger.info("parameter: " + wahl + "," + art + "," + bundesland + "," + gemeinde + "," + new Timestamp(time));
		return new Object[] { wahl, art, bundesland, gemeinde, new Timestamp(time) };
	}

	@Override
	public Class<StimmbezirkDTO> getDtoClazz() {
		return StimmbezirkDTO.class;
	}

}
