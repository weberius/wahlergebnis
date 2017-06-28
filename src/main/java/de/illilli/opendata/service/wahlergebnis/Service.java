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
import de.illilli.opendata.service.DefaultFacade;
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
	 * <p>
	 * Beispiel:
	 * <a href="http://localhost:8080/wahlergebnis/service/ping"> /wahlergebnis/
	 * service/ping</a>
	 * </p>
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
	 * <p>
	 * Dieser Service stellt Wahlergebnisse auf Gemeindeebene zur Verfügung.
	 * </p>
	 * <p>
	 * Beispiel:
	 * <ul>
	 * <li><a href=
	 * "http://localhost:8080/wahlergebnis/service/landtagswahl/05/05315000/2012-05-13/erststimmen">
	 * /landtagswahl/05/05315000/2012-05-13/erststimmen</a></li>
	 * <li><a href=
	 * "http://localhost:8080/wahlergebnis/service/landtagswahl/05/05315000/2012-05-13/zweitstimmen">
	 * /landtagswahl/05/05315000/2012-05-13/zweitstimmen</a></li>
	 * </ul>
	 * 
	 * @param wahl
	 *            Ob es sich um die 'landtagswahl' oder 'bundestagswahl'
	 *            handelt; Z.Zt. nur 'landtagswahl'
	 * @param land
	 *            Das Bundesland mit einheitlichem Schlüssel; Z.Zt. '05' für
	 *            Nordrhein-Westfalen
	 * @param gemeinde
	 *            Die Gemeinde für die die Ergebnisse vorliegen; Z.Zt.
	 *            '05315000' für Köln
	 * @param datum
	 *            Datum im Format 'yyyy-MM-dd'
	 * @param art
	 *            Ob 'erststimmen' oder 'zweitstimmen'
	 * @return
	 * @throws SQLException
	 * @throws NamingException
	 * @throws IOException
	 * @throws ParseException
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/{wahl}/{land}/{gemeinde}/{datum}/{art}")
	public String getWahlNachArt(@PathParam("wahl") String wahl, @PathParam("land") String land,
			@PathParam("gemeinde") String gemeinde, @PathParam("datum") String datum, @PathParam("art") String art)
			throws SQLException, NamingException, IOException, ParseException {

		logger.info("/" + wahl + "/" + land + "/" + gemeinde + "/" + datum + "/" + art + " called");

		request.setCharacterEncoding(Config.getProperty("encoding"));
		response.setCharacterEncoding(Config.getProperty("encoding"));

		Facade facade = WahlFacadeFactory.getFacade(wahl, land, gemeinde, datum, art);
		return facade.getJson();
	}

	/**
	 * 
	 * <p>
	 * Beispiel:
	 * <ul>
	 * <li><a href=
	 * "http://localhost:8080/wahlergebnis/service/landtagswahl/05/05315000/2012-05-13/erststimmen/10101">
	 * /landtagswahl/05/05315000/{year}/{art}/{stimmbezirk}</a></li>
	 * <li><a href=
	 * "http://localhost:8080/wahlergebnis/service/landtagswahl/05/05315000/2012-05-13/erststimmen/10101,10102,10103">
	 * /landtagswahl/05/05315000/{year}/{art}/{stimmbezirk,stimmbezirk,
	 * stimmbezirk}</a></li>
	 * <li><a href=
	 * "http://localhost:8080/wahlergebnis/service/landtagswahl/05/05315000/2012-05-13/zweitstimmen/10101">
	 * /landtagswahl/05/05315000/{year}/{art}/{stimmbezirk}</a></li>
	 * </ul>
	 * 
	 * @param land
	 * @param gemeinde
	 * @param datum
	 * @param nr
	 * @param art
	 *            Stimmart - erststimmen oder zweitstimmen
	 * @return
	 * @throws SQLException
	 * @throws NamingException
	 * @throws IOException
	 * @throws ParseException
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/{wahl}/{land}/{gemeinde}/{datum}/{art}/{nr}")
	public String getWahlNachArtUndStimmbezirk(@PathParam("wahl") String wahl, @PathParam("land") String land,
			@PathParam("gemeinde") String gemeinde, @PathParam("datum") String datum, @PathParam("art") String art,
			@PathParam("nr") String nr) throws SQLException, NamingException, IOException, ParseException {

		logger.info("/" + wahl + "/" + land + "/" + gemeinde + "/" + datum + "/" + art + "/" + nr + " called");

		request.setCharacterEncoding(Config.getProperty("encoding"));
		response.setCharacterEncoding(Config.getProperty("encoding"));

		Facade facade = WahlFacadeFactory.getFacade(wahl, land, gemeinde, datum, art, nr);
		return facade.getJson();
	}

	/**
	 * <p>
	 * Gibt alle Wahldaten in Form einer json-formatierten Liste aus. Kann mit
	 * ?datatable aufgerufen werden, um ohne weiteres mit einer
	 * <a href="">datatable</a> benutzen zu können.
	 * </p>
	 * <p>
	 * Beispiele:
	 * <ul>
	 * <li><a href="http://localhost:8080/wahlergebnis/service/wahldaten">
	 * /wahlergebnis/service/wahldaten</a></li>
	 * <li><a href=
	 * "http://localhost:8080/wahlergebnis/service/wahldaten?datatables">
	 * /wahlergebnis/service/wahldaten?datatables</a></li>
	 * </ul>
	 * </p>
	 * 
	 * @return
	 * @throws SQLException
	 * @throws NamingException
	 * @throws IOException
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/wahldaten")
	public String getWahldaten() throws SQLException, NamingException, IOException {

		request.setCharacterEncoding(Config.getProperty("encoding"));
		response.setCharacterEncoding(Config.getProperty("encoding"));

		boolean isDatatables = request.getParameter("datatables") != null;

		Facade facade = null;
		if (isDatatables) {
			facade = new WahldatenDatatablesFacade();
		} else {
			facade = new WahldatenFacade();
		}
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
		if ("localhost".equals(request.getServerName())) {
			facade = new PutWahlergebnisFacade(data);
		} else {
			facade = new DefaultFacade(DefaultFacade.INFO, "Data not loaded; Method only available on localhost");
		}

		return Response.status(201).entity(facade.getJson()).build();
	}

}
