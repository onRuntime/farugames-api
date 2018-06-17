package net.faru.api.bungee;

import net.md_5.bungee.api.plugin.Plugin;

public class BungeeFaruAPI extends Plugin {

	private static BungeeFaruAPI instance;
	
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
	
	public static BungeeFaruAPI getInstance() {
		return instance;
	}
}
