package net.farugames.api.spigot.listeners.player;

import net.farugames.api.spigot.SpigotFaruAPI;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;

public class PlayerBedEnterListener implements Listener {

	@EventHandler
	public void onPlayerBedEnter(PlayerBedEnterEvent event) {
		if(!SpigotFaruAPI.getInstance().getEvent(this)) {
			event.setCancelled(true);
		}
	}
}
