package net.farugames.api.bungee.listeners.event;

import net.farugames.api.bungee.BungeeFaruAPI;
import net.farugames.api.bungee.proxiedplayer.FaruBungeePlayer;
import net.farugames.api.spigot.player.data.DataType;
import net.farugames.api.spigot.player.languages.Lang;
import net.farugames.api.spigot.player.rank.Rank;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

import java.util.Date;

public class PostLoginListener implements Listener {

	@EventHandler
	public void postLoginListener(PostLoginEvent event) {
		FaruBungeePlayer faruBungeePlayer = FaruBungeePlayer.getPlayer(event.getPlayer().getUniqueId());
		
		faruBungeePlayer.setData(DataType.LAST_IP, String.valueOf(event.getPlayer().getAddress().getHostString()));
		faruBungeePlayer.setData(DataType.LAST_LOGIN, new Date());
		if(faruBungeePlayer.getPlayer() != event.getPlayer()) faruBungeePlayer.setPlayer(event.getPlayer());
		if(faruBungeePlayer.isOnline() || BungeeFaruAPI.isMaintenance() && faruBungeePlayer.getRank().getPower() < Rank.FRIEND.getPower()) {
			faruBungeePlayer.needDisconnect(true);
			if(faruBungeePlayer.isOnline()) faruBungeePlayer.setDisconnectReason(Lang.BAD_ACCOUNT);
			if(BungeeFaruAPI.isMaintenance() && faruBungeePlayer.getRank().getPower() < Rank.FRIEND.getPower()) faruBungeePlayer.setDisconnectReason(Lang.MAINTENANCE_DISCONNECT_MESSAGE);
		}
	}
}
