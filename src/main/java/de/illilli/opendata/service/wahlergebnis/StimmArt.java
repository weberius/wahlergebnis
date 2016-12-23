package de.illilli.opendata.service.wahlergebnis;

public enum StimmArt {

	erststimmen("erststimmen"), zweitstimmen("zweitstimmen");

	private StimmArt(String name) {
		this.name = name;
	}

	public String name;

}
