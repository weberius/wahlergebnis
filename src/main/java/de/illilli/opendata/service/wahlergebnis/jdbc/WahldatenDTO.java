package de.illilli.opendata.service.wahlergebnis.jdbc;

import java.sql.Date;
import java.sql.Timestamp;

public class WahldatenDTO {

	private int id;
	private String art;
	private String wahl;
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

	public String getWahl() {
		return wahl;
	}

	public void setWahl(String wahl) {
		this.wahl = wahl;
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((art == null) ? 0 : art.hashCode());
		result = prime * result + ((bundesland == null) ? 0 : bundesland.hashCode());
		result = prime * result + ((datum == null) ? 0 : datum.hashCode());
		result = prime * result + ((gemeinde == null) ? 0 : gemeinde.hashCode());
		result = prime * result + id;
		result = prime * result + ((modtime == null) ? 0 : modtime.hashCode());
		result = prime * result + ((wahl == null) ? 0 : wahl.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WahldatenDTO other = (WahldatenDTO) obj;
		if (art == null) {
			if (other.art != null)
				return false;
		} else if (!art.equals(other.art))
			return false;
		if (bundesland == null) {
			if (other.bundesland != null)
				return false;
		} else if (!bundesland.equals(other.bundesland))
			return false;
		if (datum == null) {
			if (other.datum != null)
				return false;
		} else if (!datum.equals(other.datum))
			return false;
		if (gemeinde == null) {
			if (other.gemeinde != null)
				return false;
		} else if (!gemeinde.equals(other.gemeinde))
			return false;
		if (id != other.id)
			return false;
		if (modtime == null) {
			if (other.modtime != null)
				return false;
		} else if (!modtime.equals(other.modtime))
			return false;
		if (wahl == null) {
			if (other.wahl != null)
				return false;
		} else if (!wahl.equals(other.wahl))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "WahldatenDTO [id=" + id + ", art=" + art + ", wahl=" + wahl + ", datum=" + datum + ", bundesland="
				+ bundesland + ", gemeinde=" + gemeinde + ", modtime=" + modtime + "]";
	}

}
