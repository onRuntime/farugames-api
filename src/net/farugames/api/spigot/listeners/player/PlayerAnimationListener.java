package net.farugames.api.spigot.listeners.player;

import net.farugames.api.spigot.SpigotFaruGamesAPI;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerAnimationEvent;

public class PlayerAnimationListener implements Listener {

	@EventHandler
	public void onPlayerAnimation(PlayerAnimationEvent event) {
		if(!SpigotFaruGamesAPI.getInstance().getEvent(this)) {
			event.setCancelled(true);
		}
	}
}
