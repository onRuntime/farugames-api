package net.farugames.api.bungee.commands;

import net.farugames.api.bungee.commands.general.HubCommand;
import net.farugames.api.bungee.commands.general.MessageCommand;
import net.farugames.api.bungee.commands.general.RankCommand;
import net.farugames.api.bungee.commands.general.ReplyCommand;
import net.farugames.api.bungee.commands.general.ServerTeleportCommand;
import net.farugames.api.bungee.commands.moderation.PunishCommand;
import net.farugames.api.bungee.commands.server.ListCommand;
import net.farugames.api.bungee.commands.server.MaintenanceCommand;
import net.farugames.api.bungee.commands.server.ServerManagerCommand;
import net.md_5.bungee.api.plugin.Command;

public enum Commands {
	
	Maintenance(new MaintenanceCommand()),
	Sanction(new PunishCommand()),
	ServerManager(new ServerManagerCommand()),
	Message(new MessageCommand()),
	Reply(new ReplyCommand()),
	Rank(new RankCommand()),
	List(new ListCommand()),
	ServerTeleport(new ServerTeleportCommand()),
	Hub(new HubCommand());
	
	private Command command;
	
	private Commands(Command command) {
		this.command = command;
	}
	
	public Command getCommand() {
		return this.command;
	}
}
