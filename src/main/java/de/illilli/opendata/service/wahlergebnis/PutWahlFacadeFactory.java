package de.illilli.opendata.service.wahlergebnis;

import java.io.IOException;

import com.google.gson.JsonSyntaxException;

import de.illilli.opendata.service.Facade;
import de.illilli.opendata.service.wahlergebnis.model.Data2Wahldaten;
import de.illilli.opendata.service.wahlergebnis.model.Wahldaten;

/**
 * Diese Factory bestimmt anhand von Gemeinde-Name und jahr
 */
public class PutWahlFacadeFactory {

	public static Facade getFacade(String data) throws JsonSyntaxException, IOException {

		Wahldaten wahldaten = new Data2Wahldaten(data).getWahldaten();

		String wahl = wahldaten.art;
		String bundesland = wahldaten.bundesland;
		String gemeindeschluessel = wahldaten.gemeinde;

		Facade facade = new PutWahlergebnisDefaultFacade();
		// prüfe auf Art der Wahl; z.B. landtagswahl
		if (Wahl.landtagswahl.name.equals(wahl)) {
			// prüfe auf Land; z.B. NRW
			if (Land.nrw.key.equals(bundesland)) {
				// prüfe auf Gemeinde; z.B. 05315000
				if (Gemeinde.koeln.key.equals(gemeindeschluessel)) {
					facade = new de.illilli.opendata.service.wahlergebnis.landtagswahl.nrw.koeln.PutWahlergebnis2012Facade();
				}
			}
		}
		return facade;
	}
}
