package net.faru.api.spigot.player.languages;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

public enum Lang {
	
	/*  ========== LANGUAGES ==========  */
	FRENCH("French", "Français", "Französische", "Francès"),
	ENGLISH("English", "Anglais", "Englisch", "Inglès"),
	GERMAN("German", "Allemand", "Deutsch", "Alemán"),
	SPANISH("Spanish", "Espagnol", "Spanisch", "Español"),
	/* ================================ */
	
	
	/*  ========== TYPES ==========  */
	ERROR("§4§m+------§c§m----------§f§m--------------------§c§m----------§4§m-----+§r\n"
		+ "\n§r"
		+ "    §7§l» §c§lAn error has occurred ⚠§r\n"
		+ "\n§r"
		+ "    §8■ §eInfo: §7%info%§r\n"
		+ "    §8■ §6Reason: §c%reason%§r\n"
		+ "\n§r"
		+ "§4§m+------§c§m----------§f§m--------------------§c§m----------§4§m-----+§r",
		"§4§m+------§c§m----------§f§m--------------------§c§m----------§4§m-----+§r\n"
		+ "\n§r"
		+ "    §7§l» §c§lUne erreur est survenue ⚠§r\n"
		+ "\n§r"
		+ "    §8■ §eInfo: §7%info%§r\n"
		+ "    §8■ §Raison: §c%reason%§r\n"
		+ "\n§r"
		+ "§4§m+------§c§m----------§f§m--------------------§c§m----------§4§m-----+§r",	
		"§4§m+------§c§m----------§f§m--------------------§c§m----------§4§m-----+§r\n"
		+ "\n§r"
		+ "    §7§l» §c§lEin fehler ist aufgetreten ⚠§r\n"
		+ "\n§r"
		+ "    §8■ §eInfo: §7%info%§r\n"
		+ "    §8■ §6Grund: §c%reason%§r\n"
		+ "\n§r"
		+ "§4§m+------§c§m----------§f§m--------------------§c§m----------§4§m-----+§r",
		""),
	/* ============================ */
	
	
	/*  ========== PLAYER ==========  */
	BAD_ACCOUNT(ChatColor.RED + "Our systems are having some problems with this player account. Contact an administrator for more informations.",
			ChatColor.RED + "Nos systèmes rencontrent des problèmes avec ce compte joueur. Contactez un administrateur pour plus d'informations.",
			"",
			ChatColor.RED + "Nuestros sistemas encuentran problemas con la cuenta del jugador. Contacte al administrador para obtener más informaciones."),
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
	
	ONLINE_PLAYERS("§c§lF§e§la§a§lr§b§lu§d§lGames §f§l» §eThere are currently §b§l%players% §eplayer(s) online.",
			"§c§lF§e§la§a§lr§b§lu§d§lGames §f§l» §eIl y actuellement §b§l%players% §ejoueur(s) en ligne.",
			"§c§lF§e§la§a§lr§b§lu§d§lGames §f§l» §eDerzeit sind §b§l%players% §espieler online.",
			""),
	/* ============================== */
	
	
	/*  ========== FEATURES ==========  */
	WORKING_FEATURE(ChatColor.RED + "This feature is under development.",
			ChatColor.RED + "Cette fonctionnalité est en développement.",
			ChatColor.RED + "Dieses feature ist in der Entwicklung.",
			ChatColor.RED + "Esta funcionalidad es en desarrollo."),
	/* ============================== */
	
	
	/*  ========== MAINTENANCE ==========  */
	MAINTENANCE_ENABLE("\n"
			+ "  §f§l» §6§lMaintenance §f❙ §bMode\n"
			+ "\n "
			+ "    §8■ §eInfo: §cMaintenance will be turned §aon§c in §e15 seconds§c.\n"
			+ "    " + inJSON("§8[§c➟ §c§lCancel§8]", new ClickEvent(ClickEvent.Action.RUN_COMMAND, "maintenance false"), new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§cClick here to cancel.").create())) + "\n",
			"\n"
			+ "  §f§l» §6§lMaintenance §f❙ §bMode\n"
			+ "\n "
			+ "    §8■ §eInfo: §cLa maintenance va être §aactivée§c dans §e15 secondes§c.\n"
			+ "    " + inJSON("§8[§c➟ §c§lAnnuler§8]", new ClickEvent(ClickEvent.Action.RUN_COMMAND, "maintenance false"), new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§cCliquez ici pour annuler.").create())) + "\n",
			"\n"
			+ "  §f§l» §6§lInstandhaltung §f❙ §bMode\n"
			+ "\n "
			+ "    §8■ §eInfo: §cDie wartung wird in §e15 sekunden§a eingeschaltet§c.\n"
			+ "    " + inJSON("§8[§c➟ §c§lCancel§8]", new ClickEvent(ClickEvent.Action.RUN_COMMAND, "maintenance false"), new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§cKlicken sie hier, um abzubrechen.").create())) + "\n",
			""),
	
	MAINTENANCE_DISABLE("\n"
			+ "  §f§l» §6§lMaintenance §f❙ §bMode"
			+ "\n"
			+ "    §8■ §eInfo: §cMaintenance will be turned §coff§c in §e15 seconds§c.\n"
			+ "    " + inJSON("§8[§c➟ §c§lCancel§8]", new ClickEvent(ClickEvent.Action.RUN_COMMAND, "maintenance true"), new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§cClick here to cancel.").create())) + "\n",
			"\n"
			+ "  §f§l» §6§lMaintenance §f❙ §bMode"
			+ "\n"
			+ "    §8■ §eInfo: §cLa maintenance va être §cdésactivée§c dans §e15 secondes§c.\n"
			+ "    " + inJSON("§8[§c➟ §c§lCancel§8]", new ClickEvent(ClickEvent.Action.RUN_COMMAND, "maintenance true"), new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§cCliquez ici pour annuler.").create())) + "\n",
			"\n"
			+ "  §f§l» §6§lMaintenance §f❙ §bMode"
			+ "\n"
			+ "    §8■ §eInfo: §cDie wartung wird in §e15 sekunden§c ausgeschaltet§c.\n"
			+ "    " + inJSON("§8[§c➟ §c§lCancel§8]", new ClickEvent(ClickEvent.Action.RUN_COMMAND, "maintenance true"), new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§cKlicken sie hier, um abzubrechen.").create())) + "\n",
			""),
	
	MAINTENANCE_ALREADY_ENABLED(ChatColor.RED + "The maintenance is already enabled.",
			ChatColor.RED + "La maintenance est déjà activée.",
			ChatColor.RED + "",
			ChatColor.RED + "El mantenimiento es ya activado."),
	
	MAINTENANCE_ALREADY_DISABLED(ChatColor.RED + "The maintenance is already disabled.",
			ChatColor.RED + "La maintenance est déjà désactivée.",
			ChatColor.RED + "",
			ChatColor.RED + "El mantenimiento es ya desactivado."),
	
	MAINTENANCE_MOTD("§c§lF§e§la§a§lr§b§lu§d§lGames§8.§dnet §8§l«» §bInnovative Server §8§l«» §f[§a1.9§f-§a1.12§f]\n"
					+ "         §4§l⚠ §cCurrently under development... §4§l⚠",
					"§c§lF§e§la§a§lr§b§lu§d§lGames§8.§dnet §8§l«» §bInnovative Server §8§l«» §f[§a1.9§f-§a1.12§f]\n"
					+ "         §4§l⚠ §cActuellement en développement... §4§l⚠",
					"§c§lF§e§la§a§lr§b§lu§d§lGames§8.§dnet §8§l«» §bInnovative Server §8§l«» §f[§a1.9§f-§a1.12§f]\n"
					+ "         §4§l⚠ §cMomentan in entwicklung... §4§l⚠",
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
	
	public static TextComponent inJSON(String message, ClickEvent click, HoverEvent hover) {
		TextComponent textComponent = new TextComponent(message);
		textComponent.setClickEvent(click);
		textComponent.setHoverEvent(hover);
		return textComponent;
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
