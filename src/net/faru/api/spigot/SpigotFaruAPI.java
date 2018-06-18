package net.faru.api.spigot;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.plugin.java.JavaPlugin;

import net.faru.api.servers.FaruServerAPI;

public class SpigotFaruAPI extends JavaPlugin {

	public static Map<String, FaruServerAPI> iFaruServers = new HashMap<String, FaruServerAPI>();
	
	private static SpigotFaruAPI instance;
	
	public void onLoad() {
		instance = this;
		
		super.onLoad();
	}
	
	public void onEnable() {
		super.onEnable();
	}
	
	public void onDisable() {
		super.onDisable();
	}
	
	public static SpigotFaruAPI getInstance() {
		return instance;
	}
}
