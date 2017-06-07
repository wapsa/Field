package com.jersey.util.common;

import java.text.DateFormat;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonProvider {

	public static Gson gson = new GsonBuilder().setDateFormat(DateFormat.LONG).setPrettyPrinting().disableHtmlEscaping()
			.create();
}
