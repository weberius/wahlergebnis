package de.illilli.opendata.service.wahlergebnis.model;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

public class Data2Wahldaten {

	private Wahldaten wahldaten = new Wahldaten();

	public Data2Wahldaten(InputStream data) throws JsonSyntaxException, IOException {
		this(IOUtils.toString(data));
	}

	public Data2Wahldaten(String data) throws JsonSyntaxException, IOException {
		wahldaten = new GsonBuilder().create().fromJson(data, wahldaten.getClass());
	}

	public Wahldaten getWahldaten() {
		return wahldaten;
	}
}
