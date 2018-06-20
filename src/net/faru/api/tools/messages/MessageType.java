package net.faru.api.tools.messages;

import net.md_5.bungee.api.ChatColor;

public enum MessageType {

	ERROR(ChatColor.RED + "" + ChatColor.BOLD + "Error : ");
	
	private String message;
	
	MessageType(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return this.message;
	}
}
