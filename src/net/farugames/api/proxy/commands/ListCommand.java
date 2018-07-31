package net.farugames.api.proxy.commands;

import net.farugames.api.proxy.ProxyFaruGamesAPI;
import net.farugames.api.core.lang.LangOld;
import net.farugames.api.proxy.ProxiedFaruPlayer;
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
		player.sendMessage(LangOld.ONLINE_PLAYERS.in(ProxiedFaruPlayer.getPlayer(player.getUniqueId()).getLanguage())
				.replaceAll("%players%", String.valueOf(ProxyFaruGamesAPI.getInstance().proxyPlayers.size())));
		return;
	}
}
