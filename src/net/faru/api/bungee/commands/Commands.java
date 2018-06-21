package net.faru.api.bungee.commands;

import net.faru.api.bungee.commands.moderation.SANCTIONCommand;
import net.faru.api.bungee.commands.server.MAINTENANCECommand;
import net.md_5.bungee.api.plugin.Command;

public enum Commands {
	
	MAINTENANCE(new MAINTENANCECommand()),
	SANCTION(new SANCTIONCommand());
	
	private Command command;
	
	Commands(Command command) {
		this.command = command;
	}
	
	public Command getCommand() {
		return this.command;
	}
}
