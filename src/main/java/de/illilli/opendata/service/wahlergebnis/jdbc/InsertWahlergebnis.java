package de.illilli.opendata.service.wahlergebnis.jdbc;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import javax.naming.NamingException;

import org.apache.log4j.Logger;

import de.illilli.jdbc.UpdateData;
import de.illilli.opendata.service.wahlergebnis.model.Ergebnis;
import de.illilli.opendata.service.wahlergebnis.model.Stimmbezirk;
import de.illilli.opendata.service.wahlergebnis.model.Wahldaten;

public class InsertWahlergebnis {

	private static final Logger logger = Logger.getLogger(InsertWahlergebnis.class);

	public InsertWahlergebnis(Wahldaten wahldaten) throws ParseException, SQLException, NamingException, IOException {
		UpdateData insertWahldaten = new InsertWahldaten(new Wahldaten2DTO(wahldaten).getDTO());
		insertWahldaten.closeConnection();
		int wahldatenId = insertWahldaten.getUpdated();
		logger.info("wahldatenId = " + wahldatenId);
		for (Stimmbezirk stimmbezirk : wahldaten.stimmbezirke) {
			UpdateData insertStimmbezirk = new InsertStimmbezirk(new Stimmbezirk2DTO(stimmbezirk).getDTO());
			insertStimmbezirk.closeConnection();
			int stimmbezirkId = insertStimmbezirk.getUpdated();
			logger.info("stimmbezirkId = " + stimmbezirkId);
			UpdateData insertStimmbezirk2Wahldaten = new InsertStimmbezirk2Wahldaten(stimmbezirkId, wahldatenId);
			insertStimmbezirk2Wahldaten.closeConnection();
			for (Ergebnis ergebnis : stimmbezirk.ergebnisse) {
				UpdateData insertErgebnis = new InsertErgebnis(new Ergebnis2DTO(ergebnis).getDTO());
				insertErgebnis.closeConnection();
				int ergebnisId = insertErgebnis.getUpdated();
				logger.info("ergebnisId = " + stimmbezirkId);
				UpdateData insertErgebnis2Stimmbezirk = new InsertErgebnis2Stimmbezirk(ergebnisId, stimmbezirkId);
				insertErgebnis2Stimmbezirk.closeConnection();
			}
		}
	}
}
