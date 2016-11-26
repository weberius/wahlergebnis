package de.illilli.opendata.service.wahlergebnis.jdbc;

import java.sql.Date;

public class WahldatenDTO {

	private String art;
	private Date datum;
	private String bundesland;
	private String gemeinde;

	public String getArt() {
		return art;
	}

	public void setArt(String art) {
		this.art = art;
	}

	public Date getDatum() {
		return datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public String getBundesland() {
		return bundesland;
	}

	public void setBundesland(String bundesland) {
		this.bundesland = bundesland;
	}

	public String getGemeinde() {
		return gemeinde;
	}

	public void setGemeinde(String gemeinde) {
		this.gemeinde = gemeinde;
	}

}
