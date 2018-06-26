package net.faru.api.bungee.commands.general;

import net.faru.api.bungee.BungeeFaruAPI;
import net.faru.api.bungee.proxiedplayer.FaruBungeePlayer;
import net.faru.api.spigot.player.data.DataType;
import net.faru.api.spigot.player.languages.Lang;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.command.ConsoleCommandSender;

public class MESSAGECommand extends Command {

	public MESSAGECommand() {
		super("message", null, new String[] {"msg", "m", "whisper", "w", "tell", "t", "mp"});
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void execute(CommandSender sender, String[] args) {
		if(sender instanceof ConsoleCommandSender) return;
		if(args.length == 0 || args.length < 2) { sender.sendMessage(this.help()); return; };
		
		FaruBungeePlayer player = FaruBungeePlayer.getPlayer(((ProxiedPlayer) sender).getUniqueId());
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
		FaruBungeePlayer target;
		if(BungeeFaruAPI.getInstance().getProxy().getPlayer(args[0]) != null) {
			target = FaruBungeePlayer.getPlayer(BungeeFaruAPI.getInstance().getProxy().getPlayer(args[0]).getUniqueId());
		} else {
			player.getPlayer().sendMessage(Lang.ERROR.in(player.getLanguage()).replaceAll("%info%", "").replaceAll("%reason%", ""));
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
