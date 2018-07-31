package net.farugames.api.spigot.player;

import net.farugames.api.redis.RedisManager;
import net.farugames.api.spigot.SpigotFaruAPI;
import net.farugames.api.spigot.player.currency.Currency;
import net.farugames.api.spigot.player.data.DataType;
import net.farugames.api.spigot.player.languages.Lang;
import net.farugames.api.spigot.player.nick.NickManager;
import net.farugames.api.spigot.player.rank.Rank;
import net.farugames.api.sql.accounts.*;
import net.farugames.api.tools.DefaultFontInfo;
import net.minecraft.server.v1_9_R2.Packet;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_9_R2.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_9_R2.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class FaruPlayer {
    private UUID uuid;
    private Player player;

    private Rank rank;
    private int permissionLevel;
    private boolean nick;

    private Boolean muted;
    private Boolean banned;

    private Integer experience;

    private Map<Currency, Integer> mapCurrency = new HashMap<>();
    private Map<DataType, Object> mapData = new HashMap<>();

    private List<String> permissionsList;

    private net.minecraft.server.v1_9_R2.ItemStack itemStackHead;

    public FaruPlayer(UUID uuid) {
        this.uuid = uuid;
        this.player = Bukkit.getPlayer(uuid);

        IRank.createAccount(uuid);
        this.setRank(IRank.getRank(uuid));
        this.permissionLevel = IRank.getPermissionLevel(uuid);

        ICurrency.createAccount(uuid);
        IExperience.createAccount(uuid);
        IData.createAccount(uuid);
        nick = NickManager.isNick(uuid);

        this.createCache();

        this.permissionsList = IPermission.getPermissions(uuid);

        this.loadPlayerHead();
        this.updateNickAndSkin();
    }

    public static FaruPlayer getPlayer(UUID uuid) {
        if (SpigotFaruAPI.iFaruPlayer.get(uuid) == null) {
            SpigotFaruAPI.iFaruPlayer.put(uuid, new FaruPlayer(uuid));
        }
        return SpigotFaruAPI.iFaruPlayer.get(uuid);
    }

    public static void sendPacket(Packet<?> packet) {
        for (Player onlinepPlayers : Bukkit.getOnlinePlayers()) {
            ((CraftPlayer) onlinepPlayers).getHandle().playerConnection.sendPacket(packet);
        }
    }

    private void loadPlayerHead() {
        ItemStack itemStack = new org.bukkit.inventory.ItemStack(Material.SKULL_ITEM, 1,
                (short) 3);
        SkullMeta itemMeta = (SkullMeta) itemStack.getItemMeta();
        itemMeta.setOwner(this.player.getDisplayName());
        this.itemStackHead = CraftItemStack.asNMSCopy(itemStack);
    }

    public void updateNickAndSkin() {
        //TODO : Nick & Skin update
    }

    private void createCache() {
        //$
        for (Currency currency : Currency.values()) {
            Integer iCoins = 0;
            try (Jedis jedis = RedisManager.getJedisPool().getResource()) {
                if (!(jedis.exists("currency:" + uuid.toString() + ":" + currency.getName()))) {
                    jedis.set("currency:" + uuid.toString() + ":" + currency.getName(),
                            ICurrency.getCoins(uuid, currency) + "");
                }
                iCoins = Integer.parseInt(jedis.get("currency:" + uuid.toString() + ":" + currency.getName()));
            } catch (Exception e) {
                System.out.println("[FaruPlayer] Impossible de récupérer les Coins du Joueur.");
                e.printStackTrace();
            }
            this.mapCurrency.put(currency, iCoins);
        }

        //xp
        try (Jedis jedis = RedisManager.getJedisPool().getResource()) {
            if (!(jedis.exists("experience:" + uuid.toString()))) {
                jedis.set("experience:" + uuid.toString(), IExperience.getExperience(uuid) + "");
            }
            experience = Integer.valueOf(jedis.get("experience:" + uuid.toString()));
        } catch (Exception e) {
            System.out.println("[FaruPlayer] Impossible de récupérer les Level Experience du Joueur.");
            e.printStackTrace();
        }

        //data
        for (DataType dataType : DataType.values()) {

            Object object = "";
            try (Jedis jedis = RedisManager.getJedisPool().getResource()) {
                if (!(jedis.exists("playerdata:" + uuid.toString() + ":" + dataType.getColumn()))) {
                    jedis.set("playerdata:" + uuid.toString() + ":" + dataType.getColumn(),
                            IData.getData(uuid, dataType).toString());
                }
                object = Boolean
                        .parseBoolean(jedis.get("playerdata:" + uuid.toString() + ":" + dataType.getColumn()));
            } catch (Exception e) {
                System.out.println("[FaruPlayer] Impossible de récupérer les informations du Joueur.");
                e.printStackTrace();
            }
            this.mapData.put(dataType, object);
        }
    }

    public UUID getUUID() {
        return this.uuid;
    }

    public Player getPlayer() {
        return this.player;
    }

    public Rank getRank() {
        return this.rank;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }

    public Boolean isMuted() {
        return this.muted;
    }

    public void setMuted(Boolean muted) {
        this.muted = muted;
    }

    public Boolean isBanned() {
        return this.banned;
    }

    public void setBanned(Boolean banned) {
        this.banned = banned;
    }

    public boolean hasPermission(String permission) {
        return this.permissionsList.contains(permission);
    }

    public void addPermission(String permission) {
        this.permissionsList.add(permission);
    }

    public void removePermission(String permission) {
        this.permissionsList.remove(permission);
    }

    public Integer getCoins(Currency currency) {
        return this.mapCurrency.get(currency);
    }

    public void addCoins(Currency currency, Integer coins) {
        try (Jedis jedis = RedisManager.getJedisPool().getResource()) {
            jedis.set("currency:" + uuid.toString() + ":" + currency.getName(), (getCoins(currency) + coins) + "");
            this.mapCurrency.put(currency, getCoins(currency) + coins);
        } catch (Exception e) {
            System.out.println("[FaruPlayer] Impossible d'ajouter des coins au Joueur.");
        }
    }

    public void removeCoins(Currency currency, Integer coins) {
        try (Jedis jedis = RedisManager.getJedisPool().getResource()) {
            jedis.set("currency:" + uuid.toString() + ":" + currency.getName(), (getCoins(currency) - coins) + "");
            this.mapCurrency.put(currency, getCoins(currency) - coins);
        } catch (Exception e) {
            System.out.println("[IPlayer] Impossible de supprimer des coins au Joueur.");
        }
    }

    public Integer getExperience() {
        return this.experience;
    }

    public void addExperience(Integer coins) {
        this.experience = experience + coins;
    }

    public void removeExperience(Integer coins) {
        this.experience = experience - coins;
    }

    public void setData(DataType dataType, boolean values) {
        try (Jedis jedis = RedisManager.getJedisPool().getResource()) {
            jedis.set("playerdata:" + uuid.toString() + ":" + dataType.getColumn(), values + "");
            this.mapData.put(dataType, values);
        } catch (Exception e) {
            System.out.println("[FaruPlayer] Impossible de changer les datas au Joueur.");
        }
    }

    public Object getData(DataType dataType) {
        return this.mapData.get(dataType);
    }

    public Lang getLanguage() {
        return Lang.ENGLISH;
    }

    public boolean isNick() {
        return nick;
    }

    public void setNick(boolean nick) {
        this.nick = nick;
    }

    public int getPermissionLevel() {
        return permissionLevel;
    }

    public net.minecraft.server.v1_9_R2.ItemStack getItemStackHead() {
        return itemStackHead;
    }

    public void sendCenteredMessage(String message) {
        if (message == null || message.equals(""))
            player.sendMessage("");
        message = ChatColor.translateAlternateColorCodes('&', message);

        int messagePxSize = 0;
        boolean previousCode = false;
        boolean isBold = false;

        for (char c : message.toCharArray()) {
            if (c == '§') {
                previousCode = true;
                continue;
            } else if (previousCode == true) {
                previousCode = false;
                if (c == 'l' || c == 'L') {
                    isBold = true;
                    continue;
                } else
                    isBold = false;
            } else {
                DefaultFontInfo dFI = DefaultFontInfo.getDefaultFontInfo(c);
                messagePxSize += isBold ? dFI.getBoldLength() : dFI.getLength();
                messagePxSize++;
            }
        }

        int halvedMessageSize = messagePxSize / 2;
        int toCompensate = 154 - halvedMessageSize;
        int spaceLength = DefaultFontInfo.SPACE.getLength() + 1;
        int compensated = 0;
        StringBuilder sb = new StringBuilder();
        while (compensated < toCompensate) {
            sb.append(" ");
            compensated += spaceLength;
        }
        player.sendMessage(sb.toString() + message);
    }
}
