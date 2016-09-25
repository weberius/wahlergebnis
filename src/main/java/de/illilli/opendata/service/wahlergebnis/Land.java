package de.illilli.opendata.service.wahlergebnis;

public enum Land {

	nrw(05, "Nordrhein-Westfalen", "nrw");

	private Land(int key, String fullName, String name) {
		this.key = key;
		this.fullName = fullName;
		this.name = name;
	}

	public String name;
	public String fullName;
	public int key;
}
