package net.farugames.api.spigot.player.nick;

import net.farugames.api.redis.RedisManager;
import net.farugames.api.spigot.SpigotFaruAPI;
import net.farugames.api.spigot.player.rank.Rank;
import net.minecraft.server.v1_9_R2.EntityPlayer;
import net.minecraft.server.v1_9_R2.PacketPlayOutPlayerInfo;
import net.minecraft.server.v1_9_R2.PacketPlayOutRespawn;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_9_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import redis.clients.jedis.Jedis;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by SweetKebab_ on 2018-07-26
 */
public class NickManager {
    public static Field name;

    public static void enableNick(UUID uuid, String playerName, String name, String skinName) {
        try (Jedis jedis = RedisManager.getRedisDatabase().getJedisPool().getResource()) {
            boolean isNick = isNick(uuid);
            if (isNick) {
                jedis.lrem("nicklist", 0, getRealName(uuid));
            }
            jedis.set("nickplayers:" + uuid.toString() + ":" + "nick", "true");
            jedis.set("nickplayers:" + uuid.toString() + ":" + "nickname", name);
            jedis.set("nickplayers:" + uuid.toString() + ":" + "skin", skinName);

            if (!isNick) {
                jedis.set("nickplayers:" + uuid.toString() + ":" + "rank", Rank.PLAYER.getIdName());
            }

            if (!isNick) {
                jedis.set("nickplayers:" + uuid.toString() + ":" + "realname", playerName);
            }
            jedis.rpush("nicklist", playerName);

        } catch (Exception e) {
            System.out.println("[NickManager] Impossible d'activer le Nick.");
        }
    }

    public static void removeNick(UUID uuid) {
        try (Jedis jedis = RedisManager.getRedisDatabase().getJedisPool().getResource()) {
            jedis.lrem("nicklist", 0, getRealName(uuid));
            jedis.del("nickplayers:" + uuid.toString() + ":" + "nick");
            jedis.del("nickplayers:" + uuid.toString() + ":" + "nickname");
            jedis.del("nickplayers:" + uuid.toString() + ":" + "rank");
            jedis.del("nickplayers:" + uuid.toString() + ":" + "skin");
            jedis.del("nickplayers:" + uuid.toString() + ":" + "realname");
        } catch (Exception e) {
            System.out.println("[NickManager] Impossible de désactiver le Nick.");
        }
    }

    public static void updatePseudo(UUID uuid, String name) {
        try (Jedis jedis = RedisManager.getRedisDatabase().getJedisPool().getResource()) {
            jedis.set("nickplayers:" + uuid.toString() + ":" + "nickname", name);
        } catch (Exception e) {
            System.out.println("[NickManager] Impossible de changer le grade du Nick.");
        }
    }

    public static void updateRank(UUID uuid, Rank rank) {
        try (Jedis jedis = RedisManager.getRedisDatabase().getJedisPool().getResource()) {
            jedis.set("nickplayers:" + uuid.toString() + ":" + "rank", rank.getIdName());
        } catch (Exception e) {
            System.out.println("[NickManager] Impossible de changer le grade du Nick.");
        }
    }

    public static void updateSkin(UUID uuid, String skinName) {
        try (Jedis jedis = RedisManager.getRedisDatabase().getJedisPool().getResource()) {
            jedis.set("nickplayers:" + uuid.toString() + ":" + "skin", skinName);
        } catch (Exception e) {
            System.out.println("[NickManager] Impossible de changer le grade du Nick.");
        }
    }

    public static String getRealName(UUID uuid) {
        try (Jedis jedis = RedisManager.getRedisDatabase().getJedisPool().getResource()) {
            return jedis.get("nickplayers:" + uuid.toString() + ":" + "realname");
        } catch (Exception e) {
            System.out.println("[NickManager] Impossible de récupérer le pseudo du Nick.");
        }
        return null;
    }

    public static String getNickName(UUID uuid) {
        try (Jedis jedis = RedisManager.getRedisDatabase().getJedisPool().getResource()) {
            String name = jedis.get("nickplayers:" + uuid.toString() + ":" + "nickname");
            return name == null ? Bukkit.getPlayer(uuid).getName() : name;
        } catch (Exception e) {
            System.out.println("[NickManager] Impossible de récupérer le pseudo du Nick.");
        }
        return null;
    }

    public static String getSkinName(UUID uuid) {
        try (Jedis jedis = RedisManager.getRedisDatabase().getJedisPool().getResource()) {
            String skin = jedis.get("nickplayers:" + uuid.toString() + ":" + "skin");
            return skin == null ? Bukkit.getPlayer(uuid).getName() : skin;
        } catch (Exception e) {
            System.out.println("[NickManager] Impossible de récupérer le Skin du Nick.");
        }
        return null;
    }

    public static Rank getRankName(UUID uuid) {
        try (Jedis jedis = RedisManager.getRedisDatabase().getJedisPool().getResource()) {
            return Rank.getRankByIdName(jedis.get("nickplayers:" + uuid.toString() + ":" + "rank"));
        } catch (Exception e) {
            System.out.println("[NickManager] Impossible de récupérer le Skin du Nick.");
        }
        return null;
    }

    public static boolean isNick(UUID uuid) {
        try (Jedis jedis = RedisManager.getRedisDatabase().getJedisPool().getResource()) {
            if ((jedis.get("nickplayers:" + uuid.toString() + ":" + "nick") != null)
                    && (jedis.get("nickplayers:" + uuid.toString() + ":" + "nick").equalsIgnoreCase("true")))
                return true;
        } catch (Exception e) {
            System.out.println("[NickManager] Impossible de récupérer le Nick d'une Joueur.");
        }
        return false;
    }

    public static List<String> getNickList() {
        List<String> nameNickList = new ArrayList<String>();
        try (Jedis jedis = RedisManager.getRedisDatabase().getJedisPool().getResource()) {
            for (String nameString : jedis.lrange("nicklist", 0, 50)) {
                nameNickList.add(nameString);
            }
        } catch (Exception e) {
            System.out.println("[NickManager] Impossible de récupérer le Nick d'une Joueur.");
        }
        return nameNickList;
    }

    public static final void reloadSkinForSelf(Player player) {
        final EntityPlayer ep = ((CraftPlayer) player).getHandle();
        final PacketPlayOutPlayerInfo removeInfo = new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.REMOVE_PLAYER, ep);
        final PacketPlayOutPlayerInfo addInfo = new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER, ep);
        final Location loc = player.getLocation().clone();
        ep.playerConnection.sendPacket(removeInfo);
        ep.playerConnection.sendPacket(addInfo);
//        player.teleport(otherWorldLocation);
        new BukkitRunnable() {
            @Override
            public void run() {
                player.teleport(loc);
                ep.playerConnection.sendPacket(new PacketPlayOutRespawn(ep.dimension, ep.getWorld().getDifficulty(), ep.getWorld().getWorldData().getType(), ep.playerInteractManager.getGameMode()));
                player.updateInventory();
            }
        }.runTaskLater(SpigotFaruAPI.getInstance(), 2L);
    }

    public static Field getField(Class<?> clazz, String name) {
        try {
            Field field = clazz.getDeclaredField(name);
            field.setAccessible(true);
            return field;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
