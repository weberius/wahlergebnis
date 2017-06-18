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
import de.illilli.opendata.service.wahlergebnis.jdbc.ErgebnisDTO;
import de.illilli.opendata.service.wahlergebnis.jdbc.SelectErgebnisForStimmbezirk;
import de.illilli.opendata.service.wahlergebnis.jdbc.SelectStimmbezirkList;
import de.illilli.opendata.service.wahlergebnis.jdbc.StimmbezirkDTO;
import de.illilli.opendata.service.wahlergebnis.model.Stimmbezirk;
import de.illilli.opendata.service.wahlergebnis.model.Wahldaten;

/**
 * Die Facade verbirgt alle Schritte, die notwendig sind, die
 * Landtagswahlergebnisse herauszugeben.
 * 
 */
public class WahlStimmbezirkFacade implements Facade {

	Wahldaten wahldaten = new Wahldaten();

	public WahlStimmbezirkFacade(String wahl, String bundesland, String gemeinde, String datum, String art,
			String stimmbezirk) throws SQLException, NamingException, IOException, ParseException {

		Connection conn = ConnectionFactory.getConnection();
		List<Stimmbezirk> stimmbezirkList = new ArrayList<>();

		// fill wahldaten
		wahldaten.wahl = wahl;
		wahldaten.art = art;
		wahldaten.bundesland = bundesland;
		wahldaten.datum = datum;
		wahldaten.gemeinde = gemeinde;
		// fill stimmbezirk
		Select<StimmbezirkDTO> selectStimmbezirk = new SelectStimmbezirkList(wahl, art, bundesland, gemeinde, datum,
				stimmbezirk);
		List<StimmbezirkDTO> dtoList = new SelectListDao<StimmbezirkDTO>(selectStimmbezirk, conn).execute();
		for (StimmbezirkDTO stimmbezirkDTO : dtoList) {
			Select<ErgebnisDTO> selectErgebnis = new SelectErgebnisForStimmbezirk(wahl, art, bundesland, gemeinde,
					datum, stimmbezirkDTO.getNr());
			List<ErgebnisDTO> ergebnisDTOList = new SelectListDao<ErgebnisDTO>(selectErgebnis, conn).execute();
			stimmbezirkList.add(new DTO2Stimmbezirk(stimmbezirkDTO, ergebnisDTOList));
		}
		Stimmbezirk[] stimmbezirke = new Stimmbezirk[stimmbezirkList.size()];
		wahldaten.stimmbezirke = stimmbezirkList.toArray(stimmbezirke);
	}

	public String getJson() {
		return new Gson().toJson(wahldaten);
	}

}
