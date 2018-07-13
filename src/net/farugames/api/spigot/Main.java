package net.farugames.api.spigot;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import net.farugames.api.bungee.servers.ServerStatut;
import net.farugames.api.managers.BukkitListenerManager;
import net.farugames.api.spigot.commands.GamemodeCommand;
import net.farugames.api.spigot.commands.TeleportCommand;
import net.farugames.api.spigot.player.FaruPlayer;
import net.farugames.data.database.servers.IServer;

public class Main extends JavaPlugin {
	
	private static Main instance;
	
	public static Map<UUID, FaruPlayer> iFaruPlayer = new HashMap<UUID, FaruPlayer>();
	private static Collection<Listener> events = new ArrayList<Listener>();
	
	public void onLoad() {
		instance = this;
		
		super.onLoad();
	}
	
	public void onEnable() {
		new BukkitListenerManager().register();
		getCommand("gamemode").setExecutor(new GamemodeCommand());
		getCommand("teleport").setExecutor(new TeleportCommand());
		
		super.onEnable();
	}
	
	public void onDisable() {
		IServer.remove(Bukkit.getServerName(), ServerStatut.DELETE);//remove(Bukkit.getServerName(), ServerStatut.DELETE);
		super.onDisable();
	}
	
	public static Main getInstance() {
		return instance;
	}
	
	public Collection<Listener> getEvents() {
		return events;
	}
	
	public Boolean getEvent(Listener event) {
		return events.contains(event) ? true : false;
	}
	
	public void enableEvent(Listener event) {
		if(getEvent(event)) {
			events.remove(event);
		}
		return;
	}
	
	public void disableEvent(Listener event) {
		if(!getEvent(event)) {
			events.add(event);
		}
		return;
	}
}
