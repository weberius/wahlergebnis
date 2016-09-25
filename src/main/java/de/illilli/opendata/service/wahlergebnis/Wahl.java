package de.illilli.opendata.service.wahlergebnis;

public enum Wahl {

	landtagswahl("landtagswahl"), bundestagswahl("landtagswahl"), kommunalwahl("landtagswahl");

	private Wahl(String name) {
		this.name = name;
	}

	public String name;
}
