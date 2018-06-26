package net.faru.api.spigot;

import java.util.ArrayList;
import java.util.Collection;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import net.faru.api.bungee.servers.ServerStatut;
import net.faru.api.managers.BukkitListenerManager;
import net.faru.data.database.servers.IServer;

public class SpigotFaruAPI extends JavaPlugin {
	
	private static SpigotFaruAPI instance;
	
	private static Collection<Listener> events = new ArrayList<Listener>();
	
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
