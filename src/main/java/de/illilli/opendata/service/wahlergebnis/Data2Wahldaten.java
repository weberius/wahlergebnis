package de.illilli.opendata.service.wahlergebnis;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import de.illilli.opendata.service.wahlergebnis.model.Wahldaten;

public class Data2Wahldaten {

	private Wahldaten wahldaten = new Wahldaten();

	public Data2Wahldaten(InputStream data) throws JsonSyntaxException, IOException {
		wahldaten = new GsonBuilder().create().fromJson(IOUtils.toString(data), wahldaten.getClass());
	}

	public Wahldaten getWahldaten() {
		return wahldaten;
	}
}
