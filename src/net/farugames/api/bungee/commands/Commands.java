package net.farugames.api.bungee.commands;

import net.farugames.api.bungee.commands.general.MESSAGECommand;
import net.farugames.api.bungee.commands.general.RANKCommand;
import net.farugames.api.bungee.commands.general.REPLYCommand;
import net.farugames.api.bungee.commands.moderation.SANCTIONCommand;
import net.farugames.api.bungee.commands.server.LISTCommand;
import net.farugames.api.bungee.commands.server.MAINTENANCECommand;
import net.farugames.api.bungee.commands.server.SERVERMANAGERCommand;
import net.md_5.bungee.api.plugin.Command;

public enum Commands {
	
	MAINTENANCE(new MAINTENANCECommand()),
	SANCTION(new SANCTIONCommand()),
	SERVERMANAGER(new SERVERMANAGERCommand()),
	MESSAGE(new MESSAGECommand()),
	REPLY(new REPLYCommand()),
	RANK(new RANKCommand()),
	LIST(new LISTCommand());
	
	private Command command;
	
	private Commands(Command command) {
		this.command = command;
	}
	
	public Command getCommand() {
		return this.command;
	}
}