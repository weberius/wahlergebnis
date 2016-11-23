package de.illilli.opendata.service.wahlergebnis;

import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

/**
 * <p>
 * Diese Klasse erlaubt es auf Basis von jersey Client die Schnittstelle
 * /wahlergebnis/service/data zu testen. Es handelt sich nicht um einen
 * automatischen Test, sondern muss manuell ausgeführt werden. Voraussetzung
 * ist, dass die Schnittstelle über localhost erreichbar ist.
 * </p>
 * <p>
 * Der Sourcecode zu diesem Test wurde einer Antwort bzgl. POST-REST Services
 * auf <a href="http://stackoverflow.com">stackoverflow</a> entnommen: <a href=
 * "http://stackoverflow.com/questions/13703807/post-in-restful-web-service">
 * POST in RESTful web service</a>
 * </p>
 */
public class JerseyClientPost {

	static final String DATA_URL = "http://localhost:8080/wahlergebnis/service/data";
	private static final Logger logger = Logger.getLogger(JerseyClientPost.class);

	public static void main(String[] args) throws Exception {

		Client client = Client.create();
		WebResource webResource = client.resource(JerseyClientPost.DATA_URL);

		InputStream inputStream = JerseyClientPost.class.getResourceAsStream("/data.json");
		String input = IOUtils.toString(inputStream);

		ClientResponse response = null;
		try {
			response = webResource.type("application/json").post(ClientResponse.class, input);
		} catch (ClientHandlerException e) {
			throw new Exception("SERVER NOT STARTET !!!");
		}

		if (response.getStatus() != 201) {
			throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
		}

		logger.info("Output from Server .... \n");
		String output = response.getEntity(String.class);
		logger.info(output);
	}
}
