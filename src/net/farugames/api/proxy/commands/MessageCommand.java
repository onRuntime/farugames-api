package net.farugames.api.proxy.commands;

import net.farugames.api.core.data.DataType;
import net.farugames.api.core.lang.I18n;
import net.farugames.api.proxy.ProxiedFaruPlayer;
import net.farugames.api.proxy.ProxyFaruGamesAPI;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.command.ConsoleCommandSender;

public class MessageCommand extends Command {

	public MessageCommand() {
		super("message", null, new String[] {"msg", "m", "whisper", "w", "tell", "t", "mp"});
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void execute(CommandSender sender, String[] args) {
		if(sender instanceof ConsoleCommandSender) return;
		if(args.length == 0 || args.length < 2) { sender.sendMessage(this.help()); return; };
		
		ProxiedFaruPlayer player = ProxiedFaruPlayer.getPlayer(((ProxiedPlayer) sender).getUniqueId());
		if(args[0].equalsIgnoreCase("on") || args[0].equalsIgnoreCase("off")) {
			if(args[0].equalsIgnoreCase("on")) {
				if(Boolean.getBoolean(player.getData(DataType.ALLOW_PRIVATE_MESSAGES).toString())) {player.setData(DataType.ALLOW_PRIVATE_MESSAGES, true); player.getPlayer().sendMessage("Messages privés activés.");return;
				}
				player.getPlayer().sendMessage("Impossible d'activer les messages privés.");
				return;
			}
			if(!Boolean.getBoolean(player.getData(DataType.ALLOW_PRIVATE_MESSAGES).toString())) {player.setData(DataType.ALLOW_PRIVATE_MESSAGES, false); player.getPlayer().sendMessage("Messages privés désactivés."); return;}
			player.getPlayer().sendMessage("Impossible de désactiver les messages privés.");
			return;
		}
		ProxiedFaruPlayer target;
		if(ProxyFaruGamesAPI.getInstance().getProxy().getPlayer(args[0]) != null) {
			target = ProxiedFaruPlayer.getPlayer(ProxyFaruGamesAPI.getInstance().getProxy().getPlayer(args[0]).getUniqueId());
		} else {
			player.getPlayer().sendMessage(I18n.tl(player.getLanguage(), "api.methods.error"));
			return;
		}
		if(!Boolean.getBoolean(player.getData(DataType.ALLOW_PRIVATE_MESSAGES).toString())) {return;}
		if(Boolean.getBoolean(target.getData(DataType.ALLOW_PRIVATE_MESSAGES).toString())) {return;}
		StringBuilder builder = new StringBuilder();
		for(int i = 1; i <= args.length; i++) {
			builder.append(args[i] + " ");
		}
		player.getPlayer().sendMessage(player.getPlayer().getName() + " > " + builder.toString());
		target.getPlayer().sendMessage(player.getPlayer().getName() + " > " + builder.toString());
		player.setLastTalked(target);
		target.setLastTalked(player);
		return;
	}
	
	public String help() {
		return "";
	}
}
