package net.farugames.api.tools.messages;

import net.md_5.bungee.api.ChatColor;

public enum Prefix {

	MAINTENANCE("Maintenance");
	
	private String prefix;
	
	Prefix(String prefix) {
		this.prefix = prefix;
	}
	
	public String getPrefix() {
		return ChatColor.AQUA + "" + ChatColor.BOLD + this.prefix + ChatColor.DARK_GRAY + " » ";
	}
}
