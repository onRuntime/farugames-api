package net.farugames.api.proxy.listeners.event;

import java.util.Date;

import net.farugames.api.core.data.DataType;
import net.farugames.api.core.lang.I18n;
import net.farugames.api.core.lang.Lang;
import net.farugames.api.core.rank.Rank;
import net.farugames.api.proxy.ProxiedFaruPlayer;
import net.farugames.api.proxy.ProxyFaruGamesAPI;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class PostLoginListener implements Listener {

	@EventHandler
	public void postLoginListener(PostLoginEvent event) {
		ProxiedFaruPlayer faruBungeePlayer = ProxiedFaruPlayer.getPlayer(event.getPlayer().getUniqueId());
		
		faruBungeePlayer.setData(DataType.LAST_IP, String.valueOf(event.getPlayer().getAddress().getHostString()));
		faruBungeePlayer.setData(DataType.LAST_LOGIN, new Date());
		if(faruBungeePlayer.getPlayer() != event.getPlayer()) faruBungeePlayer.setPlayer(event.getPlayer());
		if(faruBungeePlayer.isOnline() || ProxyFaruGamesAPI.isMaintenance() && faruBungeePlayer.getPermissionLevel() < Rank.FRIEND.getPower()) {
			faruBungeePlayer.needDisconnect(true);
			if(faruBungeePlayer.isOnline()) faruBungeePlayer.setDisconnectReason(I18n.tl(faruBungeePlayer.getLanguage(), "api_proxy_disconnect_reason_badaccount"));
			if(ProxyFaruGamesAPI.isMaintenance() && faruBungeePlayer.getPermissionLevel() < Rank.FRIEND.getPower()) faruBungeePlayer.setDisconnectReason(I18n.tl(Lang.ENGLISH, "api_proxy_disconnect_reason_badaccount"));
		}
	}
}
