package net.farugames.api.spigot;

import net.farugames.api.bungee.servers.ServerStatut;
import net.farugames.api.managers.BukkitListenerManager;
import net.farugames.api.spigot.commands.GamemodeCommand;
import net.farugames.api.spigot.commands.TeleportCommand;
import net.farugames.api.spigot.player.FaruPlayer;
import net.farugames.data.spigot.SpigotFaruData;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

public class SpigotFaruAPI extends JavaPlugin {
	
	private static SpigotFaruAPI instance;
	
	public static Map<UUID, FaruPlayer> iFaruPlayer = new HashMap<>();
	
	private static Collection<Listener> events = new ArrayList<>();
	
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
		SpigotFaruData.getInstance().getSpigotDatabase().update(Bukkit.getServerName(), ServerStatut.DELETE);
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
