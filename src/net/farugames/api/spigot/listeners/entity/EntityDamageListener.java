package net.farugames.api.spigot.listeners.entity;

import net.farugames.api.spigot.SpigotFaruGamesAPI;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class EntityDamageListener implements Listener {

	@EventHandler
	public void onEntityDamage(EntityDamageEvent event) {
		if(!SpigotFaruGamesAPI.getInstance().getEvent(this)) {
			event.setCancelled(true);
		}
	}
}
