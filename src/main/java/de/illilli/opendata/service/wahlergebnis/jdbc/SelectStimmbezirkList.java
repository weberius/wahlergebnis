package de.illilli.opendata.service.wahlergebnis.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.naming.NamingException;

import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.io.IOUtils;

import de.illilli.jdbc.Select;

public class SelectStimmbezirkList extends Select<StimmbezirkDTO> {

	private static String queryString = "/selectStimmbezirk.sql";

	public SelectStimmbezirkList(String wahl, String art, String bundesland, String gemeinde, String datum, String nr)
			throws SQLException, NamingException, IOException, ParseException {
		long time = new SimpleDateFormat("yyyy-MM-dd").parse(datum).getTime();
		Object[] params = new Object[] { wahl, art, bundesland, gemeinde, new Timestamp(time) };
		runSelect(new BeanListHandler<StimmbezirkDTO>(StimmbezirkDTO.class), SelectStimmbezirkList.getSQL(nr), params);
	}

	public static String getSQL(String stimmbezirke) throws IOException {
		InputStream inputStream = SelectStimmbezirkList.class.getResourceAsStream(queryString);
		StringBuffer sql = new StringBuffer(IOUtils.toString(inputStream));
		sql.append(" and ");
		sql.append(" stimmbezirk.nr ");
		sql.append(" in ( ");
		sql.append(stimmbezirke);
		sql.append(" ) ");
		return sql.toString();
	}

}
