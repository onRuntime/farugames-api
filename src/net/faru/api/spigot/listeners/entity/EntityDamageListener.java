package net.faru.api.spigot.listeners.entity;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import net.faru.api.spigot.SpigotFaruAPI;

public class EntityDamageListener implements Listener {

	@EventHandler
	public void onEntityDamage(EntityDamageEvent event) {
		if(!SpigotFaruAPI.getInstance().getEvent(this)) {
			event.setCancelled(true);
		}
	}
}
