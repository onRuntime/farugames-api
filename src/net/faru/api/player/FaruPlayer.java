package net.faru.api.player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import net.faru.api.player.currency.Currency;
import net.faru.api.player.data.DataType;
import net.faru.api.player.rank.Rank;
import net.faru.api.tools.player.UUIDManager;
import net.faru.data.database.currency.ICurrency;
import net.faru.data.database.player.IData;
import net.faru.data.database.player.IExperience;
import net.faru.data.database.player.IPermission;
import net.faru.data.database.rank.IRank;
import net.faru.data.spigot.SpigotFaruData;

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
	
	public FaruPlayer(String playerName) {
		this.uuid = UUIDManager.getUUID(playerName);
		this.player = Bukkit.getPlayer(playerName);
		
		IRank.createAccount(uuid);
		IPermission.createAccount(uuid);
		ICurrency.createAccount(uuid);
		IExperience.createAccount(uuid);
		IData.createAccount(uuid, player);
		
		this.loadData();
		this.setRank(IRank.getRank(uuid));
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
		IPermission.setPermissions(uuid, permissionsList);
	}
	
	public void loadPermissions() {
		for(String permission : IPermission.getPermissions(uuid)) {
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
			ICurrency.addCoins(uuid, currency, getCoins(currency));
		}
	}
	
	public void loadCoins() {
		for (Currency currency : Currency.values()) {
			this.mapCurrency.put(currency, ICurrency.getCoins(uuid, currency));
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
	
	public void pushData() {
		for (DataType dataType : DataType.values()) {
			IData.setData(uuid, dataType, this.mapData.get(dataType));
		}
	}
	
	public void pushData(DataType dataType) {
		IData.setData(uuid, dataType, this.mapData.get(dataType));
	}
	
	public void loadData() {
		for (DataType dataType : DataType.values()) {
			this.mapData.put(dataType, IData.getData(uuid, dataType));
		}
	}
	
	public void setData(DataType dataType, boolean values) {
		this.mapData.put(dataType, values);
	}

	public boolean getData(DataType dataType) {
		return (boolean) this.mapData.get(dataType);
	}
	
	public void pushExperience() {
		IExperience.setExperience(uuid, this.getExperience());
	}
	
	public void loadExperience() {
		this.experience = IExperience.getExperience(uuid);
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
	
	public static FaruPlayer getPlayer(UUID uuid) {
		return SpigotFaruData.iFaruPlayer.get(uuid) != null ?
				SpigotFaruData.iFaruPlayer.get(uuid) :
					SpigotFaruData.iFaruPlayer.put(uuid, new FaruPlayer(UUIDManager.getName(uuid.toString())));
	}
}
