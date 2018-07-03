package net.farugames.api.managers;

import net.farugames.api.bungee.Main;
import net.farugames.api.bungee.listeners.Listeners;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.plugin.PluginManager;

public class BungeeListenerManager {

	public PluginManager pluginManager;
	public Plugin plugin;
	
	public BungeeListenerManager() {
		this.plugin = Main.getInstance();
		this.pluginManager = plugin.getProxy().getPluginManager();
	}
	
	public void register() {
		for(Listeners listener : Listeners.values()) {
			this.pluginManager.registerListener(this.plugin, listener.getListener());
		}
	}
}
