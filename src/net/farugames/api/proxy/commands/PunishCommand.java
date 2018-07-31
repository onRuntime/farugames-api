package net.farugames.api.proxy.commands;

import net.farugames.api.core.lang.LangOld;
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
		LangOld lang = faruPlayer.getLanguage();
		
		if(faruPlayer.getPermissionLevel() < Rank.HELPER.getPower()) { player.sendMessage(LangOld.ERROR.in(lang) + "\n" + LangOld.NO_PERMISSION_MESSAGE.in(lang)); return; }
		if(args == null || args.length > 2 || args[0].equalsIgnoreCase("help")) { player.sendMessage(this.HELP()); return; }
		
		if(args.length == 1) {
			FaruPlayer faruTarget = FaruPlayer.getPlayer(UUIDFetcher.getUUID(args[1])) != null ?
					FaruPlayer.getPlayer(UUIDFetcher.getUUID(args[1])) :
						null;
			if(faruPlayer == faruTarget) { player.sendMessage(LangOld.ERROR.in(lang) + "\n" + LangOld.UNPUNISHABLE_PLAYER.in(lang)); return; }
			if(faruTarget == null) { player.sendMessage(LangOld.ERROR.in(lang) + "\n" + LangOld.BAD_ACCOUNT.in(lang)); return; }
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
