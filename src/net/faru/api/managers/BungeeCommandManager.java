package net.faru.api.managers;

import net.faru.api.bungee.BungeeFaruAPI;
import net.faru.api.bungee.commands.Commands;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.plugin.PluginManager;

public class BungeeCommandManager {

	public PluginManager pluginManager;
	public Plugin plugin;
	
	public BungeeCommandManager() {
		this.plugin = BungeeFaruAPI.getInstance();
		this.pluginManager = plugin.getProxy().getPluginManager();
	}
	
	public void register() {
		for(Commands command : Commands.values()) {
			this.pluginManager.registerCommand(this.plugin, command.getCommand());
		}
	}
}
