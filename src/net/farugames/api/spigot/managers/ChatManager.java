package net.farugames.api.spigot.managers;

import net.farugames.api.core.data.DataType;
import net.farugames.api.core.rank.Rank;
import net.farugames.api.spigot.FaruPlayer;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatManager {
	
	public static void customMessage(AsyncPlayerChatEvent e, String prefix, String suffix) {
		Player sender = e.getPlayer();
		FaruPlayer faruSender = FaruPlayer.getPlayer(sender.getUniqueId());
		Player fs = faruSender.getPlayer();
		Rank r = faruSender.getRank();
		String msg = e.getMessage();

		e.setCancelled(true);

		if ((boolean) faruSender.getData(DataType.ALLOW_CHAT)) {
			for (Player op : e.getRecipients()) {
				FaruPlayer faruOp = FaruPlayer.getPlayer(op.getUniqueId());
				Player fOp = faruOp.getPlayer();
				if (msg.contains(fOp.getName())) {
					if ((boolean) faruOp.getData(DataType.ALLOW_CHAT_MENTIONS)) {
						fOp.playSound(fOp.getLocation(), Sound.BLOCK_NOTE_PLING, 10, 1);
						// TitleManager.sendActionBar(fOp, "§a" + fs.getName() +
						// "§d vous a mentionné dans le chat !");
						msg = msg.replaceAll("@", "");
						msg = msg.replaceAll(fOp.getDisplayName(), "§e@" + fOp.getDisplayName() + r.getChatColor());
					}
				}
			}
			for (Player op : e.getRecipients()) {
				FaruPlayer faruOp = FaruPlayer.getPlayer(op.getUniqueId());
				if ((boolean) faruOp.getData(DataType.ALLOW_CHAT)) {
					op.sendMessage(prefix + r.getColor() + r.getPrefix() + fs.getName() + suffix + " §8»§r "
							+ r.getChatColor() + msg);
				}
			}
		} else {
			// errormsg
		}
	}

}
