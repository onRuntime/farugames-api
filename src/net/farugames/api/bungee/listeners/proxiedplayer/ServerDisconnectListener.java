package net.farugames.api.bungee.listeners.proxiedplayer;

import net.farugames.api.bungee.proxiedplayer.FaruBungeePlayer;
import net.md_5.bungee.api.event.ServerDisconnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class ServerDisconnectListener implements Listener {

	@EventHandler
	public void onServerDisconnect(ServerDisconnectEvent event) {
		FaruBungeePlayer.getPlayer(event.getPlayer().getUniqueId()).disconnect();
	}
}
