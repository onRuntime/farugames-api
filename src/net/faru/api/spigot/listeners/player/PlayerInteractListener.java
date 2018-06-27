package net.faru.api.spigot.listeners.player;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import net.faru.api.spigot.SpigotFaruAPI;

public class PlayerInteractListener implements Listener {

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		if(!SpigotFaruAPI.getInstance().getEvent(this)) {
			event.setCancelled(true);
		}
	}

}
