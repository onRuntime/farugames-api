package net.farugames.api.spigot.listeners.player;

import net.farugames.api.core.rank.Rank;
import net.farugames.api.spigot.FaruPlayer;
import net.farugames.api.spigot.patchs.CollisionsPatch;
import net.farugames.api.spigot.patchs.PvpPatch;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

	@EventHandler(priority = EventPriority.HIGHEST)
	public static void onPlayerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		FaruPlayer faruPlayer = FaruPlayer.getPlayer(player.getUniqueId());
		if(faruPlayer.getPermissionLevel() == Rank.OWNER.getPower()) player.setOp(true);
		CollisionsPatch.patch(player.getUniqueId());
		PvpPatch.patch(player.getUniqueId());
		event.setJoinMessage(null);
	}

}
