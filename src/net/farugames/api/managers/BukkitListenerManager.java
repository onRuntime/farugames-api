package net.farugames.api.managers;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

import net.farugames.api.spigot.Main;
import net.farugames.api.spigot.listeners.Listeners;

public class BukkitListenerManager {

	public PluginManager pluginManager;
	public Plugin plugin;
	
	public BukkitListenerManager() {
		this.plugin = Main.getInstance();
		this.pluginManager = plugin.getServer().getPluginManager();
	}
	
	public void register() {
		for(Listeners listener : Listeners.values()) {
			this.pluginManager.registerEvents(listener.getListener(), this.plugin);
			Main.getInstance().enableEvent(listener.getListener());
		}
	}
}
