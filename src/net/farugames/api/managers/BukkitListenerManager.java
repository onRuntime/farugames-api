package net.farugames.api.managers;

import net.farugames.api.spigot.SpigotFaruAPI;
import net.farugames.api.spigot.listeners.Listeners;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

public class BukkitListenerManager {

	public PluginManager pluginManager;
	public Plugin plugin;
	
	public BukkitListenerManager() {
		this.plugin = SpigotFaruAPI.getInstance();
		this.pluginManager = plugin.getServer().getPluginManager();
	}
	
	public void register() {
		for(Listeners listener : Listeners.values()) {
			this.pluginManager.registerEvents(listener.getListener(), this.plugin);
			SpigotFaruAPI.getInstance().enableEvent(listener.getListener());
		}
	}
}
