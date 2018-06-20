package net.faru.api.tools.messages;

import org.bukkit.ChatColor;

public enum PluginMessages {
	
	NO_PERMISSION(MessageType.ERROR.getMessage() + "\n" + ChatColor.RED + "You don't have the permission for that."),
	FONCTION_IN_WORK(MessageType.ERROR.getMessage() + "\n" + ChatColor.RED + "This fonctionnality is in work."),
	MAINTENANCE_ALREADY_ENABLED(MessageType.ERROR.getMessage() + "\n" + ChatColor.RED + "The maintenance is already enabled."),
	MAINTENANCE_ALREADY_DISABLED(MessageType.ERROR.getMessage() + "\n" + ChatColor.RED + "The maintenance is already disabled.");
	
	private String message;
	
	PluginMessages(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return this.message;
	}
}
