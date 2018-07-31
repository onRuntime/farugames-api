package net.farugames.api.spigot;

import com.mojang.authlib.GameProfile;
import net.farugames.api.bungee.servers.ServerStatut;
import net.farugames.api.managers.BukkitListenerManager;
import net.farugames.api.redis.RedisManager;
import net.farugames.api.redis.spigot.PubSubSpigot;
import net.farugames.api.spigot.commands.GamemodeCommand;
import net.farugames.api.spigot.commands.NickCommand;
import net.farugames.api.spigot.commands.TeleportCommand;
import net.farugames.api.spigot.player.FaruPlayer;
import net.farugames.api.spigot.player.nick.NickManager;
import net.farugames.api.sql.SQLManager;
import net.farugames.api.sql.accounts.IServer;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

public class SpigotFaruAPI extends JavaPlugin {
	
	private static SpigotFaruAPI instance;
	
	public static Map<UUID, FaruPlayer> iFaruPlayer = new HashMap<>();
	
	private static Collection<Listener> events = new ArrayList<>();

	public SQLManager sqlManager;
	public RedisManager redisManager;

	public PubSubSpigot redisPubSubSpigot;

	public void onLoad() {
		instance = this;

		redisManager = new RedisManager("149.202.102.63","b4z5MT4Nk6hA",6379);
		redisManager.connect();

		sqlManager = new SQLManager("149.202.102.63","farugames","proxy","HCK2u7a8Up4d",3306);
		sqlManager.connect();

		super.onLoad();
	}
	
	public void onEnable() {
		new BukkitListenerManager().register();
		getCommand("gamemode").setExecutor(new GamemodeCommand());
		getCommand("teleport").setExecutor(new TeleportCommand());
		getCommand("nick").setExecutor(new NickCommand());

		NickManager.name = NickManager.getField(GameProfile.class, "name");
		redisPubSubSpigot = new PubSubSpigot();
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
