package de.illilli.opendata.service.wahlergebnis;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import javax.naming.NamingException;

import com.google.gson.Gson;

import de.illilli.jdbc.ConnectionFactory;
import de.illilli.jdbc.Select;
import de.illilli.jdbc.SelectDao;
import de.illilli.jdbc.SelectListDao;
import de.illilli.opendata.service.Facade;
import de.illilli.opendata.service.wahlergebnis.jdbc.DTO2Gemeinde;
import de.illilli.opendata.service.wahlergebnis.jdbc.ErgebnisDTO;
import de.illilli.opendata.service.wahlergebnis.jdbc.GemeindeDTO;
import de.illilli.opendata.service.wahlergebnis.jdbc.SelectErgebnisForGemeinde;
import de.illilli.opendata.service.wahlergebnis.jdbc.SelectGemeinde;
import de.illilli.opendata.service.wahlergebnis.model.WahldatenGemeinde;

public class WahlGemeindeFacade implements Facade {

	WahldatenGemeinde wahldaten = new WahldatenGemeinde();

	public WahlGemeindeFacade(String wahl, String bundesland, String gemeinde, String datum, String art)
			throws SQLException, NamingException, IOException, ParseException {
		Connection conn = ConnectionFactory.getConnection();
		// fill wahldaten
		wahldaten.wahl = wahl;
		wahldaten.art = art;
		wahldaten.bundesland = bundesland;
		wahldaten.datum = datum;
		wahldaten.gemeinde = gemeinde;
		// fill gemeinde
		Select<GemeindeDTO> selectGemeinde = new SelectGemeinde(wahl, art, bundesland, gemeinde, datum);
		GemeindeDTO stimmbezirkDTO = new SelectDao<GemeindeDTO>(selectGemeinde, conn).execute();
		// fill ergebnis
		Select<ErgebnisDTO> selectErgebnis = new SelectErgebnisForGemeinde(wahl, art, bundesland, gemeinde, datum);
		List<ErgebnisDTO> ergebnisDTOList = new SelectListDao<ErgebnisDTO>(selectErgebnis, conn).execute();

		wahldaten.wahlergebnis = new DTO2Gemeinde(stimmbezirkDTO, ergebnisDTOList);

	}

	@Override
	public String getJson() {
		return new Gson().toJson(wahldaten);
	}

}
