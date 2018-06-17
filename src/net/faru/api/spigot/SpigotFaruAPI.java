package net.faru.api.spigot;

import org.bukkit.plugin.java.JavaPlugin;

public class SpigotFaruAPI extends JavaPlugin {

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
