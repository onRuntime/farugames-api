package net.faru.api.bungee.commands.server;

import net.faru.api.bungee.BungeeFaruAPI;
import net.faru.api.player.FaruPlayer;
import net.faru.api.player.languages.Lang;
import net.faru.api.player.rank.Rank;
import net.faru.data.database.servers.IMaintenance;
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
		Lang lang = faruPlayer.getLanguage();
		
		if(faruPlayer.getRank().getPower() < Rank.DEVELOPER.getPower()) { player.sendMessage(Lang.ERROR.in(lang) + "\n" + Lang.NO_PERMISSION_MESSAGE.in(lang)); return; }
		if(args == null || args.length > 2 || args[0].equalsIgnoreCase("help")) { player.sendMessage(this.HELP()); return; }
		
		if(args[0].equalsIgnoreCase("set")) {
			if(!args[1].equalsIgnoreCase("false") || !args[1].equalsIgnoreCase("true")) { player.sendMessage(this.HELP()); return; }
			IMaintenance.setState(Boolean.valueOf(args[1]));
			if(args[1].equalsIgnoreCase("true")) {
				if(BungeeFaruAPI.isMaintenance()) { player.sendMessage(Lang.ERROR.in(lang) + "\n" + Lang.MAINTENANCE_ALREADY_ENABLED.in(lang)); return; }
				player.sendMessage(Lang.MAINTENANCE_ENABLE.in(lang));
				return;
			}
			if(!BungeeFaruAPI.isMaintenance()) { player.sendMessage(Lang.ERROR.in(lang) + "\n" + Lang.MAINTENANCE_ALREADY_DISABLED.in(lang)); return; }
			player.sendMessage(Lang.MAINTENANCE_DISABLE.in(lang));
			return;
		}
		
		if(args[0].equalsIgnoreCase("add")) {
			player.sendMessage(Lang.WORKING_FEATURE.in(lang));
			return;
		}
		
		if(args[0].equalsIgnoreCase("remove")) {
			player.sendMessage(Lang.WORKING_FEATURE.in(lang));
			return;
		}
		
		else {
			player.sendMessage(this.HELP());
			return;
		}
	}
}
