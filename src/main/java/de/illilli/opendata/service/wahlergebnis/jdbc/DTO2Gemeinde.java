package de.illilli.opendata.service.wahlergebnis.jdbc;

import java.util.List;

import de.illilli.opendata.service.wahlergebnis.model.Ergebnis;
import de.illilli.opendata.service.wahlergebnis.model.Gemeinde;

public class DTO2Gemeinde extends Gemeinde {

	public DTO2Gemeinde(GemeindeDTO dto, List<ErgebnisDTO> ergebnisDTOList) {

		super.abgegeben = dto.getAbgegeben();
		super.gueltig = dto.getGueltig();
		super.ungueltig = dto.getUngueltig();
		super.wahlberechtigt = dto.getWahlberechtigt();
		Ergebnis[] ergebnisArray = new Ergebnis[ergebnisDTOList.size()];
		for (int i = 0; i < ergebnisDTOList.size(); i++) {
			ergebnisArray[i] = new DTO2Ergebnis(ergebnisDTOList.get(i));
		}
		super.ergebnisse = ergebnisArray;
	}

}
