package de.illilli.opendata.service.wahlergebnis;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.google.gson.JsonSyntaxException;

import de.illilli.opendata.service.wahlergebnis.model.Wahldaten;

public class Data2WahldatenTest {

	InputStream data;

	@Before
	public void setUp() throws Exception {
		data = Data2Wahldaten.class.getResourceAsStream("/data.json");
	}

	@After
	public void tearDown() throws Exception {
		data.close();
	}

	@Test
	public void testWahldatenArt() throws JsonSyntaxException, IOException {

		Data2Wahldaten d2w = new Data2Wahldaten(data);
		Wahldaten wahldaten = d2w.getWahldaten();
		String expected = "landtagswahl";
		String actual = wahldaten.art;

		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testWahldatenBundesland() throws JsonSyntaxException, IOException {

		Data2Wahldaten d2w = new Data2Wahldaten(data);
		Wahldaten wahldaten = d2w.getWahldaten();
		String expected = "05";
		String actual = wahldaten.bundesland;

		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testWahldatenGemeinde() throws JsonSyntaxException, IOException {

		Data2Wahldaten d2w = new Data2Wahldaten(data);
		Wahldaten wahldaten = d2w.getWahldaten();
		String expected = "05315000";
		String actual = wahldaten.gemeinde;

		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testWahldatenDatum() throws JsonSyntaxException, IOException {

		Data2Wahldaten d2w = new Data2Wahldaten(data);
		Wahldaten wahldaten = d2w.getWahldaten();
		String expected = "11.11.2016";
		String actual = wahldaten.datum;

		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testWahldatenDatumParsable() throws JsonSyntaxException, IOException, ParseException {

		Data2Wahldaten d2w = new Data2Wahldaten(data);
		Wahldaten wahldaten = d2w.getWahldaten();
		Date expected = new SimpleDateFormat("dd.MM.yyyy").parse("11.11.2016");
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
		Date actual = sdf.parse(wahldaten.datum);

		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testWahldatenStimmbezirke() throws JsonSyntaxException, IOException {

		Data2Wahldaten d2w = new Data2Wahldaten(data);
		Wahldaten wahldaten = d2w.getWahldaten();
		int expected = 1;
		int actual = wahldaten.stimmbezirke.length;

		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testWahldatenStimmbezirk10101Nr() throws JsonSyntaxException, IOException {

		Data2Wahldaten d2w = new Data2Wahldaten(data);
		Wahldaten wahldaten = d2w.getWahldaten();
		int expected = 10101;
		int actual = wahldaten.stimmbezirke[0].nr;

		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testWahldatenStimmbezirk10101Abgegeben() throws JsonSyntaxException, IOException {

		Data2Wahldaten d2w = new Data2Wahldaten(data);
		Wahldaten wahldaten = d2w.getWahldaten();
		int expected = 500;
		int actual = wahldaten.stimmbezirke[0].abgegeben;

		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testWahldatenStimmbezirk10101Gueltig() throws JsonSyntaxException, IOException {

		Data2Wahldaten d2w = new Data2Wahldaten(data);
		Wahldaten wahldaten = d2w.getWahldaten();
		int expected = 450;
		int actual = wahldaten.stimmbezirke[0].gueltig;

		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testWahldatenStimmbezirk10101Ungueltig() throws JsonSyntaxException, IOException {

		Data2Wahldaten d2w = new Data2Wahldaten(data);
		Wahldaten wahldaten = d2w.getWahldaten();
		int expected = 50;
		int actual = wahldaten.stimmbezirke[0].ungueltig;

		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testWahldatenStimmbezirk10101Wahlberechtigt() throws JsonSyntaxException, IOException {

		Data2Wahldaten d2w = new Data2Wahldaten(data);
		Wahldaten wahldaten = d2w.getWahldaten();
		int expected = 1000;
		int actual = wahldaten.stimmbezirke[0].wahlberechtigt;

		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testWahldatenStimmbezirk10101Partei1Stimmen() throws JsonSyntaxException, IOException {

		Data2Wahldaten d2w = new Data2Wahldaten(data);
		Wahldaten wahldaten = d2w.getWahldaten();
		int expected = 123;
		int actual = wahldaten.stimmbezirke[0].ergebnisse[0].stimmen;

		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testWahldatenStimmbezirk10101Partei1Partei() throws JsonSyntaxException, IOException {

		Data2Wahldaten d2w = new Data2Wahldaten(data);
		Wahldaten wahldaten = d2w.getWahldaten();
		String expected = "Partei1";
		String actual = wahldaten.stimmbezirke[0].ergebnisse[0].partei;

		Assert.assertEquals(expected, actual);
	}

}
