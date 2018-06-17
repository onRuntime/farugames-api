package net.faru.api.player.languages;

public enum Languages {

	FRENCH("fr_FR.lang", "French", "Français", "Französisch", "Francés"),
	ENGLISH("en_US.lang", "English", "Anglais", "Englisch", "Inglés"),
	GERMAN("de_DE.lang", "German", "Allemand", "Deutsche", "Alemán"),
	SPANISH("sp_SP.lang", "Spanish", "Espagnol", "Spanisch", "Español");
	
	private String file;
	
	private String english;
	private String french;
	private String german;
	private String spanish;
	
	Languages(String file, String english, String french, String german, String spanish) {
		this.file = file;
		this.english = english;
		this.french = french;
		this.german = german;
		this.spanish = spanish;
	}
	
	public String getFile() {
		return this.file;
	}
}
