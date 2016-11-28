package de.illilli.opendata.service.wahlergebnis.jdbc;

import de.illilli.opendata.service.wahlergebnis.model.Stimmbezirk;

public class Stimmbezirk2DTO {

	private StimmbezirkDTO dto = new StimmbezirkDTO();

	public Stimmbezirk2DTO(Stimmbezirk stimmbezirk) {
		dto.setAbgegeben(stimmbezirk.abgegeben);
		dto.setGueltig(stimmbezirk.gueltig);
		dto.setNr(stimmbezirk.nr);
		dto.setUngueltig(stimmbezirk.ungueltig);
		dto.setWahlberechtigt(stimmbezirk.wahlberechtigt);
	}

	public StimmbezirkDTO getDTO() {
		return dto;
	}
}
