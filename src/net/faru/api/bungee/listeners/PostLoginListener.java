package net.faru.api.bungee.listeners;

import net.faru.api.bungee.BungeeFaruAPI;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class PostLoginListener implements Listener {

	@EventHandler
	public void postLoginListener(PostLoginEvent event) {
		ProxiedPlayer player = event.getPlayer();
		
		if(BungeeFaruAPI.isMaintenance() && !BungeeFaruAPI.isMaintenance(player.getName())) player.disconnect();
	}
}
