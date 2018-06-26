package net.faru.api.spigot.listeners.world;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;

import net.faru.api.spigot.SpigotFaruAPI;

public class WeatherChangeListener implements Listener {

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onWeatherChange(WeatherChangeEvent event) {
		if(!SpigotFaruAPI.getInstance().getEvent(this)) {
			event.setCancelled(true);
		}
	}
}
