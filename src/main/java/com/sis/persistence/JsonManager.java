package com.sis.persistence;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonManager {

	public static String toJson(Object obj) {
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd-HH:mm:ss").create();
		return gson.toJson(obj);
	}
}
