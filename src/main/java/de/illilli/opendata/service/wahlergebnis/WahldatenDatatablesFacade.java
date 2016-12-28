package de.illilli.opendata.service.wahlergebnis;

import java.io.IOException;
import java.sql.SQLException;

import javax.naming.NamingException;

public class WahldatenDatatablesFacade extends WahldatenFacade {

	public WahldatenDatatablesFacade() throws SQLException, NamingException, IOException {
		super();
	}

	@Override
	public String getJson() {
		return "{\"data\":" + super.getJson() + "}";
	}

}
