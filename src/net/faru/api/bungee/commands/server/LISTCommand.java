package net.faru.api.bungee.commands.server;

import net.faru.api.bungee.BungeeFaruAPI;
import net.faru.api.bungee.player.FaruBungeePlayer;
import net.faru.api.player.languages.Lang;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.command.ConsoleCommandSender;

public class LISTCommand extends Command {

	public LISTCommand() {
		super("list");
	}

	@SuppressWarnings("deprecation")
	@Override
	public void execute(CommandSender sender, String[] args) {
		if(sender instanceof ConsoleCommandSender) { return; }
		
		ProxiedPlayer player = (ProxiedPlayer) sender;
		player.sendMessage(Lang.ONLINE_PLAYERS.in(FaruBungeePlayer.getPlayer(player.getUniqueId()).getLanguage())
				.replaceAll("%players%", String.valueOf(BungeeFaruAPI.getInstance().proxyPlayers.size())));
		return;
	}
}
