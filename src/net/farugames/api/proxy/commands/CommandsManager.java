package net.farugames.api.proxy.commands;

import net.farugames.api.proxy.ProxyFaruGamesAPI;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.plugin.PluginManager;

public class CommandsManager {

	public PluginManager pluginManager;
	public Plugin plugin;
	
	public CommandsManager() {
		this.plugin = ProxyFaruGamesAPI.getInstance();
		this.pluginManager = plugin.getProxy().getPluginManager();
	}
	
	public void register() {
		for(Commands command : Commands.values()) {
			this.pluginManager.registerCommand(this.plugin, command.getCommand());
		}
	}
}
