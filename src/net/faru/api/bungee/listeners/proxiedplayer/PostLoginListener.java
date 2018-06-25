package net.faru.api.bungee.listeners.proxiedplayer;

import net.faru.api.bungee.BungeeFaruAPI;
import net.faru.api.bungee.proxiedplayer.FaruBungeePlayer;
import net.faru.api.player.languages.Lang;
import net.faru.api.player.rank.Rank;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class PostLoginListener implements Listener {

	@EventHandler
	public void postLoginListener(PostLoginEvent event) {
		FaruBungeePlayer faruBungeePlayer = FaruBungeePlayer.getPlayer(event.getPlayer().getUniqueId());
		if(faruBungeePlayer.getPlayer() != event.getPlayer()) faruBungeePlayer.setPlayer(event.getPlayer());
		if(faruBungeePlayer.isOnline() || BungeeFaruAPI.isMaintenance() && faruBungeePlayer.getRank().getPower() < Rank.FRIEND.getPower()) {
			faruBungeePlayer.needDisconnect(true);
			if(faruBungeePlayer.isOnline()) faruBungeePlayer.setDisconnectReason(Lang.BAD_ACCOUNT);
			if(BungeeFaruAPI.isMaintenance() && faruBungeePlayer.getRank().getPower() < Rank.FRIEND.getPower()) faruBungeePlayer.setDisconnectReason(Lang.MAINTENANCE_DISCONNECT_MESSAGE);
		}
	}
}
