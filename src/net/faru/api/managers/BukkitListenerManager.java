package net.faru.api.managers;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

import net.faru.api.spigot.SpigotFaruAPI;

public class BukkitListenerManager {

	public PluginManager pluginManager;
	public Plugin plugin;
	
	public BukkitListenerManager() {
		this.plugin = SpigotFaruAPI.getInstance();
		this.pluginManager = plugin.getServer().getPluginManager();
	}
	
	public void register() {
	}
}
