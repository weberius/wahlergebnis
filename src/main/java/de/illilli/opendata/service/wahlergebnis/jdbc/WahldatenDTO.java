package de.illilli.opendata.service.wahlergebnis.jdbc;

import java.sql.Date;
import java.sql.Timestamp;

public class WahldatenDTO {

	private int id;
	private String art;
	private Date datum;
	private String bundesland;
	private String gemeinde;
	private Timestamp modtime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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

	public Timestamp getModtime() {
		return modtime;
	}

	public void setModtime(Timestamp modtime) {
		this.modtime = modtime;
	}

	@Override
	public String toString() {
		return "WahldatenDTO [id=" + id + ", art=" + art + ", datum=" + datum + ", bundesland=" + bundesland
				+ ", gemeinde=" + gemeinde + ", modtime=" + modtime + "]";
	}

}
