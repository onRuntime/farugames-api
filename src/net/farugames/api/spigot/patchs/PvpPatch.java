package net.farugames.api.spigot.patchs;

import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;

import java.util.UUID;

public class PvpPatch {

	public static void patch(UUID uuid) {
		Player player = Bukkit.getPlayer(uuid) != null ? Bukkit.getPlayer(uuid) : null;
		if(player == null) return;
        player.getAttribute(Attribute.GENERIC_ATTACK_SPEED).setBaseValue(16.0);
    }
}
