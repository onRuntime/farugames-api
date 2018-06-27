package net.faru.api.spigot.listeners.player;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;

import net.faru.api.spigot.SpigotFaruAPI;

public class PlayerBedEnterListener implements Listener {

	@EventHandler
	public void onPlayerBedEnter(PlayerBedEnterEvent event) {
		if(!SpigotFaruAPI.getInstance().getEvent(this)) {
			event.setCancelled(true);
		}
	}
}
