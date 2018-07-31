package net.farugames.api.spigot.listeners;

import net.farugames.api.spigot.SpigotFaruGamesAPI;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

public class ListenersManager {

	public PluginManager pluginManager;
	public Plugin plugin;
	
	public ListenersManager() {
		this.plugin = SpigotFaruGamesAPI.getInstance();
		this.pluginManager = plugin.getServer().getPluginManager();
	}
	
	public void register() {
		for(Listeners listener : Listeners.values()) {
			this.pluginManager.registerEvents(listener.getListener(), this.plugin);
			SpigotFaruGamesAPI.getInstance().enableEvent(listener.getListener());
		}
	}
}
