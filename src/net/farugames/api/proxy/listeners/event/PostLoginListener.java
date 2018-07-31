package net.farugames.api.proxy.listeners.event;

import net.farugames.api.proxy.ProxyFaruGamesAPI;
import net.farugames.api.core.data.DataType;
import net.farugames.api.core.lang.LangOld;
import net.farugames.api.core.rank.Rank;
import net.farugames.api.proxy.ProxiedFaruPlayer;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

import java.util.Date;

public class PostLoginListener implements Listener {

	@EventHandler
	public void postLoginListener(PostLoginEvent event) {
		ProxiedFaruPlayer faruBungeePlayer = ProxiedFaruPlayer.getPlayer(event.getPlayer().getUniqueId());
		
		faruBungeePlayer.setData(DataType.LAST_IP, String.valueOf(event.getPlayer().getAddress().getHostString()));
		faruBungeePlayer.setData(DataType.LAST_LOGIN, new Date());
		if(faruBungeePlayer.getPlayer() != event.getPlayer()) faruBungeePlayer.setPlayer(event.getPlayer());
		if(faruBungeePlayer.isOnline() || ProxyFaruGamesAPI.isMaintenance() && faruBungeePlayer.getPermissionLevel() < Rank.FRIEND.getPower()) {
			faruBungeePlayer.needDisconnect(true);
			if(faruBungeePlayer.isOnline()) faruBungeePlayer.setDisconnectReason(LangOld.BAD_ACCOUNT);
			if(ProxyFaruGamesAPI.isMaintenance() && faruBungeePlayer.getPermissionLevel() < Rank.FRIEND.getPower()) faruBungeePlayer.setDisconnectReason(LangOld.MAINTENANCE_DISCONNECT_MESSAGE);
		}
	}
}
