package net.farugames.api.bungee.commands.server;

import net.farugames.api.bungee.Main;
import net.farugames.api.bungee.proxiedplayer.FaruBungeePlayer;
import net.farugames.api.spigot.player.languages.Lang;
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
				.replaceAll("%players%", String.valueOf(Main.getInstance().proxyPlayers.size())));
		return;
	}
}