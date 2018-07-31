package net.farugames.api.proxy.commands;

import net.farugames.api.core.lang.I18n;
import net.farugames.api.proxy.ProxiedFaruPlayer;
import net.farugames.api.proxy.ProxyFaruGamesAPI;
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
		player.sendMessage(I18n.tl(ProxiedFaruPlayer.getPlayer(player.getUniqueId()).getLanguage(), "api_proxy_onlineplayers", String.valueOf(ProxyFaruGamesAPI.getInstance().proxyPlayers.size())));
		return;
	}
}
