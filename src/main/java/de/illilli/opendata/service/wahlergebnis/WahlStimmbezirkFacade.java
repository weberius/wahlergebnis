package de.illilli.opendata.service.wahlergebnis;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import com.google.gson.Gson;

import de.illilli.jdbc.Select;
import de.illilli.opendata.service.Facade;
import de.illilli.opendata.service.wahlergebnis.jdbc.DTO2Stimmbezirk;
import de.illilli.opendata.service.wahlergebnis.jdbc.ErgebnisDTO;
import de.illilli.opendata.service.wahlergebnis.jdbc.SelectErgebnis;
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
		List<StimmbezirkDTO> dtoList = selectStimmbezirk.getDbObjectList();
		for (StimmbezirkDTO stimmbezirkDTO : dtoList) {
			Select<ErgebnisDTO> selectErgebnis = new SelectErgebnis(wahl, art, bundesland, gemeinde, datum,
					stimmbezirkDTO.getNr());
			List<ErgebnisDTO> ergebnisDTOList = selectErgebnis.getDbObjectList();
			stimmbezirkList.add(new DTO2Stimmbezirk(stimmbezirkDTO, ergebnisDTOList));
		}
		Stimmbezirk[] stimmbezirke = new Stimmbezirk[stimmbezirkList.size()];
		wahldaten.stimmbezirke = stimmbezirkList.toArray(stimmbezirke);
	}

	public String getJson() {
		return new Gson().toJson(wahldaten);
	}

}
