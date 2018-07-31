package net.farugames.api.proxy.listeners.event;

import net.farugames.api.proxy.ProxiedFaruPlayer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.event.ServerConnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class ServerConnectListener implements Listener {

	@EventHandler
	public void onServerConnect(ServerConnectEvent event) {
		ProxiedFaruPlayer faruBungeePlayer = ProxiedFaruPlayer.getPlayer(event.getPlayer().getUniqueId());
		if(faruBungeePlayer.needDisconnect()) {
			faruBungeePlayer.getPlayer().disconnect(new TextComponent(faruBungeePlayer.getDisconnectReason().in(faruBungeePlayer.getLanguage())));
			faruBungeePlayer.disconnect();
			event.setCancelled(true);
		}
	}
}
