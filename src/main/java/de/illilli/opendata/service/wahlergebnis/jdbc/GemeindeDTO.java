package de.illilli.opendata.service.wahlergebnis.jdbc;

import java.sql.Timestamp;

public class GemeindeDTO {

	private int wahlberechtigt;
	private int abgegeben;
	private int gueltig;
	private int ungueltig;
	private Timestamp modtime;

	public int getWahlberechtigt() {
		return wahlberechtigt;
	}

	public void setWahlberechtigt(int wahlberechtigt) {
		this.wahlberechtigt = wahlberechtigt;
	}

	public int getAbgegeben() {
		return abgegeben;
	}

	public void setAbgegeben(int abgegeben) {
		this.abgegeben = abgegeben;
	}

	public int getGueltig() {
		return gueltig;
	}

	public void setGueltig(int gueltig) {
		this.gueltig = gueltig;
	}

	public int getUngueltig() {
		return ungueltig;
	}

	public void setUngueltig(int ungueltig) {
		this.ungueltig = ungueltig;
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
		result = prime * result + abgegeben;
		result = prime * result + gueltig;
		result = prime * result + ((modtime == null) ? 0 : modtime.hashCode());
		result = prime * result + ungueltig;
		result = prime * result + wahlberechtigt;
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
		GemeindeDTO other = (GemeindeDTO) obj;
		if (abgegeben != other.abgegeben)
			return false;
		if (gueltig != other.gueltig)
			return false;
		if (modtime == null) {
			if (other.modtime != null)
				return false;
		} else if (!modtime.equals(other.modtime))
			return false;
		if (ungueltig != other.ungueltig)
			return false;
		if (wahlberechtigt != other.wahlberechtigt)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "GemeindeDTO [wahlberechtigt=" + wahlberechtigt + ", abgegeben=" + abgegeben + ", gueltig=" + gueltig
				+ ", ungueltig=" + ungueltig + ", modtime=" + modtime + "]";
	}

}
