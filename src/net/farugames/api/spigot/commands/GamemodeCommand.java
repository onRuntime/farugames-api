package net.farugames.api.spigot.commands;

import net.farugames.api.spigot.player.FaruPlayer;
import net.farugames.api.spigot.player.rank.Rank;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GamemodeCommand implements CommandExecutor {

	public void cmdGameModeHelp(Player p, String label) {
		p.sendMessage("");
		p.sendMessage("  §f§l» §6§lGameMode §f❙ §eHelp");
		p.sendMessage("");
		p.sendMessage("    §8■ §e/" + label + " help" + " §f» §bView help.");
		p.sendMessage("    §8■ §e/" + label + " [gamemode]" + " <player>" + " §f» §bChange mode.");
		p.sendMessage("");
	}

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if ((sender instanceof Player)) {
			Player p = (Player) sender;
			FaruPlayer faruPlayer = FaruPlayer.getPlayer(p.getUniqueId());
			Rank r = faruPlayer.getRank();
			if (r.getPower() >= Rank.MODERATOR.getPower()) {
				if (args.length == 1) {
					if (args[0].equalsIgnoreCase("help")) {
						cmdGameModeHelp(p, label);
					} else if (args[0].equalsIgnoreCase("survival") || args[0].equalsIgnoreCase("0")) {
						p.sendMessage("");
						p.sendMessage("  §f§l» §6§lGameMode §f❙ §aMode");
						p.sendMessage("");
						p.sendMessage("    §8■ §fYou are now in §6Survival§f mode.");
						p.sendMessage("");
						p.setGameMode(GameMode.SURVIVAL);

					} else if (args[0].equalsIgnoreCase("creative") || args[0].equalsIgnoreCase("1")) {
						p.sendMessage("");
						p.sendMessage("  §f§l» §6§lGameMode §f❙ §aMode");
						p.sendMessage("");
						p.sendMessage("    §8■ §fYou are now in §6Creative§f mode.");
						p.sendMessage("");
						p.setGameMode(GameMode.CREATIVE);
					} else if (args[0].equalsIgnoreCase("adventure") || args[0].equalsIgnoreCase("2")) {
						p.sendMessage("");
						p.sendMessage("  §f§l» §6§lGameMode §f❙ §aMode");
						p.sendMessage("");
						p.sendMessage("    §8■ §fYou are now in §6Adventure§f mode.");
						p.sendMessage("");
						p.setGameMode(GameMode.ADVENTURE);
					} else if (args[0].equalsIgnoreCase("spectator") || args[0].equalsIgnoreCase("3")) {
						p.sendMessage("");
						p.sendMessage("  §f§l» §6§lGameMode §f❙ §aMode");
						p.sendMessage("");
						p.sendMessage("    §8■ §fYou are now in §6Spectator§f mode.");
						p.sendMessage("");
						p.setGameMode(GameMode.SPECTATOR);
					} else {
						cmdGameModeHelp(p, label);
					}
				} else if (args.length == 2) {
					if (Bukkit.getPlayer(args[1]) != null) {
						Player targetPlayer = Bukkit.getPlayer(args[1]);
						if (args[0].equalsIgnoreCase("survival") || args[0].equalsIgnoreCase("0")) {
							p.sendMessage("");
							p.sendMessage("  §f§l» §6§lGameMode §f❙ §aMode");
							p.sendMessage("");
							p.sendMessage("    §8■ §e" + targetPlayer.getName() + "§f is now in §6Survival§f mode.");
							p.sendMessage("");
							targetPlayer.sendMessage("");
							targetPlayer.sendMessage("  §f§l» §6§lGameMode §f❙ §aMode");
							targetPlayer.sendMessage("");
							targetPlayer.sendMessage("    §8■ §fYou are now in §6Survival§f mode.");
							targetPlayer.sendMessage("");
							targetPlayer.setGameMode(GameMode.SURVIVAL);
						} else if (args[0].equalsIgnoreCase("creative") || args[0].equalsIgnoreCase("1")) {
							p.sendMessage("");
							p.sendMessage("  §f§l» §6§lGameMode §f❙ §aMode");
							p.sendMessage("");
							p.sendMessage("    §8■ §e" + targetPlayer.getName() + "§f is now in §6Creative§f mode.");
							p.sendMessage("");
							targetPlayer.sendMessage("");
							targetPlayer.sendMessage("  §f§l» §6§lGameMode §f❙ §aMode");
							targetPlayer.sendMessage("");
							targetPlayer.sendMessage("    §8■ §fYou are now in §6Creative§f mode.");
							targetPlayer.sendMessage("");
							targetPlayer.setGameMode(GameMode.CREATIVE);
						} else if (args[0].equalsIgnoreCase("adventure") || args[0].equalsIgnoreCase("2")) {
							p.sendMessage("");
							p.sendMessage("  §f§l» §6§lGameMode §f❙ §aMode");
							p.sendMessage("");
							p.sendMessage("    §8■ §e" + targetPlayer.getName() + "§f is now in §6Adventure§f mode.");
							p.sendMessage("");
							targetPlayer.sendMessage("");
							targetPlayer.sendMessage("  §f§l» §6§lGameMode §f❙ §aMode");
							targetPlayer.sendMessage("");
							targetPlayer.sendMessage("    §8■ §fYou are now in §6Adventure§f mode.");
							targetPlayer.sendMessage("");
							targetPlayer.setGameMode(GameMode.ADVENTURE);
						} else if (args[0].equalsIgnoreCase("spectator") || args[0].equalsIgnoreCase("3")) {
							p.sendMessage("");
							p.sendMessage("  §f§l» §6§lGameMode §f❙ §aMode");
							p.sendMessage("");
							p.sendMessage("    §8■ §e" + targetPlayer.getName() + "§f is now in §6Spectator§f mode.");
							p.sendMessage("");
							targetPlayer.sendMessage("");
							targetPlayer.sendMessage("  §f§l» §6§lGameMode §f❙ §aMode");
							targetPlayer.sendMessage("");
							targetPlayer.sendMessage("    §8■ §fYou are now in §6Spectator§f mode.");
							targetPlayer.sendMessage("");
						} else {
							cmdGameModeHelp(p, label);
						}
					} else {
						// errormsg
					}
				} else {
					cmdGameModeHelp(p, label);
				}
			} else {
				// errormsg
			}
		}
		return false;
	}

}
