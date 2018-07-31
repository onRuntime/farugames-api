package net.farugames.api.spigot.listeners.player;

import net.farugames.api.spigot.SpigotFaruGamesAPI;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener {

    @EventHandler
    public static void onPlayerQuit(PlayerQuitEvent event) {
        System.out.println(SpigotFaruGamesAPI.iFaruPlayer.containsKey(event.getPlayer().getUniqueId()));
        if(SpigotFaruGamesAPI.iFaruPlayer.containsKey(event.getPlayer().getUniqueId()))
            SpigotFaruGamesAPI.iFaruPlayer.remove(event.getPlayer().getUniqueId());
        System.out.println(SpigotFaruGamesAPI.iFaruPlayer.containsKey(event.getPlayer().getUniqueId()));
        event.setQuitMessage(null);
    }
}
