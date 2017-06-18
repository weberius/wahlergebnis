package de.illilli.opendata.service.wahlergebnis;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import com.google.gson.Gson;

import de.illilli.jdbc.ConnectionFactory;
import de.illilli.jdbc.Select;
import de.illilli.jdbc.SelectListDao;
import de.illilli.opendata.service.Facade;
import de.illilli.opendata.service.wahlergebnis.jdbc.DTO2Stimmbezirk;
import de.illilli.opendata.service.wahlergebnis.jdbc.SelectAllStimmbezirkeList;
import de.illilli.opendata.service.wahlergebnis.jdbc.StimmbezirkDTO;
import de.illilli.opendata.service.wahlergebnis.model.Stimmbezirk;
import de.illilli.opendata.service.wahlergebnis.model.Wahldaten;

public class WahlStimmbezirkAllFacade implements Facade {

	Wahldaten wahldaten = new Wahldaten();

	public WahlStimmbezirkAllFacade(String wahl, String bundesland, String gemeinde, String datum, String art)
			throws SQLException, NamingException, IOException, ParseException {

		Connection conn = ConnectionFactory.getConnection();
		List<Stimmbezirk> stimmbezirkList = new ArrayList<>();

		// fill wahldaten
		wahldaten.wahl = wahl;
		wahldaten.art = art;
		wahldaten.bundesland = bundesland;
		wahldaten.datum = datum;
		wahldaten.gemeinde = gemeinde;

		Select<StimmbezirkDTO> selectStimmbezirk = new SelectAllStimmbezirkeList(wahl, art, bundesland, gemeinde,
				datum);
		List<StimmbezirkDTO> dtoList = new SelectListDao<StimmbezirkDTO>(selectStimmbezirk, conn).execute();

		for (StimmbezirkDTO stimmbezirkDTO : dtoList) {
			stimmbezirkList.add(new DTO2Stimmbezirk(stimmbezirkDTO));
		}

		Stimmbezirk[] stimmbezirke = new Stimmbezirk[stimmbezirkList.size()];
		wahldaten.stimmbezirke = stimmbezirkList.toArray(stimmbezirke);

	}

	@Override
	public String getJson() {
		return new Gson().toJson(wahldaten);
	}

}
