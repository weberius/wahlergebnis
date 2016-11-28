package de.illilli.opendata.service.wahlergebnis.jdbc;

import java.sql.Timestamp;

public class ErgebnisDTO {

	private int id;
	private String partei;
	private int stimmen;
	private Timestamp modtime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPartei() {
		return partei;
	}

	public void setPartei(String partei) {
		this.partei = partei;
	}

	public int getStimmen() {
		return stimmen;
	}

	public void setStimmen(int stimmen) {
		this.stimmen = stimmen;
	}

	public Timestamp getModtime() {
		return modtime;
	}

	public void setModtime(Timestamp modtime) {
		this.modtime = modtime;
	}

	@Override
	public String toString() {
		return "ErgebnisDTO [id=" + id + ", partei=" + partei + ", stimmen=" + stimmen + ", modtime=" + modtime + "]";
	}

}
