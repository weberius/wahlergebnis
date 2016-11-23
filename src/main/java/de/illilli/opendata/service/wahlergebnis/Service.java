package de.illilli.opendata.service.wahlergebnis;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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
	 * Dieser Service nimmt eine json - Struktur per POST entgegen. 
	 * </p>
	 * 
	 * @return
	 */
	@POST
	@Consumes({MediaType.APPLICATION_JSON})
	@Path("/data")
	public Response putData(String data) {
		String result = "Data post: " + data;
		return Response.status(201).entity(result).build(); 
	}

}
