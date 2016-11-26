package de.illilli.opendata.service.wahlergebnis.jdbc;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import de.illilli.opendata.service.wahlergebnis.model.Wahldaten;

/**
 * Wandelt ein Waldaten-Object in ein WahldatenDTO-Object
 */
public class Wahldaten2DTO {

	private WahldatenDTO dto = new WahldatenDTO();

	public Wahldaten2DTO(Wahldaten wahldaten) throws ParseException {
		this.dto.setArt(wahldaten.art);
		Date date = new Date(new SimpleDateFormat("dd.MM.yyyy").parse(wahldaten.datum).getTime());
		this.dto.setDatum(date);
		this.dto.setBundesland(wahldaten.bundesland);
		this.dto.setGemeinde(wahldaten.gemeinde);
	}

	public WahldatenDTO getDTO() {
		return this.dto;
	}
}
