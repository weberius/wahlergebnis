package de.illilli.opendata.service.wahlergebnis.jdbc;

import java.text.SimpleDateFormat;

import de.illilli.opendata.service.Config;
import de.illilli.opendata.service.wahlergebnis.model.Wahldaten;

public class DTO2Wahldaten extends Wahldaten {

	public DTO2Wahldaten(WahldatenDTO dto) {
		super.art = dto.getArt();
		super.bundesland = dto.getBundesland();
		super.datum = new SimpleDateFormat(Config.getProperty("dateformat")).format(dto.getDatum());
		super.gemeinde = dto.getGemeinde();
		super.wahl = dto.getWahl();
	}
}
