package de.illilli.opendata.service.wahlergebnis.model;

/**
 * 
 * <pre>
 * { 
  "art":"erststimmen",
  "wahl":"landtagswahl",
  "datum":"11.11.2016",
  "bundesland":"05",
  "gemeinde":"05315000",
  "wahlergebnis":{
    "wahlberechtigt":1000,
    "abgegeben":500,
    "gueltig":450,
    "ungueltig":50,
    "ergebnisse":[{
      "partei":"Partei1",
      "stimmen":123
     }
  }]
}
 * </pre>
 * 
 * *
 */
public class WahldatenGemeinde {

	public String art;
	public String wahl;
	public String bundesland;
	public String gemeinde;
	public String datum;
	public Gemeinde wahlergebnis;

}
