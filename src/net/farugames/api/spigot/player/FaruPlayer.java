package net.farugames.api.spigot.player;

import net.farugames.api.spigot.SpigotFaruAPI;
import net.farugames.api.spigot.player.currency.Currency;
import net.farugames.api.spigot.player.data.DataType;
import net.farugames.api.spigot.player.languages.Lang;
import net.farugames.api.spigot.player.rank.Rank;
import net.farugames.data.database.entities.PlayerDataEntity;
import net.farugames.data.spigot.SpigotFaruData;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import redis.clients.jedis.Jedis;

import java.util.*;

public class FaruPlayer {

    private UUID uuid;
    private Player player;

    private Rank rank;

    private Boolean muted;
    private Boolean banned;

    private Integer experience;

    private Map<Currency, Integer> mapCurrency = new HashMap<Currency, Integer>();
    private Map<DataType, Object> mapData = new HashMap<DataType, Object>();

    private List<String> permissionsList = new ArrayList<String>();

    public FaruPlayer(UUID uuid) {
        this.uuid = uuid;
        this.player = Bukkit.getPlayer(uuid);

        this.createAccounts();
        this.loadPlayer();
    }

    private void createAccounts() {
        Jedis jedis = SpigotFaruData.getInstance().getRedisDatabase().getJedisPool().getResource();
        if (!jedis.exists("PlayerData:" + uuid))
            jedis.set("PlayerData:" + uuid, SpigotFaruData.getGson.toJson(new PlayerDataEntity(uuid, player.getName(), player.getName(), player.getName(), Rank.PLAYER.getIdName(), 0, 0, 0, 0)));
        for (DataType dataType : DataType.values()) {
            if (!jedis.exists("PlayerData:Preferences:" + uuid + ":" + dataType.getColumn()))
                jedis.set("PlayerData:Preferences:" + uuid + ":" + dataType.getColumn(), SpigotFaruData.getGson.toJson(dataType.getDefaultValue()));
        }
    }

    public void loadPlayer() {
        String rawPDE = SpigotFaruData.getInstance().getRedisDatabase().getJedisPool().getResource().get("PlayerData:" + player.getUniqueId());
        System.out.println(rawPDE);
        this.setRank(Rank.getRankByIdName(SpigotFaruData.getGson.fromJson(rawPDE, PlayerDataEntity.class).getPlayerRankName()));
        this.loadData();
        this.loadPermissions();
        this.loadCoins();
        this.loadExperience();
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

    public void pushPermissions() {
        SpigotFaruData.getInstance().getSpigotDatabase().setPermissions(uuid, permissionsList);
    }

    public void loadPermissions() {
        for (String permission : SpigotFaruData.getInstance().getSpigotDatabase().getPermissions(uuid)) {
            this.permissionsList.add(permission);
        }
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

    public void pushCoins() {
        for (Currency currency : Currency.values()) {
//			ICurrency.addCoins(uuid, currency, getCoins(currency));
        }
    }

    public void loadCoins() {
        for (Currency currency : Currency.values()) {
//			this.mapCurrency.put(currency, ICurrency.getCoins(uuid, currency));
        }
    }

    public Integer getCoins(Currency currency) {
        return this.mapCurrency.get(currency);
    }

    public void addCoins(Currency currency, Integer coins) {
        this.mapCurrency.put(currency, getCoins(currency) + coins);
    }

    public void removeCoins(Currency currency, Integer coins) {
        this.mapCurrency.put(currency, getCoins(currency) - coins);
    }

//	public void pushExperience() {
//		IExperience.setExperience(uuid, this.getExperience());
//	}

    public void loadExperience() {
        this.experience = SpigotFaruData.getInstance().getSpigotDatabase().getPlayerData(uuid).getPlayerExperience();
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

    public void pushData() {
        for (DataType dataType : DataType.values()) {
            SpigotFaruData.getInstance().getSpigotDatabase().setData(uuid, dataType, this.mapData.get(dataType));
        }
    }

    public void pushData(DataType dataType) {
        SpigotFaruData.getInstance().getSpigotDatabase().setData(uuid, dataType, this.mapData.get(dataType));
    }

    public void loadData() {
        Jedis jedis = SpigotFaruData.getInstance().getRedisDatabase().getJedisPool().getResource();

        for (DataType dataType : DataType.values()) {
            this.mapData.put(dataType, jedis.get("PlayerData:Preferences:" + uuid + ":" + dataType.getColumn()));
        }
    }

    public void setData(DataType dataType, boolean values) {
        this.mapData.put(dataType, values);
    }

    public Object getData(DataType dataType) {
        return this.mapData.get(dataType);
    }

    public Lang getLanguage() {
        return Lang.getByString(String.valueOf(this.getData(DataType.LANGUAGE)));
    }

    public static FaruPlayer getPlayer(UUID uuid) {
        if (SpigotFaruAPI.iFaruPlayer.get(uuid) == null) {
            SpigotFaruAPI.iFaruPlayer.put(uuid, new FaruPlayer(uuid));
        }
        return SpigotFaruAPI.iFaruPlayer.get(uuid);
    }
}
