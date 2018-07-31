package net.farugames.api.core.lang;

import java.util.HashMap;

public enum Lang {
	ENGLISH(0, "English", "EN"), FRENCH(1, "Français", "FR"), SPANISH(2, "Español", "ES"), GERMAN(3, "Deutsch", "DE");

	private int id;
	private String name;
	private String code;
	private HashMap<String, String> words;

	Lang(int id, String name, String code) {
		this.id = id;
		this.name = name;
		this.code = code;
		this.words = new HashMap<String, String>();
	}

	public int getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}

	public String getCode() {
		return this.code;
	}

	public HashMap<String, String> getWords() {
		return this.words;
	}
}
