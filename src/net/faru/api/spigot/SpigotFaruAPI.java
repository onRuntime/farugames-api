package net.faru.api.spigot;

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
		IServer.create(Bukkit.getServerName(), "149.202.102.63", Bukkit.getPort(), null, null);
		
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
