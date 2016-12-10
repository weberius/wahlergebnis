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
	 * @param land
	 * @param gemeinde
	 * @param date
	 * @param stimmbezirk
	 * @return
	 * @throws SQLException
	 * @throws NamingException
	 * @throws IOException
	 * @throws ParseException
	 */
	public static Facade getFacade(Wahl wahl, String land, String gemeinde, String datum, int stimmbezirk)
			throws SQLException, NamingException, IOException, ParseException {
		Facade facade = new DefaultWahlFacade();
		if (wahl.equals(Wahl.landtagswahl)) {
			if (Land.nrw.key.equals(land)) {
				facade = new LandtagswahlNRWFacade(land, gemeinde, datum, stimmbezirk);
			}
		}
		return facade;
	}
}
