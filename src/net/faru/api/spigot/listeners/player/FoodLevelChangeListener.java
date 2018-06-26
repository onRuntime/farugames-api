package net.faru.api.spigot.listeners.player;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

import net.faru.api.spigot.SpigotFaruAPI;

public class FoodLevelChangeListener implements Listener {

	@EventHandler
	public void onFoodLevelChange(FoodLevelChangeEvent event) {
		if(!SpigotFaruAPI.getInstance().getEvent(this)) {
			event.setCancelled(true);
		}
	}
}
