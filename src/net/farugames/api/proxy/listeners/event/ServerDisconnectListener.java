package net.farugames.api.proxy.listeners.event;

import net.farugames.api.proxy.ProxiedFaruPlayer;
import net.md_5.bungee.api.event.ServerDisconnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class ServerDisconnectListener implements Listener {

	@EventHandler
	public void onServerDisconnect(ServerDisconnectEvent event) {
		ProxiedFaruPlayer.getPlayer(event.getPlayer().getUniqueId()).disconnect();
	}
}
