package net.farugames.api.proxy.commands;

import net.farugames.api.core.lang.I18n;
import net.farugames.api.core.lang.Lang;
import net.farugames.api.core.rank.Rank;
import net.farugames.api.core.sanction.Sanction;
import net.farugames.api.spigot.FaruPlayer;
import net.farugames.api.tools.player.UUIDFetcher;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class PunishCommand extends Command {

	public PunishCommand() {
		super("punish");
	}

	@SuppressWarnings("deprecation")
	@Override
	public void execute(CommandSender sender, String[] args) {
		ProxiedPlayer player = (ProxiedPlayer) sender;
		FaruPlayer faruPlayer = FaruPlayer.getPlayer(player.getUniqueId());
		Lang lang = faruPlayer.getLanguage();
		
		if(faruPlayer.getPermissionLevel() < Rank.HELPER.getPower()) { player.sendMessage(I18n.tl(lang, "api.methods.error")); return; }
		if(args == null || args.length > 2 || args[0].equalsIgnoreCase("help")) { player.sendMessage(this.HELP()); return; }
		
		if(args.length == 1) {
			FaruPlayer faruTarget = FaruPlayer.getPlayer(UUIDFetcher.getUUID(args[1])) != null ?
					FaruPlayer.getPlayer(UUIDFetcher.getUUID(args[1])) :
						null;
			if(faruPlayer == faruTarget) { player.sendMessage(I18n.tl(lang, "api.methods.error")); return; }
			if(faruTarget == null) { player.sendMessage(I18n.tl(lang, "api.methods.error")); return; }
			new Sanction(faruPlayer, faruTarget, null);
			return;
		}
		
		else {
			return;
		}
	}
	
	private String HELP() {
		return "";
	}
}
