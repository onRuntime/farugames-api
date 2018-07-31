package net.farugames.api.redis.spigot;

import net.farugames.api.redis.RedisManager;
import net.farugames.api.spigot.SpigotFaruAPI;
import net.farugames.api.spigot.player.FaruPlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

import java.io.Closeable;
import java.io.IOException;

/**
 * Created by SweetKebab_ on 2018-07-25
 */
public class PubSubSpigot implements Closeable {

    private static final String CHANNEL_BROADCAST = "CHANNELSPIGOT_BROADCAST";
    private static final String CHANNEL_BROADCASTSTAFF = "CHANNELSPIGOT_BROADCASTSTAFF";

    private BroadcastListener listener;

    public PubSubSpigot() {
        listener = new BroadcastListener();
        new BukkitRunnable() {

            @Override
            public void run() {
                try (Jedis jedis = RedisManager.getJedisPool().getResource()) {
                    jedis.subscribe(listener, CHANNEL_BROADCAST, CHANNEL_BROADCASTSTAFF);
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("[RedisPubSubSpigot] Impossible d'enregistrer les Channels..");
                }
            }
        }.runTaskAsynchronously(SpigotFaruAPI.getInstance());
    }

    @Override
    public void close() throws IOException {
        listener.unsubscribe(CHANNEL_BROADCAST, CHANNEL_BROADCASTSTAFF);
    }
    protected class BroadcastListener extends JedisPubSub {
        @Override
        public void onMessage(String channel, String message) {

            /** Broadcast */
            if (CHANNEL_BROADCAST.equalsIgnoreCase(channel)) {
                Bukkit.broadcastMessage(message);
            }

            /** Broadcast to Staff */
            if (CHANNEL_BROADCASTSTAFF.equalsIgnoreCase(channel)) {
                for(Player playerOnline : Bukkit.getOnlinePlayers()) {
                    FaruPlayer faruPlayer = FaruPlayer.getPlayer(playerOnline.getUniqueId());
                    if(faruPlayer != null) {
                        if(faruPlayer.getPermissionLevel() >= 500) {
                            playerOnline.sendMessage(message);
                        }
                    }
                }
            }

        }
    }
}
