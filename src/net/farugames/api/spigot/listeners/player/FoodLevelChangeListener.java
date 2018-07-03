package net.farugames.api.spigot.listeners.player;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

import net.farugames.api.spigot.Main;

public class FoodLevelChangeListener implements Listener {

	@EventHandler
	public void onFoodLevelChange(FoodLevelChangeEvent event) {
		if(!Main.getInstance().getEvent(this)) {
			event.setCancelled(true);
		}
	}
}
