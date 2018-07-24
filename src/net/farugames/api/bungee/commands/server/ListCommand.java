package net.farugames.api.bungee.commands.server;

import net.farugames.api.bungee.BungeeFaruAPI;
import net.farugames.api.bungee.proxiedplayer.FaruBungeePlayer;
import net.farugames.api.spigot.player.languages.Lang;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.command.ConsoleCommandSender;

public class ListCommand extends Command {

	public ListCommand() {
		super("list", "", "glist");
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
