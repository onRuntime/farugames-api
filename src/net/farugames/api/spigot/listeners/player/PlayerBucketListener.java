package net.farugames.api.spigot.listeners.player;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBucketEvent;

import net.farugames.api.spigot.Main;

public class PlayerBucketListener implements Listener {

	@EventHandler
	public void onPlayerBucket(PlayerBucketEvent event) {
		if(!Main.getInstance().getEvent(this)) {
			event.setCancelled(true);
		}
	}
}
