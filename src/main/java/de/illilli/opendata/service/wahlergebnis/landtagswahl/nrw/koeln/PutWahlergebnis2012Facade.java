package de.illilli.opendata.service.wahlergebnis.landtagswahl.nrw.koeln;

import de.illilli.opendata.service.Facade;

/**
 * Lese die Werte für die Landtagswahl von offene Daten Köln ein und schreibe
 * sie in die Datenbank.
 *
 */
public class PutWahlergebnis2012Facade implements Facade {

	private String msg = "landtagswahl 05 05315000";

	/**
	 * 
	 * @param gemeinde
	 * @param year
	 */
	public PutWahlergebnis2012Facade() {
		// hole die Daten von Offene Daten Köln

		// Überführe sie in eine Java-Struktur

		// Lösche die bisherigen Werte für diesen Datensatz

		// Füge die Daten in die Datenbank ein.
	}

	/**
	 * Mit dieser Methode können Informationen bzgl. des Upload-Prozesses an die
	 * Service-Schnittstelle zurückgegeben werden.
	 */
	@Override
	public String getJson() {
		return msg;
	}

}
