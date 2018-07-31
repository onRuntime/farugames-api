package net.farugames.api.bungee.commands.general;

import net.farugames.api.bungee.proxiedplayer.FaruBungeePlayer;
import net.farugames.api.spigot.player.languages.Lang;
import net.farugames.api.spigot.player.rank.Rank;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class RankCommand extends Command {

	public RankCommand() {
		super("rank");
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void execute(CommandSender sender, String[] args) {
		FaruBungeePlayer faruPlayer = FaruBungeePlayer.getPlayer(((ProxiedPlayer) sender).getUniqueId());
		Lang lang = faruPlayer.getLanguage();
		
		if(faruPlayer.getPermissionLevel() < Rank.ADMINISTRATOR.getPower()) { faruPlayer.getPlayer().sendMessage(Lang.ERROR.in(lang) + "\n" + Lang.NO_PERMISSION_MESSAGE.in(lang)); return; }
		if(args.length == 0 || args.length > 2 || args[0].equalsIgnoreCase("help")) { faruPlayer.getPlayer().sendMessage(this.help()); return; }
		
		if(args[0].equalsIgnoreCase("list")) {
			String ranks = "";
			for(Rank rank : Rank.values()) {
				ranks = ranks + "    §8■ §a" + rank.getOrder() + " §f❘ §b" + rank.getPower() + " §f❘ §f" + rank.getIdName() + " §f❘ " + rank.getColor() + rank.getPrefix() + " §f❘ §r" + rank.getChatColor() + "ChatColor§r" + "\n";
			}
			faruPlayer.getPlayer().sendMessage("");
			faruPlayer.getPlayer().sendMessage("  §f§l» §6§lRank §f❙ §9List");
			faruPlayer.getPlayer().sendMessage("");
			faruPlayer.getPlayer().sendMessage("    §8■ §aOrder §f❘ §bPower §f❘ §fRandId §f❘ §dRankColor & RankPrefix §f❘  §7Chatcolor");
			faruPlayer.getPlayer().sendMessage("");
			faruPlayer.getPlayer().sendMessage(ranks != "" ? ranks : "§cRank unvailable.");
		}
		
		if(args[0].equalsIgnoreCase("reset")) {
			
		}
		
		else {
			faruPlayer.getPlayer().sendMessage(this.help());
			return;
		}
	}
	
	public String help() {
		return "";
	}
}
