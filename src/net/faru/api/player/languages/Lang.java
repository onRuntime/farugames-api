package net.faru.api.player.languages;

public enum Lang {

	FRENCH("French", "Français", "Französisch", "Francés"),
	ENGLISH("English", "Anglais", "Englisch", "Inglés"),
	GERMAN("German", "Allemand", "Deutsche", "Alemán"),
	SPANISH("Spanish", "Espagnol", "Spanisch", "Español"),
	
	JOIN_MESSAGE("", "", "", ""),
	
	ERROR_MESSAGE("", "", "", "");
		
	public String english, french, german, spanish;
	
	Lang(String english, String french, String german, String spanish) {
		this.english = english;
		this.french = french;
		this.german = german;
		this.spanish = spanish;
	}
	
	public static Lang getByString(String language) {
		return Lang.valueOf(language) != null ? Lang.valueOf(language) : Lang.ENGLISH;
	}
	
	public String in(Lang language) {
		switch(language) {
			case FRENCH:
				return this.french;
			case ENGLISH:
				return this.english;
			case GERMAN:
				return this.german;
			case SPANISH:
				return this.spanish;
			default:
				return this.english;
		}
	}
}
