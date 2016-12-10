package de.illilli.opendata.service.wahlergebnis.jdbc;

import de.illilli.opendata.service.wahlergebnis.model.Ergebnis;

public class DTO2Ergebnis extends Ergebnis {

	public DTO2Ergebnis(ErgebnisDTO dto) {
		super.partei = dto.getPartei();
		super.stimmen = dto.getStimmen();
	}
}
