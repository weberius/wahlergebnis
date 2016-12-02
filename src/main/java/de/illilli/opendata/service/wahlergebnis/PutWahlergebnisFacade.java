package de.illilli.opendata.service.wahlergebnis;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import javax.naming.NamingException;

import org.apache.log4j.Logger;

import de.illilli.jdbc.UpdateData;
import de.illilli.opendata.service.Facade;
import de.illilli.opendata.service.wahlergebnis.jdbc.DeleteWahlergebnis;
import de.illilli.opendata.service.wahlergebnis.jdbc.InsertWahlergebnis;
import de.illilli.opendata.service.wahlergebnis.model.Data2Wahldaten;
import de.illilli.opendata.service.wahlergebnis.model.Wahldaten;

/**
 * Lese die Werte für die Landtagswahl von offene Daten Köln ein und schreibe
 * sie in die Datenbank.
 *
 */
public class PutWahlergebnisFacade implements Facade {

	private static final Logger logger = Logger.getLogger(PutWahlergebnisFacade.class);
	private String msg = "landtagswahl 05 05315000";

	/**
	 * 
	 * @param gemeinde
	 * @param year
	 * @throws ParseException
	 * @throws IOException
	 * @throws NamingException
	 * @throws SQLException
	 */
	public PutWahlergebnisFacade(String data) throws ParseException, SQLException, NamingException, IOException {
		// Überführe die Daten in eine Java-Struktur
		Wahldaten wahldaten = new Data2Wahldaten(data).getWahldaten();
		// Lösche die bisherigen Werte für diesen Datensatz
		UpdateData delete = new DeleteWahlergebnis(wahldaten);
		delete.closeConnection();
		logger.info("'" + delete.getUpdated() + "'  deleted");
		delete.getUpdated();
		// Füge die Daten in die Datenbank ein.
		InsertWahlergebnis insert = new InsertWahlergebnis(wahldaten);
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
