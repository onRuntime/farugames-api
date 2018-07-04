package net.farugames.api.bungee.listeners;

import net.farugames.api.bungee.listeners.event.PostLoginListener;
import net.farugames.api.bungee.listeners.event.ProxyPingListener;
import net.farugames.api.bungee.listeners.event.ServerConnectListener;
import net.farugames.api.bungee.listeners.event.ServerDisconnectListener;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.plugin.PluginManager;

public class ListenersManager {

	public Plugin plugin;
	public PluginManager pluginManager ;

	public ListenersManager(Plugin plugin) {
		this.plugin = plugin;
		this.pluginManager = ProxyServer.getInstance().getPluginManager();
		// TODO Auto-generated constructor stub
	}

	public void registerListeners() {
		pluginManager.registerListener(plugin, new PostLoginListener());
		pluginManager.registerListener(plugin, new ServerConnectListener());
		pluginManager.registerListener(plugin, new ServerDisconnectListener());
		pluginManager.registerListener(plugin, new ProxyPingListener());
	}
	
	

}
