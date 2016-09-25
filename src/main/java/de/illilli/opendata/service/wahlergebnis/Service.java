package de.illilli.opendata.service.wahlergebnis;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import de.illilli.opendata.service.Config;
import de.illilli.opendata.service.Facade;

/**
 * Die Service Klasse definiert die Schnittstellen für den Server nach ausen.
 */
@Path("/")
public class Service {

	private final static Logger logger = Logger.getLogger(Service.class);
	public static final String ENCODING = Config.getProperty("encoding");

	@Context
	private HttpServletRequest request;
	@Context
	private HttpServletResponse response;

	/**
	 * Method for checking the state of the service.
	 * 
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/ping")
	public String getPing() {
		return "{alive}";
	}

	/**
	 * 
	 * 
	 * @param year
	 *            Das Jahr der Wahl
	 * @param gemeinde
	 *            Gemeindeschlüssel, zur Zeit nur für Köln (05315000). String,
	 *            weil mit führender '0'.
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/landtagswahl/nrw/koeln/{year}/")
	public String getLandtagswahl(@PathParam("year") int year) {
		Facade facade = new LandtagswahlNRWFacade(Gemeinde.koeln, year);
		return facade.getJson();
	}

	/**
	 * <p>
	 * Dieser Service holt die Daten z.B. von offene Daten Köln und schreibt die
	 * aktuellen Werte in die Datenbank. Hierfür wird die http-PUT Methode
	 * verwendet. Der erste oder erneute Aufruf dieser Schnittstelle führt dazu,
	 * dass bisherige Daten gelöscht und ggf. aktualisierte Daten eingelesen
	 * werden.
	 * </p>
	 * 
	 * <p>
	 * Das Beispiel zeigt, wie die Wahlergebnisse für die 2012er Landtagswahl
	 * Nordrheinwestfalen in Köln geladen werden.
	 * <code>curl -X PUT http://localhost:8080/wahlergebnis/service/put/nrw/koeln/2012</code>
	 * </p>
	 * 
	 * @param year
	 * @return
	 */
	@PUT
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/put/landtagswahl/nrw/koeln/{year}")
	public String putLandtagswahl(@PathParam("year") int year) {
		Facade facade = PutWahlFacadeFactory.getFacade("landtagswahl", "nrw", Gemeinde.koeln.key, year);
		String msg = facade.getJson();
		logger.info(msg);
		return msg;
	}

}
