package net.farugames.api.spigot.listeners.player;

import net.farugames.api.spigot.SpigotFaruGamesAPI;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerArmorStandManipulateEvent;

public class PlayerArmorStandManipulateListener implements Listener {

	@EventHandler
	public void onPlayerArmorStandManipulate(PlayerArmorStandManipulateEvent event) {
		if(!SpigotFaruGamesAPI.getInstance().getEvent(this)) {
			event.setCancelled(true);
		}
	}
}
