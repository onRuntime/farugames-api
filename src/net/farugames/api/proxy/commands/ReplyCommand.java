package net.farugames.api.proxy.commands;

import net.farugames.api.core.data.DataType;
import net.farugames.api.proxy.ProxiedFaruPlayer;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.command.ConsoleCommandSender;

public class ReplyCommand extends Command {

	public ReplyCommand() {
		super("reply", null, new String[] {"rep", "r", "response", "respond", "resp"});
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void execute(CommandSender sender, String[] args) {
		if(sender instanceof ConsoleCommandSender) return;
		if(args.length == 0 || args.length < 2) { sender.sendMessage(this.help()); return; };
		
		ProxiedFaruPlayer player = ProxiedFaruPlayer.getPlayer(((ProxiedPlayer) sender).getUniqueId());
		if(!Boolean.getBoolean(player.getData(DataType.ALLOW_PRIVATE_MESSAGES).toString())) {return;}
		if(!player.isLastTalked()) {return;}
		ProxiedFaruPlayer target = ProxiedFaruPlayer.getPlayer(player.getLastTalked().getPlayer().getUniqueId());
		if(!Boolean.getBoolean(target.getData(DataType.ALLOW_PRIVATE_MESSAGES).toString())) {return;}
		StringBuilder builder = new StringBuilder();
		for(int i = 1; i <= (args.length + 1); i++) {
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
