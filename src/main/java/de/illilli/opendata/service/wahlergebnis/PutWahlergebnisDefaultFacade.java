package de.illilli.opendata.service.wahlergebnis;

import de.illilli.opendata.service.Config;
import de.illilli.opendata.service.Facade;

public class PutWahlergebnisDefaultFacade implements Facade {

	public String getJson() {
		return Config.getProperty("wahlergebnis.put.default");
	}

}
