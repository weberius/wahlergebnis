package de.illilli.opendata.service.wahlergebnis.jdbc;

import de.illilli.opendata.service.wahlergebnis.model.Ergebnis;

public class Ergebnis2DTO {

	private ErgebnisDTO dto = new ErgebnisDTO();

	public Ergebnis2DTO(Ergebnis ergebnis) {
		this.dto.setPartei(ergebnis.partei);
		this.dto.setStimmen(ergebnis.stimmen);
	}

	public ErgebnisDTO getDTO() {
		return this.dto;
	}
}
