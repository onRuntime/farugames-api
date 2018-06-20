package net.faru.api.bungee.commands;

import net.faru.api.bungee.BungeeFaruAPI;
import net.faru.api.player.FaruPlayer;
import net.faru.api.player.languages.Lang;
import net.faru.api.player.rank.Rank;
import net.faru.api.tools.messages.PluginMessages;
import net.faru.api.tools.messages.Prefix;
import net.faru.data.database.servers.IMaintenance;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class MAINTENANCECommand extends Command {
	
	public MAINTENANCECommand() {
		super("maintenance");
	}
	
	private String HELP() {
		return "";
	}

	@SuppressWarnings("deprecation")
	@Override
	public void execute(CommandSender sender, String[] args) {
		ProxiedPlayer player = (ProxiedPlayer) sender;
		FaruPlayer faruPlayer = FaruPlayer.getPlayer(player.getUniqueId());
		
		if(faruPlayer.getRank().getPower() < Rank.DEVELOPER.getPower()) { player.sendMessage(PluginMessages.NO_PERMISSION.getMessage()); return; }
		if(args == null || args.length > 2 || args[0].equalsIgnoreCase("help")) { player.sendMessage(this.HELP()); return; }
		
		if(args[0].equalsIgnoreCase("set")) {
			if(args[1].equalsIgnoreCase("true")) {
				if(BungeeFaruAPI.isMaintenance()) { player.sendMessage(PluginMessages.MAINTENANCE_ALREADY_ENABLED.getMessage()); return; }
				IMaintenance.setState(true);
				player.sendMessage(Prefix.MAINTENANCE.getPrefix() + ChatColor.GRAY + "Maintenance is now " + ChatColor.GREEN + "enabled" + ChatColor.GRAY + ".");
				return;
			}
			if(!BungeeFaruAPI.isMaintenance()) { player.sendMessage(PluginMessages.MAINTENANCE_ALREADY_DISABLED.getMessage()); return; }
			IMaintenance.setState(false);
			player.sendMessage(Prefix.MAINTENANCE.getPrefix() + ChatColor.GRAY + "Maintenance is now " + ChatColor.RED + "disabled" + ChatColor.GRAY + ".");
			return;
		}
		
		if(args[0].equalsIgnoreCase("add")) {
			player.sendMessage(PluginMessages.FONCTION_IN_WORK.getMessage());
			return;
		}
		
		if(args[0].equalsIgnoreCase("remove")) {
			player.sendMessage(PluginMessages.FONCTION_IN_WORK.getMessage());
			return;
		}
		
		else {
			player.sendMessage(this.HELP());
			return;
		}
	}
}
