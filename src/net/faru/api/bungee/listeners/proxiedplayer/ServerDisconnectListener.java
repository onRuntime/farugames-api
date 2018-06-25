package net.faru.api.bungee.listeners.proxiedplayer;

import net.faru.api.bungee.player.FaruBungeePlayer;
import net.md_5.bungee.api.event.ServerDisconnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class ServerDisconnectListener implements Listener {

	@EventHandler
	public void onServerDisconnect(ServerDisconnectEvent event) {
		FaruBungeePlayer.getPlayer(event.getPlayer().getUniqueId()).disconnect();
	}
}
