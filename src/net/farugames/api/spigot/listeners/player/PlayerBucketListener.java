package net.farugames.api.spigot.listeners.player;

import net.farugames.api.spigot.SpigotFaruAPI;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBucketEvent;

public class PlayerBucketListener implements Listener {

	@EventHandler
	public void onPlayerBucket(PlayerBucketEvent event) {
		if(!SpigotFaruAPI.getInstance().getEvent(this)) {
			event.setCancelled(true);
		}
	}
}
