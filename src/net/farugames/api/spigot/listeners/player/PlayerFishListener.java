package net.farugames.api.spigot.listeners.player;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;

import net.farugames.api.spigot.Main;

public class PlayerFishListener implements Listener {

	@EventHandler
	public void onPlayerFish(PlayerFishEvent event) {
		if(!Main.getInstance().getEvent(this)) {
			event.setCancelled(true);
		}
	}
}
