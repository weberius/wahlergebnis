package de.illilli.opendata.service.wahlergebnis.jdbc;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import javax.naming.NamingException;

import de.illilli.jdbc.UpdateData;
import de.illilli.opendata.service.wahlergebnis.model.Ergebnis;
import de.illilli.opendata.service.wahlergebnis.model.Stimmbezirk;
import de.illilli.opendata.service.wahlergebnis.model.Wahldaten;

public class InsertWahlergebnis {

	public InsertWahlergebnis(Wahldaten wahldaten) throws ParseException, SQLException, NamingException, IOException {
		UpdateData insertWahldaten = new InsertWahldaten(new Wahldaten2DTO(wahldaten).getDTO());
		insertWahldaten.closeConnection();
		int wahldatenId = insertWahldaten.getUpdated();
		for (Stimmbezirk stimmbezirk : wahldaten.stimmbezirke) {
			UpdateData insertStimmbezirk = new InsertStimmbezirk(new Stimmbezirk2DTO(stimmbezirk).getDTO());
			insertStimmbezirk.closeConnection();
			int stimmbezirkId = insertStimmbezirk.getUpdated();
			UpdateData insertErgebnis2Stimmbezirk = new InsertErgebnis2Stimmbezirk(wahldatenId, stimmbezirkId);
			insertErgebnis2Stimmbezirk.closeConnection();
			for (Ergebnis ergebnis : stimmbezirk.ergebnisse) {
				UpdateData insertErgebnis = new InsertErgebnis(new Ergebnis2DTO(ergebnis).getDTO());
				insertErgebnis.closeConnection();
				int ergebnisId = insertErgebnis.getUpdated();
				UpdateData insertStimmbezirk2Wahldaten = new InsertStimmbezirk2Wahldaten(stimmbezirkId, ergebnisId);
				insertStimmbezirk2Wahldaten.closeConnection();
			}
		}
	}
}
