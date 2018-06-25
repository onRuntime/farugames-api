package net.faru.api.patchs.player;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;

public class PvP {

	public static void patch(UUID uuid) {
		Player player = Bukkit.getPlayer(uuid) != null ? Bukkit.getPlayer(uuid) : null;
		if(player == null) return;
        player.getAttribute(Attribute.GENERIC_ATTACK_SPEED).setBaseValue(16.0);
    }
}
