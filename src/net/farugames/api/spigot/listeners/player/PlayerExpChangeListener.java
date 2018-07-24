package net.farugames.api.spigot.listeners.player;

import net.farugames.api.spigot.SpigotFaruAPI;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerExpChangeEvent;

public class PlayerExpChangeListener implements Listener {

	@EventHandler
	public void onPlayerExpChange(PlayerExpChangeEvent event) {
		if(!SpigotFaruAPI.getInstance().getEvent(this)) {
			event.setAmount(0);
		}
	}
}
