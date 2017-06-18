package de.illilli.opendata.service.wahlergebnis;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import com.google.gson.Gson;

import de.illilli.jdbc.ConnectionFactory;
import de.illilli.jdbc.Select;
import de.illilli.jdbc.SelectListDao;
import de.illilli.opendata.service.Facade;
import de.illilli.opendata.service.wahlergebnis.jdbc.DTO2Wahldaten;
import de.illilli.opendata.service.wahlergebnis.jdbc.SelectWahldaten;
import de.illilli.opendata.service.wahlergebnis.jdbc.WahldatenDTO;
import de.illilli.opendata.service.wahlergebnis.model.Wahldaten;

public class WahldatenFacade implements Facade {

	private List<Wahldaten> wahldatenList = new ArrayList<>();

	public WahldatenFacade() throws SQLException, NamingException, IOException {
		Connection conn = ConnectionFactory.getConnection();
		Select<WahldatenDTO> select = new SelectWahldaten();
		List<WahldatenDTO> dtoList = new SelectListDao<WahldatenDTO>(select, conn).execute();
		for (WahldatenDTO dto : dtoList) {
			Wahldaten wahldaten = new DTO2Wahldaten(dto);
			this.wahldatenList.add(wahldaten);
		}
	}

	@Override
	public String getJson() {
		return new Gson().toJson(wahldatenList);
	}

}
