package net.farugames.api.proxy.commands;

import net.farugames.api.proxy.ProxyFaruGamesAPI;
import net.farugames.api.core.lang.LangOld;
import net.farugames.api.core.rank.Rank;
import net.farugames.api.proxy.ProxiedFaruPlayer;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

import java.util.Timer;
import java.util.TimerTask;

public class MaintenanceCommand extends Command {
	
	public MaintenanceCommand() {
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
		ProxiedFaruPlayer faruPlayer = ProxiedFaruPlayer.getPlayer(player.getUniqueId());
		LangOld lang = faruPlayer.getLanguage();
		
		if(faruPlayer.getPermissionLevel() < Rank.DEVELOPER.getPower()) { player.sendMessage(LangOld.ERROR.in(lang) + "\n" + LangOld.NO_PERMISSION_MESSAGE.in(lang)); return; }
		if(args.length == 0 || args.length > 2 || args[0].equalsIgnoreCase("help")) { player.sendMessage(this.HELP()); return; }
		
		if(args[0].equalsIgnoreCase("true") || args[0].equalsIgnoreCase("false")) {
			if(args[0].equalsIgnoreCase("true")) {
				if(ProxyFaruGamesAPI.isMaintenance()) { player.sendMessage(LangOld.ERROR.in(lang).replaceAll("%reason%", LangOld.MAINTENANCE_ALREADY_ENABLED.in(lang))); return; }
				player.sendMessage(LangOld.MAINTENANCE_ENABLE.in(lang));
			} else {
				if(!ProxyFaruGamesAPI.isMaintenance()) { player.sendMessage(LangOld.ERROR.in(lang).replaceAll("%reason%", LangOld.MAINTENANCE_ALREADY_ENABLED.in(lang))); return; }
				player.sendMessage(LangOld.MAINTENANCE_DISABLE.in(lang));
			}
			while(ProxyFaruGamesAPI.maintenance != Boolean.valueOf(args[0])) {
				i = 1;
				Timer timer = new Timer();
		    	timer.schedule(new TimerTask() {
		    	       @Override
		    	       public void run() {
		    	    	   if(i == 15) { 
		    	    		   ProxyFaruGamesAPI.maintenance = Boolean.valueOf(args[0]);
		    	    		   timer.cancel();
		    	    		   return; 
		    	    	   }
		    	    	   i++;
		    	       }
		    	    }, 0, 1000);
			}
		} else { player.sendMessage(this.HELP()); return; }
		
		if(args[0].equalsIgnoreCase("add")) {
			player.sendMessage(LangOld.WORKING_FEATURE.in(lang));
			return;
		}
		
		if(args[0].equalsIgnoreCase("remove")) {
			player.sendMessage(LangOld.WORKING_FEATURE.in(lang));
			return;
		}
		
		else {
			player.sendMessage(this.HELP());
			return;
		}
	}
}
