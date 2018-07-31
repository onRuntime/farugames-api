package net.farugames.api.proxy.commands;

import java.util.Timer;
import java.util.TimerTask;

import net.farugames.api.core.lang.I18n;
import net.farugames.api.core.lang.Lang;
import net.farugames.api.core.rank.Rank;
import net.farugames.api.proxy.ProxiedFaruPlayer;
import net.farugames.api.proxy.ProxyFaruGamesAPI;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

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
		Lang lang = faruPlayer.getLanguage();
		
		if(faruPlayer.getPermissionLevel() < Rank.DEV.getPower()) { player.sendMessage(I18n.tl(lang, "api.methods.error")); return; }
		if(args.length == 0 || args.length > 2 || args[0].equalsIgnoreCase("help")) { player.sendMessage(this.HELP()); return; }
		
		if(args[0].equalsIgnoreCase("true") || args[0].equalsIgnoreCase("false")) {
			if(args[0].equalsIgnoreCase("true")) {
				if(ProxyFaruGamesAPI.isMaintenance()) { player.sendMessage(I18n.tl(lang, "api.proxy.error", I18n.tl(lang, "api.proxy.maintnenance.already.enabled"))); return; }
				player.sendMessage(I18n.tl(lang, "api.proxy.maintenance.enable"));
			} else {
				if(!ProxyFaruGamesAPI.isMaintenance()) { player.sendMessage(I18n.tl(lang, "api.proxy.error", I18n.tl(lang, "api.proxy.maintnenance.already.enabled"))); return; }
				player.sendMessage(I18n.tl(lang, "api.proxy.maintenance.disable"));
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
			player.sendMessage(I18n.tl(lang, "api.methods.under_developement"));
			return;
		}
		
		if(args[0].equalsIgnoreCase("remove")) {
			player.sendMessage(I18n.tl(lang, "api.methods.under_developement"));
			return;
		}
		
		else {
			player.sendMessage(this.HELP());
			return;
		}
	}
}
