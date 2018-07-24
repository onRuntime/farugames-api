package net.farugames.api.spigot.listeners.player;

import net.farugames.api.spigot.player.FaruPlayer;
import net.farugames.api.spigot.player.patchs.Collisions;
import net.farugames.api.spigot.player.patchs.PvP;
import net.farugames.api.spigot.player.rank.Rank;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		FaruPlayer faruPlayer = FaruPlayer.getPlayer(player.getUniqueId());
//		faruPlayer.loadPlayer();
		if(faruPlayer.getRank().getPower() == Rank.OWNER.getPower()) player.setOp(true);
		Collisions.patch(player.getUniqueId());
		PvP.patch(player.getUniqueId());
		event.setJoinMessage(null);
	}
}
