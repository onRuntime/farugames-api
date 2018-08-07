package net.farugames.api.proxy.commands;

import net.farugames.api.core.lang.I18n;
import net.farugames.api.core.lang.Lang;
import net.farugames.api.core.rank.Rank;
import net.farugames.api.core.server.ServerStatus;
import net.farugames.api.proxy.ProxiedFaruPlayer;
import net.farugames.api.proxy.ProxyFaruGamesAPI;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class ServerManagerCommand extends Command {

	public ServerManagerCommand() {
		super("servermanager", null, "sm");
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void execute(CommandSender sender, String[] args) {
		ProxiedPlayer player = (ProxiedPlayer) sender;
		ProxiedFaruPlayer faruPlayer = ProxiedFaruPlayer.getPlayer(player.getUniqueId());
		Lang lang = faruPlayer.getLanguage();

		if(faruPlayer.getPermissionLevel() <= Rank.DEV.getPower()) { player.sendMessage("error"); return; }
		if(args.length == 0 || args.length > 2 || args[0].equalsIgnoreCase("help")) { player.sendMessage(this.help()); return; }
		
		if(args[0].equalsIgnoreCase("request")) {
			if(args[1] == null) { player.sendMessage(this.help()); return; }
			//IServer.request(args[1]);
			
			return;
		}
		
		if(args[0].equalsIgnoreCase("delete")) {
			if(args[1] == null) { player.sendMessage(this.help()); return; }
			player.sendMessage(I18n.tl(lang, "api.proxy.servermanager.delete", args[1].toUpperCase().toString()));
			return;
		}
		
		else {
			player.sendMessage(this.help());
			return;
		}
	}

	public String help() {
		return "\n"
			+ "  §f§l» §6§lServerManager §f❙ §eHelp\n"
			+ "\n"
			+ "    §8■ §e/" + "servermanager" + " help §f» §bView help.\n"
			+ "    §8■ §e/" + "servermanager" + " request <stype> §f» §bRequest a server type.\n"
			+ "    §8■ §e/" + "servermanager" + " delete <sname> §f» §bDelete a bug server.\n";
	}
}
