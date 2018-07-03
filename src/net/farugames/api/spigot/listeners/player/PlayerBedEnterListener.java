package net.farugames.api.spigot.listeners.player;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;

import net.farugames.api.spigot.Main;

public class PlayerBedEnterListener implements Listener {

	@EventHandler
	public void onPlayerBedEnter(PlayerBedEnterEvent event) {
		if(!Main.getInstance().getEvent(this)) {
			event.setCancelled(true);
		}
	}
}
