package de.illilli.opendata.service.wahlergebnis;

import de.illilli.opendata.service.Facade;

/**
 * Diese Factory bestimmt anhand von Gemeinde-Name und jahr
 */
public class PutWahlFacadeFactory {

	/**
	 * Zur Zeit wird in der Factory nur auf die Gemeinde geprüft. Die
	 * Eigenschaften wahl, Bundesland und Jahr sind noch nicht berücksichtigt.
	 * 
	 * @param wahl
	 *            Die Art der Wahl; z.B. 'landtagswahl', 'bundestagswahl',
	 *            'kommunalwahl'
	 * @param land
	 *            Das Bundesland kleingeschrieben und ohne umlaute; z.B. 'nrw'
	 * @param gemeindeschluessel
	 *            Es wird nach Gemeindeschluessel abgefragt; z.B. '05315000'
	 * @param year
	 *            Das Jahr, in der die Wahl durchgeführt wurde.
	 * @return
	 */
	@Deprecated
	public static Facade getFacade(String wahl, String land, String gemeindeschluessel, int year) {
		Facade facade = new PutWahlergebnisDefaultFacade();
		// prüfe auf Art der Wahl; z.B. landtagswahl
		if (Wahl.landtagswahl.name.equals(wahl)) {
			// prüfe auf Land; z.B. NRW
			if (Land.nrw.name.equals(land)) {
				// prüfe auf Gemeinde; z.B. 05315000
				if (Gemeinde.koeln.key.equals(gemeindeschluessel)) {
					facade = new de.illilli.opendata.service.wahlergebnis.landtagswahl.nrw.koeln.PutWahlergebnis2012Facade();
				}
			}
		}
		return facade;
	}
}
