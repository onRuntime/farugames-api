package net.faru.api.spigot;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import net.faru.api.managers.BukkitListenerManager;
import net.faru.api.servers.ServerStatut;
import net.faru.data.database.servers.IServer;

public class SpigotFaruAPI extends JavaPlugin {
	
	private static SpigotFaruAPI instance;
	
	public void onLoad() {
		instance = this;
		
		super.onLoad();
	}
	
	public void onEnable() {
		try {
			IServer.create(Bukkit.getServerName(), InetAddress.getLocalHost().getHostAddress(), Bukkit.getPort(), null, null);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
		new BukkitListenerManager().register();
		
		super.onEnable();
	}
	
	public void onDisable() {
		IServer.remove(Bukkit.getServerName(), ServerStatut.DELETE);
		
		super.onDisable();
	}
	
	public static SpigotFaruAPI getInstance() {
		return instance;
	}
}
