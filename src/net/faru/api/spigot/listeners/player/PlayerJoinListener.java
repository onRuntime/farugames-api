package net.faru.api.spigot.listeners.player;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import net.faru.api.patchs.player.Collisions;
import net.faru.api.patchs.player.PvP;
import net.faru.api.player.FaruPlayer;
import net.faru.api.player.rank.Rank;

public class PlayerJoinListener implements Listener {

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		FaruPlayer faruPlayer = FaruPlayer.getPlayer(player.getUniqueId());
		if(faruPlayer.getRank().getPower() == Rank.OWNER.getPower()) player.setOp(true);
		Collisions.patch(player.getUniqueId());
		PvP.patch(player.getUniqueId());
		event.setJoinMessage(null);
	}
}
