package net.faru.api.bungee.commands.general;

import net.faru.api.bungee.proxiedplayer.FaruBungeePlayer;
import net.faru.api.spigot.player.languages.Lang;
import net.faru.api.spigot.player.rank.Rank;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class RANKCommand extends Command {

	public RANKCommand() {
		super("rank");
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void execute(CommandSender sender, String[] args) {
		FaruBungeePlayer faruPlayer = FaruBungeePlayer.getPlayer(((ProxiedPlayer) sender).getUniqueId());
		Lang lang = faruPlayer.getLanguage();
		
		if(faruPlayer.getRank().getPower() < Rank.ADMINISTRATOR.getPower()) { faruPlayer.getPlayer().sendMessage(Lang.ERROR.in(lang) + "\n" + Lang.NO_PERMISSION_MESSAGE.in(lang)); return; }
		if(args.length == 0 || args.length > 2 || args[0].equalsIgnoreCase("help")) { faruPlayer.getPlayer().sendMessage(this.help()); return; }
		
		if(args[0].equalsIgnoreCase("list")) {
			String ranks = "";
			for(Rank rank : Rank.values()) {
				ranks = ranks + " §7» §f" + rank.getOrder() + " §8/ §3" + rank.getPower() + " §8/ " + rank.getChatColor() + rank.getIdName() + " §8/ " + rank.getColor() + rank.getPrefix() + "\n";
			}
			faruPlayer.getPlayer().sendMessage("");
			faruPlayer.getPlayer().sendMessage("§7Liste des grades ");
			faruPlayer.getPlayer().sendMessage("");
			faruPlayer.getPlayer().sendMessage(ranks != "" ? ranks : "§cAucun grade disponible.");
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
