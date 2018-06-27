package net.faru.api.spigot.listeners.player;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerGameModeChangeEvent;

import net.faru.api.spigot.SpigotFaruAPI;

public class PlayerGameModeChangeListener implements Listener {

	@EventHandler
	public void onPlayerGameModeChange(PlayerGameModeChangeEvent event) {
		if(!SpigotFaruAPI.getInstance().getEvent(this)) {
			event.setCancelled(true);
		}
	}

}
