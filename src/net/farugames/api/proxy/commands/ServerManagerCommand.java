package net.farugames.api.proxy.commands;

import net.farugames.api.proxy.ProxyFaruGamesAPI;
import net.farugames.api.core.lang.LangOld;
import net.farugames.api.core.rank.Rank;
import net.farugames.api.core.server.ServerStatut;
import net.farugames.api.database.sql.accounts.IServer;
import net.farugames.api.proxy.ProxiedFaruPlayer;
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
		LangOld lang = faruPlayer.getLanguage();

		if(faruPlayer.getPermissionLevel() < Rank.DEVELOPER.getPower()) { player.sendMessage(LangOld.ERROR.in(lang) + "\n" + LangOld.NO_PERMISSION_MESSAGE.in(lang)); return; }
		if(args.length == 0 || args.length > 2 || args[0].equalsIgnoreCase("help")) { player.sendMessage(this.HELP()); return; }
		
		if(args[0].equalsIgnoreCase("request")) {
			if(args[1] == null) { player.sendMessage(this.HELP()); return; }
			IServer.request(args[1]);
			player.sendMessage(LangOld.SERVER_REQUESTED.in(lang).replaceAll("%server%", args[1].toUpperCase().toString()));
			return;
		}
		
		if(args[0].equalsIgnoreCase("delete")) {
			if(args[1] == null) { player.sendMessage(this.HELP()); return; }
			ProxyFaruGamesAPI.iFaruServers.get(args[1]).setStatut(ServerStatut.DELETE);
			player.sendMessage(LangOld.SERVER_REMOVE.in(lang).replaceAll("%server%", args[1].toUpperCase().toString()));
			return;
		}
		
		if(args[0].equalsIgnoreCase("info")) {
			if(args[1] == null) { player.sendMessage(this.HELP()); return; }
			player.sendMessage("\n"
								+ "");
		}
		
		else {
			player.sendMessage(this.HELP());
			return;
		}
	}

	public String HELP() {
		return "\n"
			+ "  §f§l» §6§lServerManager §f❙ §eHelp\n"
			+ "\n"
			+ "    §8■ §e/" + "servermanager" + " help §f» §bView help.\n"
			+ "    §8■ §e/" + "servermanager" + " request <stype> §f» §bRequest a server type.\n"
			+ "    §8■ §e/" + "servermanager" + " delete <sname> §f» §bDelete a bug server.\n";
	}
}
