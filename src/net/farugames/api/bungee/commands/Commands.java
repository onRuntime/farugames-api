package net.farugames.api.bungee.commands;

import net.farugames.api.bungee.commands.general.HubCommand;
import net.farugames.api.bungee.commands.general.MessageCommand;
import net.farugames.api.bungee.commands.general.RankCommand;
import net.farugames.api.bungee.commands.general.ReplyCommand;
import net.farugames.api.bungee.commands.moderation.PunishCommand;
import net.farugames.api.bungee.commands.server.ListCommand;
import net.farugames.api.bungee.commands.server.MaintenanceCommand;
import net.farugames.api.bungee.commands.server.ServerManagerCommand;
import net.md_5.bungee.api.plugin.Command;

public enum Commands {
	
	MAINTENANCE(new MaintenanceCommand()),
	SANCTION(new PunishCommand()),
	SERVERMANAGER(new ServerManagerCommand()),
	MESSAGE(new MessageCommand()),
	REPLY(new ReplyCommand()),
	RANK(new RankCommand()),
	LIST(new ListCommand()),
	Hub(new HubCommand());
	
	private Command command;
	
	private Commands(Command command) {
		this.command = command;
	}
	
	public Command getCommand() {
		return this.command;
	}
}
