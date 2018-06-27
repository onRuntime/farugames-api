package net.faru.api.spigot.listeners.player;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;

import net.faru.api.spigot.SpigotFaruAPI;

public class PlayerFishListener implements Listener {

	@EventHandler
	public void onPlayerFish(PlayerFishEvent event) {
		if(!SpigotFaruAPI.getInstance().getEvent(this)) {
			event.setCancelled(true);
		}
	}
}
