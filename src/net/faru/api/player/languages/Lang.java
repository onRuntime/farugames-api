package net.faru.api.player.languages;

import org.bukkit.ChatColor;

public enum Lang {
	
	/*  ========== LANGUAGES ==========  */
	FRENCH("French", "Français", "", ""),
	ENGLISH("English", "Anglais", "", ""),
	GERMAN("German", "Allemand", "", ""),
	SPANISH("Spanish", "Espagnol", "", ""),
	/* ================================ */
	
	
	/*  ========== TYPES ==========  */
	ERROR(ChatColor.DARK_RED + "" + ChatColor.BOLD + "✘" + ChatColor.WHITE + "" + ChatColor.BOLD + " ∣ " + ChatColor.RED + "" + ChatColor.BOLD + "Error :",
			ChatColor.DARK_RED + "" + ChatColor.BOLD + "✘" + ChatColor.WHITE + "" + ChatColor.BOLD + " ∣ " + ChatColor.RED + "" + ChatColor.BOLD + "Erreur :",
			"",
			""),
	/* ============================== */
	
	
	/*  ========== GENERAL ==========  */
	NO_PERMISSION_MESSAGE(ChatColor.RED + "You do not have the permission.",
			ChatColor.RED + "Vous n'avez pas la permission.",
			"",
			""),
	
	ERROR_HAS_OCCURRED(ChatColor.RED + "An error has occurred.",
			ChatColor.RED + "Une erreur est survenue.",
			"",
			""),
	/* ============================== */
	
	
	/*  ========== MAINTENANCE ==========  */
	WORKING_FEATURE(ChatColor.RED + "This feature is in work.",
			ChatColor.RED + "Cette fonctionnalité est en développement.",
			"",
			""),
	/* ============================== */
	
	
	/*  ========== MAINTENANCE ==========  */
	MAINTENANCE_ENABLE(ChatColor.GRAY + "The maintenance is now " + ChatColor.GREEN + "enabled" + ChatColor.GRAY + ".",
			ChatColor.GRAY + "La maintenance est maintenant " + ChatColor.GREEN + "activée" + ChatColor.GRAY + ".",
			"",
			""),
	
	MAINTENANCE_DISABLE(ChatColor.GRAY + "The maintenance is now " + ChatColor.RED + "disabled" + ChatColor.GRAY + ".",
			ChatColor.GRAY + "La maintenance est maintenant " + ChatColor.RED + "désactivée" + ChatColor.GRAY + ".",
			"",
			""),
	
	MAINTENANCE_ALREADY_ENABLED(ChatColor.RED + "The maintenance is already enabled.",
			ChatColor.RED + "La maintenance est déjà activée.",
			"",
			""),
	
	MAINTENANCE_ALREADY_DISABLED(ChatColor.RED + "The maintenance is already disabled.",
			ChatColor.RED + "La maintenance est déjà désactivée.",
			"",
			""),
	/* ============================== */
	
	
	/*  ========== JOIN ==========  */
	JOIN_MESSAGE(ChatColor.GRAY + " has joined the Hub !", ChatColor.GRAY + " a rejoint le Hub !", "", ""),
	GAME_JOIN_MESSAGE(ChatColor.GRAY + " has joined the game !", ChatColor.GRAY + " a rejoint la partie !", "", "");
	/* =========================== */
		
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
		String message = this.english;
		switch(language) {
			case FRENCH:
				message = this.french;
			case GERMAN:
				message = this.german;
			case SPANISH:
				message = this.spanish;
			default:
				break;
		}
		return message + ChatColor.RESET;
	}
}
