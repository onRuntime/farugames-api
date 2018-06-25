package net.faru.api.spigot.listeners;

import org.bukkit.event.Listener;

import net.faru.api.spigot.listeners.player.PlayerJoinListener;

public enum Listeners {
	
	PLAYER_JOIN_LISTENER(new PlayerJoinListener());
	
	private Listener listener;
	
	Listeners(Listener listener) {
		this.listener = listener;
	}
	
	public Listener getListener() {
		return this.listener;
	}
}
