package de.illilli.opendata.service.wahlergebnis;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import javax.naming.NamingException;

import de.illilli.opendata.service.Facade;

public class WahlFacadeFactory {

	/**
	 * Wahl.landtagswahl, land, gemeinde, date, stimmbezirk
	 * 
	 * @param wahl
	 *            z.B. landtagswahl oder bundestagswahl
	 * @param land
	 *            das Bundesland als Schlüssel; z.B. '05' für NRW
	 * @param gemeinde
	 *            die Gemeinde als Gemeindekennschlüssel;
	 * @param date
	 *            das Datum, an dem gewählt wurde
	 * @param stimmbezirk
	 *            der Stimmbezirk in dem gewählt wurde.
	 * @param art
	 *            z.B. erststimmen oder zweitstimmen
	 * @return
	 * @throws SQLException
	 * @throws NamingException
	 * @throws IOException
	 * @throws ParseException
	 */
	public static Facade getFacade(String wahl, String land, String gemeinde, String datum, String art,
			String stimmbezirk) throws SQLException, NamingException, IOException, ParseException {
		Facade facade = new DefaultWahlFacade();

		if (!stimmArtExists(art)) {
			return facade;
		}

		if (Wahl.landtagswahl.name.equals(wahl)) {
			if (Land.nrw.key.equals(land)) {
				if ("all".equals(stimmbezirk)) {
					facade = new WahlStimmbezirkAllFacade(wahl, land, gemeinde, datum, art);
				} else {
					facade = new WahlStimmbezirkFacade(wahl, land, gemeinde, datum, art, stimmbezirk);
				}
			}
		}
		return facade;
	}

	public static Facade getFacade(String wahl, String land, String gemeinde, String datum, String art)
			throws SQLException, NamingException, IOException, ParseException {
		Facade facade = new DefaultWahlFacade();

		if (!stimmArtExists(art)) {
			return facade;
		}

		if (Wahl.landtagswahl.name.equals(wahl)) {
			if (Land.nrw.key.equals(land)) {
				facade = new WahlGemeindeFacade(wahl, land, gemeinde, datum, art);
			}
		}
		return facade;
	}

	/**
	 * Checks wether the requested Stimmart exists or not
	 * 
	 * @param art
	 * @return
	 */
	static boolean stimmArtExists(String art) {

		for (StimmArt stimmArt : StimmArt.values()) {
			if (stimmArt.name.equals(art)) {
				return true;
			}
		}
		return false;
	}

}
