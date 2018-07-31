package net.farugames.api.bungee.commands.general;

import net.farugames.api.bungee.proxiedplayer.FaruBungeePlayer;
import net.farugames.api.spigot.player.rank.Rank;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class ServerTeleportCommand extends Command {

	public ServerTeleportCommand() {
		super("serverteleport", "", new String[] { "servertp", "stp", "steleport" });
	}

	@SuppressWarnings("deprecation")
	public void cmdHelp(CommandSender sender) {
		sender.sendMessage("");
		sender.sendMessage("  §f§l» §6§lServerTeleport §f❙ §eHelp\n");
		sender.sendMessage("");
		sender.sendMessage("    §8■ §e/" + "serverteleport" + " help" + " §f» §bView help.");
		sender.sendMessage("    §8■ §e/" + "serverteleport" + " <server>" + " §f» §bTeleport to a server.");
		sender.sendMessage(
				"    §8■ §e/" + "serverteleport" + " <server>" + " <player>" + " §f» §bTeleport a player to a server.");
		sender.sendMessage("");
	}

	@SuppressWarnings("deprecation")
	@Override
	public void execute(CommandSender sender, String[] args) {
		if (sender instanceof ProxiedPlayer) {
			ProxiedPlayer p = (ProxiedPlayer) sender;
			FaruBungeePlayer faruBungeePlayer = FaruBungeePlayer.getPlayer(p.getUniqueId());
			if (faruBungeePlayer.getPermissionLevel() >= Rank.YOUTUBER.getPower()) {
				if (args.length >= 1) {
					if (args[0].equalsIgnoreCase("help")) {
						cmdHelp(sender);
					} else if (ProxyServer.getInstance().getServerInfo(args[0]) != null) {
						ServerInfo targetedServer = ProxyServer.getInstance().getServerInfo(args[0]);
						if (args.length == 1) {
							if (ProxyServer.getInstance().getServerInfo(args[0]) != p.getServer().getInfo()) {
								p.connect(targetedServer);
								p.sendMessage("");
								p.sendMessage("  §f§l» §6§lServerTeleport §f❙ §aTeleport");
								p.sendMessage("");
								p.sendMessage("    §8■ §fYou were teleported to §e"
										+ targetedServer.getName() + "§f.");
								p.sendMessage("");
							}
						} else if (args.length == 2) {
							if(ProxyServer.getInstance().getPlayer(args[1]) != null) {
								ProxiedPlayer targetedPlayer = ProxyServer.getInstance().getPlayer(args[1]);
								p.sendMessage("");
								p.sendMessage("  §f§l» §6§lServerTeleport §f❙ §aTeleport");
								p.sendMessage("");
								p.sendMessage("    §8■ §fYou teleport §e" + targetedPlayer.getName() + " §fto §e"
										+ targetedServer.getName() + "§f.");
								p.sendMessage("");
								targetedPlayer.connect(targetedServer);
								targetedPlayer.sendMessage("");
								targetedPlayer.sendMessage("  §f§l» §6§lServerTeleport §f❙ §aTeleport");
								targetedPlayer.sendMessage("");
								targetedPlayer.sendMessage("    §8■ §fYou were teleported to §e"
										+ targetedServer.getName() + "§f.");
								targetedPlayer.sendMessage("");
							}
						}
					}
				} else {
					cmdHelp(sender);
				}
			}
		}
	}

}
