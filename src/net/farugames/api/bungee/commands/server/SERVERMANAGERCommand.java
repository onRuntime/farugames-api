package net.farugames.api.bungee.commands.server;

import net.farugames.api.bungee.Main;
import net.farugames.api.bungee.proxiedplayer.FaruBungeePlayer;
import net.farugames.api.bungee.servers.ServerStatut;
import net.farugames.api.spigot.player.languages.Lang;
import net.farugames.api.spigot.player.rank.Rank;
import net.farugames.data.database.servers.IServer;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class SERVERMANAGERCommand extends Command {

	public SERVERMANAGERCommand() {
		super("servermanager", null, "sm");
	}

	@SuppressWarnings("deprecation")
	@Override
	public void execute(CommandSender sender, String[] args) {
		ProxiedPlayer player = (ProxiedPlayer) sender;
		FaruBungeePlayer faruPlayer = FaruBungeePlayer.getPlayer(player.getUniqueId());
		Lang lang = faruPlayer.getLanguage();
		
		if(faruPlayer.getRank().getPower() < Rank.DEVELOPER.getPower()) { player.sendMessage(Lang.ERROR.in(lang) + "\n" + Lang.NO_PERMISSION_MESSAGE.in(lang)); return; }
		if(args.length == 0 || args.length > 2 || args[0].equalsIgnoreCase("help")) { player.sendMessage(this.HELP()); return; }
		
		if(args[0].equalsIgnoreCase("request")) {
			if(args[1] == null) { player.sendMessage(this.HELP()); return; }
			IServer.request(args[1]);
			player.sendMessage(Lang.SERVER_REQUESTED.in(lang).replaceAll("%server%", args[1].toUpperCase().toString()));
			return;
		}
		
		if(args[0].equalsIgnoreCase("delete")) {
			if(args[1] == null) { player.sendMessage(this.HELP()); return; }
			Main.iFaruServers.get(args[1]).setStatut(ServerStatut.DELETE);
			player.sendMessage(Lang.SERVER_REMOVE.in(lang).replaceAll("%server%", args[1].toUpperCase().toString()));
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
			+ "  §f§l» §6§lServerManager §f❙ §eHelp";
	}
}
