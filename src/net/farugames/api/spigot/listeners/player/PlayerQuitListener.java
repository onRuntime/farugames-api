package net.farugames.api.spigot.listeners.player;

import net.farugames.api.spigot.SpigotFaruAPI;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener {

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        System.out.println(SpigotFaruAPI.iFaruPlayer.containsKey(event.getPlayer().getUniqueId()));
        if(SpigotFaruAPI.iFaruPlayer.containsKey(event.getPlayer().getUniqueId()))
            SpigotFaruAPI.iFaruPlayer.remove(event.getPlayer().getUniqueId());
        System.out.println(SpigotFaruAPI.iFaruPlayer.containsKey(event.getPlayer().getUniqueId()));
        event.setQuitMessage(null);
    }
}
