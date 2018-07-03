package net.farugames.api.spigot;

import java.util.ArrayList;
import java.util.Collection;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import net.farugames.api.bungee.servers.ServerStatut;
import net.farugames.api.managers.BukkitListenerManager;
import net.farugames.data.database.servers.IServer;

public class Main extends JavaPlugin {
	
	private static Main instance;
	
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
