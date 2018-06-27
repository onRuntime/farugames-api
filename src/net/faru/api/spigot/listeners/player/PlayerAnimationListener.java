package net.faru.api.spigot.listeners.player;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerAnimationEvent;

import net.faru.api.spigot.SpigotFaruAPI;

public class PlayerAnimationListener implements Listener {

	@EventHandler
	public void onPlayerAnimation(PlayerAnimationEvent event) {
		if(!SpigotFaruAPI.getInstance().getEvent(this)) {
			event.setCancelled(true);
		}
	}
}
