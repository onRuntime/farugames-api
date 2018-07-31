package net.farugames.api.spigot.listeners.player;

import net.farugames.api.spigot.SpigotFaruGamesAPI;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;

public class PlayerFishListener implements Listener {

	@EventHandler
	public void onPlayerFish(PlayerFishEvent event) {
		if(!SpigotFaruGamesAPI.getInstance().getEvent(this)) {
			event.setCancelled(true);
		}
	}
}
