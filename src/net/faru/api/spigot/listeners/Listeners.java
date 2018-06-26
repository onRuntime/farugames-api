package net.faru.api.spigot.listeners;

import org.bukkit.event.Listener;

import net.faru.api.spigot.listeners.player.FoodLevelChangeListener;
import net.faru.api.spigot.listeners.player.PlayerJoinListener;
import net.faru.api.spigot.listeners.world.WeatherChangeListener;

public enum Listeners {
	
	PLAYER_JOIN_LISTENER(new PlayerJoinListener()),
	
	FOOD_LEVEL_CHANGE_LISTENER(new FoodLevelChangeListener()),
	
	WEATHER_CHANGE_LISTENER(new WeatherChangeListener());
	
	private Listener listener;
	
	Listeners(Listener listener) {
		this.listener = listener;
	}
	
	public Listener getListener() {
		return this.listener;
	}
}
