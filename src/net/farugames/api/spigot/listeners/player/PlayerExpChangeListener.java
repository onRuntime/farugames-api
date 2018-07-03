package net.farugames.api.spigot.listeners.player;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerExpChangeEvent;

import net.farugames.api.spigot.Main;

public class PlayerExpChangeListener implements Listener {

	@EventHandler
	public void onPlayerExpChange(PlayerExpChangeEvent event) {
		if(!Main.getInstance().getEvent(this)) {
			event.setAmount(0);
		}
	}
}
