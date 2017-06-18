package de.illilli.opendata.service.wahlergebnis.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.naming.NamingException;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.math.NumberUtils;

import de.illilli.jdbc.Select;

public class SelectStimmbezirkList implements Select<StimmbezirkDTO> {

	private static String sqlFileName = "/sql/selectStimmbezirk.sql";

	private String wahl;
	private String art;
	private String bundesland;
	private String gemeinde;
	private long time;
	private String stimmbezirke;

	public SelectStimmbezirkList(String wahl, String art, String bundesland, String gemeinde, String datum,
			String stimmbezirke) throws SQLException, NamingException, IOException, ParseException {
		this.wahl = wahl;
		this.art = art;
		this.bundesland = bundesland;
		this.gemeinde = gemeinde;
		this.time = new SimpleDateFormat("yyyy-MM-dd").parse(datum).getTime();
		this.stimmbezirke = stimmbezirke;
	}

	@Override
	public String getSql() throws IOException {
		InputStream inputStream = SelectErgebnisForGemeinde.class.getResourceAsStream(sqlFileName);
		StringBuffer sql = new StringBuffer(IOUtils.toString(inputStream));
		sql.append(" and ");
		sql.append(" stimmbezirk.nr ");
		sql.append(" in ( ");
		for (int i = 0; i < getStimmbezirkArray().length; i++) {
			if (i == 0) {
				sql.append("?");
			} else {
				sql.append(",?");
			}
		}
		sql.append(" ) ");
		return sql.toString();
	}

	@Override
	public Object[] getParameter() {

		Object[] stimmbezirkArray = getStimmbezirkArray();
		Object[] params = new Object[] { wahl, art, bundesland, gemeinde, new Timestamp(time) };

		return ArrayUtils.addAll(params, stimmbezirkArray);
	}

	@Override
	public Class<StimmbezirkDTO> getDtoClazz() {
		return StimmbezirkDTO.class;
	}

	Object[] getStimmbezirkArray() {
		Integer[] stimmbezirkArray = new Integer[0];
		List<Integer> stimmbezirkList = new ArrayList<>();
		StringTokenizer st = new StringTokenizer(this.stimmbezirke, ",");
		while (st.hasMoreTokens()) {
			String str = st.nextToken();
			if (NumberUtils.isCreatable(str)) {
				stimmbezirkList.add(NumberUtils.createInteger(str));
			}
		}
		return stimmbezirkList.toArray(stimmbezirkArray);
	}

}
