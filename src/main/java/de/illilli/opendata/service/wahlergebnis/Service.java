package de.illilli.opendata.service.wahlergebnis;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.google.gson.JsonSyntaxException;

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
	 * Beispiel: <a href=
	 * "http://localhost:8080/wahlergebnis/service/landtagswahl/05/05315000/2012-05-13/10101">
	 * /landtagswahl/05/05315000/{year}/{stimmbezirk}</a>
	 * 
	 * @param year
	 *            Das Jahr der Wahl
	 * @param gemeinde
	 *            Gemeindeschlüssel, zur Zeit nur für Köln (05315000). String,
	 *            weil mit führender '0'.
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 * @throws NamingException
	 * @throws SQLException
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/landtagswahl/{land}/{gemeinde}/{datum}/{nr}")
	public String getLandtagswahl(@PathParam("land") String land, @PathParam("gemeinde") String gemeinde,
			@PathParam("datum") String datum, @PathParam("nr") int nr)
			throws SQLException, NamingException, IOException, ParseException {

		logger.info("/landtagswahl/" + land + "/" + gemeinde + "/" + datum + "/" + nr + " called");

		request.setCharacterEncoding(Config.getProperty("encoding"));
		response.setCharacterEncoding(Config.getProperty("encoding"));

		Facade facade = WahlFacadeFactory.getFacade(Wahl.landtagswahl, land, gemeinde, datum, nr);
		return facade.getJson();
	}

	/**
	 * <p>
	 * Dieser Service nimmt eine json - Struktur per POST entgegen.
	 * </p>
	 * 
	 * @return
	 * @throws IOException
	 * @throws JsonSyntaxException
	 * @throws NamingException
	 * @throws SQLException
	 * @throws ParseException
	 */
	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/data")
	public Response putData(String data)
			throws JsonSyntaxException, IOException, ParseException, SQLException, NamingException {
		Facade facade = new PutWahlergebnisFacade(data);
		return Response.status(201).entity(facade.getJson()).build();
	}

}
