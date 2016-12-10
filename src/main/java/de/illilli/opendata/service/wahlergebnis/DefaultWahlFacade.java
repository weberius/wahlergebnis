package de.illilli.opendata.service.wahlergebnis;

import de.illilli.opendata.service.Facade;

/**
 * Diese Facade liefert die Information, dass keine
 *
 */
public class DefaultWahlFacade implements Facade {

	private String json = "{no data available}";

	public DefaultWahlFacade() {

	}

	@Override
	public String getJson() {
		return json;
	}

}
