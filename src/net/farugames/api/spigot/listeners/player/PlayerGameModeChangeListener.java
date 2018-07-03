package net.farugames.api.spigot.listeners.player;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerGameModeChangeEvent;

import net.farugames.api.spigot.Main;

public class PlayerGameModeChangeListener implements Listener {

	@EventHandler
	public void onPlayerGameModeChange(PlayerGameModeChangeEvent event) {
		if(!Main.getInstance().getEvent(this)) {
			event.setCancelled(true);
		}
	}

}
