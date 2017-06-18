package de.illilli.opendata.service.wahlergebnis.jdbc;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;

import de.illilli.jdbc.Select;

public class SelectWahldaten implements Select<WahldatenDTO> {

	private final static String sqlFileName = "/sql/selectWahldaten.sql";

	@Override
	public String getSql() throws IOException {
		InputStream inputStream = SelectErgebnisForGemeinde.class.getResourceAsStream(sqlFileName);
		StringBuffer sql = new StringBuffer(IOUtils.toString(inputStream));
		return sql.toString();
	}

	@Override
	public Object[] getParameter() {
		Object[] params = new Object[] {};
		return params;
	}

	@Override
	public Class<WahldatenDTO> getDtoClazz() {
		return WahldatenDTO.class;
	}
}
