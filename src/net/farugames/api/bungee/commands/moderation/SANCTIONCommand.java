package net.farugames.api.bungee.commands.moderation;

import net.farugames.api.bungee.sanctions.Sanction;
import net.farugames.api.spigot.player.FaruPlayer;
import net.farugames.api.spigot.player.languages.Lang;
import net.farugames.api.spigot.player.rank.Rank;
import net.farugames.api.tools.player.UUIDManager;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class SANCTIONCommand extends Command {

	public SANCTIONCommand() {
		super("punish");
	}

	@SuppressWarnings("deprecation")
	@Override
	public void execute(CommandSender sender, String[] args) {
		ProxiedPlayer player = (ProxiedPlayer) sender;
		FaruPlayer faruPlayer = FaruPlayer.getPlayer(player.getUniqueId());
		Lang lang = faruPlayer.getLanguage();
		
		if(faruPlayer.getRank().getPower() < Rank.HELPER.getPower()) { player.sendMessage(Lang.ERROR.in(lang) + "\n" + Lang.NO_PERMISSION_MESSAGE.in(lang)); return; }
		if(args == null || args.length > 2 || args[0].equalsIgnoreCase("help")) { player.sendMessage(this.HELP()); return; }
		
		if(args.length == 1) {
			FaruPlayer faruTarget = FaruPlayer.getPlayer(UUIDManager.getUUID(args[1])) != null ?
					FaruPlayer.getPlayer(UUIDManager.getUUID(args[1])) :
						null;
			if(faruPlayer == faruTarget) { player.sendMessage(Lang.ERROR.in(lang) + "\n" + Lang.UNPUNISHABLE_PLAYER.in(lang)); return; }
			if(faruTarget == null) { player.sendMessage(Lang.ERROR.in(lang) + "\n" + Lang.BAD_ACCOUNT.in(lang)); return; }
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
