package net.farugames.api.spigot.listeners.player;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerAnimationEvent;

import net.farugames.api.spigot.Main;

public class PlayerAnimationListener implements Listener {

	@EventHandler
	public void onPlayerAnimation(PlayerAnimationEvent event) {
		if(!Main.getInstance().getEvent(this)) {
			event.setCancelled(true);
		}
	}
}
