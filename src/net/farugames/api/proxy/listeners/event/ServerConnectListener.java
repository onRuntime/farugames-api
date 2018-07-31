package net.farugames.api.proxy.listeners.event;

import net.farugames.api.proxy.ProxiedFaruPlayer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.event.ServerConnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class ServerConnectListener implements Listener {

	@EventHandler
	public void onServerConnect(ServerConnectEvent event) {
		ProxiedFaruPlayer faruProxiedPlayer = ProxiedFaruPlayer.getPlayer(event.getPlayer().getUniqueId());
		if(faruProxiedPlayer.needDisconnect()) {
			faruProxiedPlayer.getPlayer().disconnect(new TextComponent(faruProxiedPlayer.getDisconnectReason()));
			faruProxiedPlayer.disconnect();
			event.setCancelled(true);
		}
		event.getPlayer().sendMessage("" + faruProxiedPlayer.getLanguage());
	}
}
