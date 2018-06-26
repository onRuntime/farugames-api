package net.faru.api.bungee.commands.server;

import java.util.Timer;
import java.util.TimerTask;

import net.faru.api.bungee.BungeeFaruAPI;
import net.faru.api.bungee.proxiedplayer.FaruBungeePlayer;
import net.faru.api.spigot.player.languages.Lang;
import net.faru.api.spigot.player.rank.Rank;
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
	
	private Integer i;

	@SuppressWarnings("deprecation")
	@Override
	public void execute(CommandSender sender, String[] args) {
		ProxiedPlayer player = (ProxiedPlayer) sender;
		FaruBungeePlayer faruPlayer = FaruBungeePlayer.getPlayer(player.getUniqueId());
		Lang lang = faruPlayer.getLanguage();
		
		if(faruPlayer.getRank().getPower() < Rank.DEVELOPER.getPower()) { player.sendMessage(Lang.ERROR.in(lang) + "\n" + Lang.NO_PERMISSION_MESSAGE.in(lang)); return; }
		if(args.length == 0 || args.length > 2 || args[0].equalsIgnoreCase("help")) { player.sendMessage(this.HELP()); return; }
		
		if(args[0].equalsIgnoreCase("true") || args[0].equalsIgnoreCase("false")) {
			if(args[0].equalsIgnoreCase("true")) {
				if(BungeeFaruAPI.isMaintenance()) { player.sendMessage(Lang.ERROR.in(lang).replaceAll("%reason%", Lang.MAINTENANCE_ALREADY_ENABLED.in(lang))); return; }
				player.sendMessage(Lang.MAINTENANCE_ENABLE.in(lang));
			} else {
				if(!BungeeFaruAPI.isMaintenance()) { player.sendMessage(Lang.ERROR.in(lang).replaceAll("%reason%", Lang.MAINTENANCE_ALREADY_ENABLED.in(lang))); return; }
				player.sendMessage(Lang.MAINTENANCE_DISABLE.in(lang));
			}
			while(BungeeFaruAPI.maintenance != Boolean.valueOf(args[0])) {
				i = 1;
				Timer timer = new Timer();
		    	timer.schedule(new TimerTask() {
		    	       @Override
		    	       public void run() {
		    	    	   if(i == 15) { 
		    	    		   BungeeFaruAPI.maintenance = Boolean.valueOf(args[0]);
		    	    		   timer.cancel();
		    	    		   return; 
		    	    	   }
		    	    	   i++;
		    	       }
		    	    }, 0, 1000);
			}
		} else { player.sendMessage(this.HELP()); return; }
		
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
