package net.faru.api.bungee;

import java.util.ArrayList;
import java.util.List;

import net.faru.api.managers.BungeeListenerManager;
import net.faru.api.servers.FaruServerAPI;
import net.faru.api.servers.ServerType;
import net.faru.data.database.servers.IMaintenance;
import net.md_5.bungee.api.plugin.Plugin;

public class BungeeFaruAPI extends Plugin {

	private static BungeeFaruAPI instance;
	
	private static Boolean maintenance = false;
	private static List<String> maintenancePlayers = new ArrayList<String>();
	
	public void onLoad() {
		instance = this;
		if(IMaintenance.isEnable()) maintenance = true;
		
		super.onLoad();
	}
	
	public void onEnable() {
		new FaruServerAPI(ServerType.PROXY).register();
		
		new BungeeListenerManager().register();
		
		super.onEnable();
	}
	
	public void onDisable() {
		super.onDisable();
	}
	
	public static Boolean isMaintenance() {
		return maintenance;
	}
	
	public static Boolean isMaintenance(String player) {
		return maintenancePlayers.contains(player);
	}
	
	public static BungeeFaruAPI getInstance() {
		return instance;
	}
}
