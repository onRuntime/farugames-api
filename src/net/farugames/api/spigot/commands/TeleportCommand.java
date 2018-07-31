package net.farugames.api.spigot.commands;

import net.farugames.api.core.rank.Rank;
import net.farugames.api.spigot.FaruPlayer;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TeleportCommand implements CommandExecutor {

	public void cmdTeleportHelp(Player p, String label) {
		p.sendMessage("");
		p.sendMessage("  §f§l» §6§lTeleportation §f❙ §eHelp");
		p.sendMessage("");
		p.sendMessage("    §8■ §e/" + label + " help" + " §f» §bView help.");
		p.sendMessage("    §8■ §e/" + label + " all" + " §f» §bTeleport all players to yourself.");
		p.sendMessage("    §8■ §e/" + label + " <player>" + " §f» §bTeleport to a player.");
		p.sendMessage("    §8■ §e/" + label + " <player>" + " <player>" + " §f» §bTeleport a player to a player.");
		p.sendMessage("");
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if ((sender instanceof Player)) {
			Player p = (Player) sender;
			FaruPlayer faruPlayer = FaruPlayer.getPlayer(p.getUniqueId());
			Rank r = faruPlayer.getRank();
			if (faruPlayer.getPermissionLevel() >= Rank.MOD.getPower()) {
				if (args.length == 1) {
					if (args[0].equalsIgnoreCase("help")) {
						cmdTeleportHelp(p, label);
					} else if (args[0].equalsIgnoreCase("all")) {
						for (Player op : Bukkit.getOnlinePlayers()) {
							op.sendMessage("");
							op.sendMessage("  §f§l» §6§lTeleportation §f❙ §aTeleport");
							op.sendMessage("");
							op.sendMessage("    §8■ §fAll players were teleported to §e" + p.getName() + "§f.");
							op.sendMessage("");
							op.teleport(p.getLocation());
						}
					} else if (Bukkit.getPlayer(args[0]) != null) {
						Player targetPlayer = Bukkit.getPlayer(args[0]);
						p.sendMessage("");
						p.sendMessage("  §f§l» §6§lTeleportation §f❙ §aTeleport");
						p.sendMessage("");
						p.sendMessage("    §8■ §fYou were teleported to §e" + targetPlayer.getName() + "§f.");
						p.sendMessage("");
						p.teleport(targetPlayer.getLocation());

					} else {
						//errormsg
					}
				} else if (args.length == 2) {
					if (Bukkit.getPlayer(args[0]) != null || Bukkit.getPlayer(args[1]) != null) {
						Player targetPlayer = Bukkit.getPlayer(args[0]);
						Player finalPlayer = Bukkit.getPlayer(args[1]);
						p.sendMessage("");
						p.sendMessage("  §f§l» §6§lTeleportation §f❙ §aTeleport");
						p.sendMessage("");
						p.sendMessage("    §8■ §fYou teleport §e" + targetPlayer.getName() + " §fto §e"
								+ finalPlayer.getName() + "§f.");
						p.sendMessage("");
						targetPlayer.sendMessage("");
						targetPlayer.sendMessage("  §f§l» §6§lTeleportation §f❙ §aTeleport");
						targetPlayer.sendMessage("");
						targetPlayer
								.sendMessage("    §8■ §fYou were teleported to §e" + targetPlayer.getName() + "§f.");
						targetPlayer.sendMessage("");
						targetPlayer.teleport(finalPlayer.getLocation());
					} else {
						//errormsg
					}
				} else {
					cmdTeleportHelp(p, label);
				}
			} else {
				//errormsg
			}
		}
		return false;
	}

}
