package net.farugames.api.spigot.listeners.entity;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import net.farugames.api.spigot.Main;

public class EntityDamageListener implements Listener {

	@EventHandler
	public void onEntityDamage(EntityDamageEvent event) {
		if(!Main.getInstance().getEvent(this)) {
			event.setCancelled(true);
		}
	}
}
