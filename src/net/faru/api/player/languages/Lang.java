package net.faru.api.player.languages;

import net.md_5.bungee.api.ChatColor;

public enum Lang {
	
	/*  ========== LANGUAGES ==========  */
	FRENCH("French", "Français", "Französische", "Francès"),
	ENGLISH("English", "Anglais", "Englisch", "Inglès"),
	GERMAN("German", "Allemand", "Deutsch", "Alemán"),
	SPANISH("Spanish", "Espagnol", "Spanisch", "Español"),
	/* ================================ */
	
	
	/*  ========== TYPES ==========  */
	ERROR(ChatColor.DARK_RED + "" + ChatColor.BOLD + "✘" + ChatColor.WHITE + "" + ChatColor.BOLD + " ∣ " + ChatColor.RED + "" + ChatColor.BOLD + "Error :",
			ChatColor.DARK_RED + "" + ChatColor.BOLD + "✘" + ChatColor.WHITE + "" + ChatColor.BOLD + " ∣ " + ChatColor.RED + "" + ChatColor.BOLD + "Erreur :",
			ChatColor.DARK_RED + "" + ChatColor.BOLD + "✘" + ChatColor.WHITE + "" + ChatColor.BOLD + " ∣ " + ChatColor.RED + "" + ChatColor.BOLD + "Fehler :",
			null),
	/* ============================ */
	
	
	/*  ========== PLAYER ==========  */
	BAD_ACCOUNT(ChatColor.RED + "Our systems are having some problems with this player account. Contact an administrator for more informations.",
			ChatColor.RED + "Nos systèmes rencontrent des problèmes avec ce compte joueur. Contactez un administrateur pour plus d'informations.",
			"",
			ChatColor.RED + "Nuestros sistemas encuentran problemas con la cuenta del jugador. Contacte al administrador para obtener más informaciones."),
	
	ALREADY_CONNECTED("This account is already connected.",
			"Ce compte est déjà connecté.",
			"",
			""),
	/* ============================= */
	
	
	/*  ========== GENERAL ==========  */
	NO_PERMISSION_MESSAGE(ChatColor.RED + "You do not have the permission.",
			ChatColor.RED + "Vous n'avez pas la permission.",
			ChatColor.RED + "",
			ChatColor.RED + "Usted no tiene el permiso."),
	
	ERROR_HAS_OCCURRED(ChatColor.RED + "An error has occurred.",
			ChatColor.RED + "Une erreur est survenue.",
			ChatColor.RED + "",
			ChatColor.RED + "Ocurrió un error."),
	
	NORMAL_MOTD("",
			"",
			"",
			""),
	
	SERVER_REQUESTED(ChatColor.GOLD + "Your server " + ChatColor.YELLOW + "%server%" + ChatColor.GOLD + " has been requested, wait a few moment for creation.",
			ChatColor.GOLD + "Votre serveur " + ChatColor.YELLOW + "%server%" + ChatColor.GOLD + " a été demandé, pentientez un moment pour sa création.",
			"",
			""),
	
	SERVER_REMOVE(ChatColor.GOLD + "Your server " + ChatColor.YELLOW + "%server%" + ChatColor.GOLD + " will be removed in a few moment.",
			ChatColor.GOLD + "Votre serveur " + ChatColor.YELLOW + "%server%" + ChatColor.GOLD + " va être supprimé dans quelques instants.",
			"",
			""),
	
	COMMAND_MESSAGE("",
			"",
			"",
			""),
	
	ONLINE_PLAYERS(ChatColor.RED + "FaruGames " + ChatColor.WHITE + "" + ChatColor.BOLD + "» " + ChatColor.YELLOW + "There are currently " + ChatColor.AQUA +
						"" + ChatColor.BOLD + "%players% " + ChatColor.YELLOW + "players online.",
			ChatColor.RED + "FaruGames " + ChatColor.WHITE + "" + ChatColor.BOLD + "» " + ChatColor.YELLOW + "Il y a actuellement " + ChatColor.AQUA +
				"" + ChatColor.BOLD + "%players% " + ChatColor.YELLOW + "joueur en ligne.",
			"",
			""),
	/* ============================== */
	
	
	/*  ========== FEATURES ==========  */
	WORKING_FEATURE(ChatColor.RED + "This feature is under development.",
			ChatColor.RED + "Cette fonctionnalité est en développement.",
			ChatColor.RED + "Dieses feature ist in der Entwicklung.",
			ChatColor.RED + "Esta funcionalidad es en desarrollo."),
	/* ============================== */
	
	
	/*  ========== MAINTENANCE ==========  */
	MAINTENANCE_ENABLE(ChatColor.GRAY + "The maintenance is now " + ChatColor.GREEN + "enabled" + ChatColor.GRAY + ".",
			ChatColor.GRAY + "La maintenance est maintenant " + ChatColor.GREEN + "activée" + ChatColor.GRAY + ".",
			ChatColor.RED + "",
			ChatColor.RED + "El mantenimiento ahora es activado."),
	
	MAINTENANCE_DISABLE(ChatColor.GRAY + "The maintenance is now " + ChatColor.RED + "disabled" + ChatColor.GRAY + ".",
			ChatColor.GRAY + "La maintenance est maintenant " + ChatColor.RED + "désactivée" + ChatColor.GRAY + ".",
			ChatColor.RED + "",
			ChatColor.RED + "El mantenimiento ahora es desactivado."),
	
	MAINTENANCE_ALREADY_ENABLED(ChatColor.RED + "The maintenance is already enabled.",
			ChatColor.RED + "La maintenance est déjà activée.",
			ChatColor.RED + "",
			ChatColor.RED + "El mantenimiento es ya activado."),
	
	MAINTENANCE_ALREADY_DISABLED(ChatColor.RED + "The maintenance is already disabled.",
			ChatColor.RED + "La maintenance est déjà désactivée.",
			ChatColor.RED + "",
			ChatColor.RED + "El mantenimiento es ya desactivado."),
	
	MAINTENANCE_MOTD(ChatColor.RED + "" + ChatColor.BOLD + "F" + ChatColor.YELLOW + "" + ChatColor.BOLD + "a" + ChatColor.GREEN + "" + ChatColor.BOLD + "r"
						+ ChatColor.AQUA + "" + ChatColor.BOLD + "u" + ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "Games"
						+ ChatColor.WHITE +  ".net" + ChatColor.GRAY + " «» " + ChatColor.GOLD + "Innovative Server" + ChatColor.GRAY + " «» " + ChatColor.WHITE + "["
						+ ChatColor.AQUA +  "1.9" + ChatColor.WHITE + " - " + ChatColor.AQUA + "1.12" + ChatColor.WHITE + "]\n      " + ChatColor.DARK_RED + ""
						+ ChatColor.BOLD + "⚠ " + ChatColor.RED + "" + ChatColor.BOLD + "Currently Under Development" + ChatColor.DARK_RED + "" + ChatColor.BOLD + " ⚠",
			ChatColor.RED + "" + ChatColor.BOLD + "F" + ChatColor.YELLOW + "" + ChatColor.BOLD + "a" + ChatColor.GREEN + "" + ChatColor.BOLD + "r"
					+ ChatColor.AQUA + "" + ChatColor.BOLD + "u" + ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "Games"
					+ ChatColor.WHITE +  ".net" + ChatColor.GRAY + " «» " + ChatColor.GOLD + "Serveur Innovant" + ChatColor.GRAY + " «» " + ChatColor.WHITE + "["
					+ ChatColor.AQUA +  "1.9" + ChatColor.WHITE + " - " + ChatColor.AQUA + "1.12" + ChatColor.WHITE + "]\n      " + ChatColor.DARK_RED + ""
					+ ChatColor.BOLD + "⚠ " + ChatColor.RED + "" + ChatColor.BOLD + "Actuellement en développement" + ChatColor.DARK_RED + "" + ChatColor.BOLD + " ⚠",
			"",
			""),
	
	MAINTENANCE_DISCONNECT_MESSAGE("",
			"",
			"",
			""),
	/* ============================== */
	
	
	/*  ========== JOIN ==========  */
	JOIN_MESSAGE("%player_rank_prefix% %player%" + ChatColor.GRAY + " has joined the Hub !",
			"%player_rank_prefix% %player%" + ChatColor.GRAY + " a rejoint le Hub !",
			"%player_rank_prefix% %player%" + ChatColor.GRAY + "",
			"%player_rank_prefix% %player%" + ChatColor.GRAY + " entro en el Hub !"),
	
	GAME_JOIN_MESSAGE("%player_rank_prefix% %player%" + ChatColor.YELLOW + " has joined the game !",
			"%player_rank_prefix% %player%" + ChatColor.YELLOW + " a rejoint la partie !",
			"%player_rank_prefix% %player%" + ChatColor.YELLOW + "",
			"%player_rank_prefix% %player%" + ChatColor.YELLOW + " entro en la partida !"),
	/* =========================== */
	
	
	/*  ========== SANCTION ==========  */
	UNPUNISHABLE_PLAYER(ChatColor.GRAY + "This player cannot be punished",
			ChatColor.GRAY + "Ce joueur ne peut pas être sanctionné",
			ChatColor.GRAY + "",
			ChatColor.GRAY + "");
	/* =============================== */
		
	public String english, french, german, spanish;
	
	private Lang(String english, String french, String german, String spanish) {
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
		return message != null ? message : this.english + ChatColor.RESET;
	}
}
