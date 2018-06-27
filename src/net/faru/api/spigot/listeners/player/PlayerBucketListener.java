package net.faru.api.spigot.listeners.player;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBucketEvent;

import net.faru.api.spigot.SpigotFaruAPI;

public class PlayerBucketListener implements Listener {

	@EventHandler
	public void onPlayerBucket(PlayerBucketEvent event) {
		if(!SpigotFaruAPI.getInstance().getEvent(this)) {
			event.setCancelled(true);
		}
	}
}
