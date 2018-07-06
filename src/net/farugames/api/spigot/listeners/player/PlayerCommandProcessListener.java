package net.farugames.api.spigot.listeners.player;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class PlayerCommandProcessListener implements Listener {

	@EventHandler
	public void onPlayerCommandProcess(PlayerCommandPreprocessEvent e) {
		String cmd = e.getMessage();
		if (cmd.equalsIgnoreCase("/rl") || cmd.equalsIgnoreCase("/reload") || cmd.equalsIgnoreCase("/bukkit:reload")
				|| cmd.equalsIgnoreCase("/bukkit:rl")) {
			e.setCancelled(true);
			// errormsg
		}
	}
}
