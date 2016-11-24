package de.illilli.opendata.service.wahlergebnis;

public enum Wahl {

	landtagswahl("landtagswahl"), bundestagswahl("bundestagswahl"), kommunalwahl("kommunalwahl");

	private Wahl(String name) {
		this.name = name;
	}

	public String name;
}
