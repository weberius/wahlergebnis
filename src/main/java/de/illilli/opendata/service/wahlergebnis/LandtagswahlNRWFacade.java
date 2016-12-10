package de.illilli.opendata.service.wahlergebnis;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import javax.naming.NamingException;

import com.google.gson.Gson;

import de.illilli.jdbc.Select;
import de.illilli.opendata.service.Facade;
import de.illilli.opendata.service.wahlergebnis.jdbc.DTO2Stimmbezirk;
import de.illilli.opendata.service.wahlergebnis.jdbc.ErgebnisDTO;
import de.illilli.opendata.service.wahlergebnis.jdbc.SelectErgebnis;
import de.illilli.opendata.service.wahlergebnis.jdbc.SelectStimmbezirk;
import de.illilli.opendata.service.wahlergebnis.jdbc.StimmbezirkDTO;
import de.illilli.opendata.service.wahlergebnis.model.Stimmbezirk;
import de.illilli.opendata.service.wahlergebnis.model.Wahldaten;

/**
 * Die Facade verbirgt alle Schritte, die notwendig sind, die
 * Landtagswahlergebnisse herauszugeben.
 * 
 */
public class LandtagswahlNRWFacade implements Facade {

	Wahldaten wahldaten = new Wahldaten();

	public LandtagswahlNRWFacade(String bundesland, String gemeinde, String datum, int nr)
			throws SQLException, NamingException, IOException, ParseException {
		// fill wahldaten
		wahldaten.art = Wahl.landtagswahl.name;
		wahldaten.bundesland = Land.nrw.key;
		wahldaten.datum = datum;
		wahldaten.gemeinde = Gemeinde.koeln.key;
		// fill stimmbezirk
		Select<StimmbezirkDTO> selectStimmbezirk = new SelectStimmbezirk(bundesland, gemeinde, datum, nr);
		StimmbezirkDTO stimmbezirkDTO = selectStimmbezirk.getDbObject();

		Select<ErgebnisDTO> selectErgebnis = new SelectErgebnis(bundesland, gemeinde, datum, nr);
		List<ErgebnisDTO> ergebnisDTOList = selectErgebnis.getDbObjectList();

		wahldaten.stimmbezirke = new Stimmbezirk[] { new DTO2Stimmbezirk(stimmbezirkDTO, ergebnisDTOList) };

	}

	public String getJson() {
		return new Gson().toJson(wahldaten);
	}

}
