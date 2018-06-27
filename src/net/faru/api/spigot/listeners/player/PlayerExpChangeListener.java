package net.faru.api.spigot.listeners.player;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerExpChangeEvent;

import net.faru.api.spigot.SpigotFaruAPI;

public class PlayerExpChangeListener implements Listener {

	@EventHandler
	public void onPlayerExpChange(PlayerExpChangeEvent event) {
		if(!SpigotFaruAPI.getInstance().getEvent(this)) {
			event.setAmount(0);
		}
	}
}
