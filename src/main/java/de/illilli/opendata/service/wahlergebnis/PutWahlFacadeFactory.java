package de.illilli.opendata.service.wahlergebnis;

import com.google.gson.Gson;

import de.illilli.opendata.service.Facade;

/**
 * Diese Factory bestimmt anhand von Gemeinde-Name und jahr
 */
public class PutWahlFacadeFactory {

	public static Facade getFacade(String data) {

		Wahldaten wahldaten = new Gson().fromJson(data, Wahldaten.class);

		String wahl = wahldaten.art;
		String bundesland = wahldaten.bundesland;
		String gemeindeschluessel = wahldaten.gemeindeschluessel;

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
